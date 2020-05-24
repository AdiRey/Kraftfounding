package pl.kraft.project.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.kraft.ability.Ability;
import pl.kraft.ability.AbilityDto;
import pl.kraft.ability.AbilityMapper;
import pl.kraft.ability.AbilityRepository;
import pl.kraft.exception.InvalidIdException;
import pl.kraft.exception.web.InvalidAbilityException;
import pl.kraft.exception.web.InvalidStudentException;
import pl.kraft.project.*;
import pl.kraft.student.Student;
import pl.kraft.student.StudentDto;
import pl.kraft.student.StudentMapper;
import pl.kraft.student.StudentRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    private ProjectRepository projectRepository;
    private StudentRepository studentRepository;
    private AbilityRepository abilityRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository, StudentRepository studentRepository, AbilityRepository abilityRepository) {
        this.projectRepository = projectRepository;
        this.studentRepository = studentRepository;
        this.abilityRepository = abilityRepository;
    }

    @Override
    public List<ProjectDto> getThreeLastAddedProjects() {
        return projectRepository.findTop3ByOrderByDateDesc().stream().map(ProjectMapper::map).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<AbilityDto> getProjectAbilities(Long id) {
        try {
            return projectRepository.findById(id).get().getAbilities().stream().map(AbilityMapper::map).collect(Collectors.toList());
        } catch (NoSuchElementException e) {
            throw new InvalidIdException();
        }
    }

    @Override
    @Transactional
    public List<StudentDto> getProjectStudents(Long id) {
        try {
            return projectRepository.findById(id).get().getStudentList().stream().map(StudentMapper::map).collect(Collectors.toList());
        } catch (NoSuchElementException e) {
            throw new InvalidIdException();
        }
    }

    @Override
    public Page<ProjectDto> getAllNotCompletedProjects(final int numberOfPage, final String sortText, final String text) {
        Sort sort;
        if (sortText.equals("DESC"))
            sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "title"));
        else
            sort = Sort.by(new Sort.Order(Sort.Direction.ASC, "title"));
        return projectRepository.findAllByTitleContainingIgnoreCaseAndCompletedIsFalse
                (text, PageRequest.of(numberOfPage, 20, sort)).map(ProjectMapper::map);
    }

    @Override
    public ProjectDto getProjectById(final Long id) {
        try {
            return ProjectMapper.map(projectRepository.findById(id).get());
        } catch (NoSuchElementException e) {
            throw new InvalidIdException();
        }
    }

    @Override
    @Transactional
    public ProjectDto saveProject(final ProjectRegisterDto dto) {
        try {
            Project project = ProjectRegisterMapper.map(dto);
            project.setDate(LocalDateTime.now());
            project.getAbilities().clear();
            Student student = studentRepository.findByEmail(dto.getStudent().getEmail()).get();
            project.setStudent(student);
            student.addMyProjects(project);
            try {
                for (AbilityDto dtoAb : dto.getAbilities()) {
                    Ability ability = abilityRepository.findByAbility(dtoAb.getAbility()).get();
                    project.addAbility(ability);
                }
                return ProjectMapper.map(projectRepository.save(project));
            } catch (NoSuchElementException e) {
                throw new InvalidAbilityException();
            }
        } catch (NoSuchElementException e) {
            throw new InvalidStudentException();
        }
    }
}