package pl.kraft.student.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.kraft.ability.Ability;
import pl.kraft.ability.AbilityDto;
import pl.kraft.ability.AbilityRepository;
import pl.kraft.exception.NotExistingIdException;
import pl.kraft.exception.web.*;
import pl.kraft.exception.InvalidIdException;
import pl.kraft.student.*;
import pl.kraft.subject.Subject;
import pl.kraft.subject.SubjectDto;
import pl.kraft.subject.SubjectRepository;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final AbilityRepository abilityRepository;
    private PasswordEncoder passwordEncoder;

    private final String basicImage = "basicImage.jpg";

    public StudentServiceImpl(StudentRepository studentRepository, SubjectRepository subjectRepository,
                              AbilityRepository abilityRepository, PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.abilityRepository = abilityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public StudentDto save(final StudentRegisterDto dto) {
        Optional<Student> foundStudent = studentRepository.findByEmail(dto.getEmail());
        foundStudent.ifPresent(
                s -> {throw new DuplicateEmailException();}
        );
        foundStudent = studentRepository.findByLogin(dto.getLogin());
        foundStudent.ifPresent(
                s -> {throw new DuplicateLoginException();}
        );
        Student student = StudentRegisterMapper.map(dto);
        student.clearAbility();

        SubjectDto subject = dto.getSubject();
        Optional<Subject> optionalSubject = subjectRepository.findByNameAndTerm(subject.getName(), subject.getTerm());
        if (optionalSubject.isEmpty()) {
            throw new InvalidSubjectException();
        }
        try {
            for (AbilityDto dtoAb : dto.getAbilities()) {
                Ability ability = abilityRepository.findByAbility(dtoAb.getAbility()).get();
                student.addAbility(ability);
                ability.addStudent(student);
            }
        } catch (NoSuchElementException e) {
            throw new InvalidAbilityException();
        }

        student.setSubject(optionalSubject.get());

        if (dto.getImageUrl() == null) {
            student.setImageUrl(basicImage);
        } else {
            try {
                Random random = new Random();
                String[] fileName = dto.getImageUrl().trim().split("\\.");
                System.out.println(fileName[0]);
                System.out.println(fileName[1]);
                student.setImageUrl(fileName[0] + random.nextInt() % 10_000_000 + "." + fileName[1]);
            } catch (ArrayIndexOutOfBoundsException ex) {
                throw new WrongImageFormatException();
            }
        }

        String hashPassword = passwordEncoder.encode(dto.getPassword());
        student.setPassword(hashPassword);
        Student savedStudent = studentRepository.save(student);
        return StudentMapper.map(savedStudent);
    }

    public StudentDto findStudentById(final Long id) {
        Optional<Student> foundStudent = studentRepository.findById(id);
        return StudentMapper.map(foundStudent.orElseThrow(InvalidIdException::new));
    }

    public Page<StudentDto> findAllStudentsUsingPaging(final Integer numberOfPage, final String column,
                                                       final String sortText, final String text) {
        Sort sort;
        if (sortText.equals("DESC"))
            sort = Sort.by(new Sort.Order(Sort.Direction.DESC, column));
        else
            sort = Sort.by(new Sort.Order(Sort.Direction.ASC, column));
        return studentRepository.findAllBySurnameContainingIgnoreCaseOrNameContainingIgnoreCase
                (text,text, PageRequest.of(numberOfPage, 20, sort)).map(StudentMapper::map);
    }

    @Override
    @Transactional
    public StudentDto editStudent(final Long id, final StudentDto dto) {
        if (!id.equals(dto.getId())) {
            throw new IdConflictException();
        }
        try {
            Student student = studentRepository.findById(id).get();
            student.setEmail(dto.getEmail());
            student.setName(dto.getName().trim());
            student.setSurname(dto.getSurname().trim());
            student.setAvailability(dto.getAvailability());
            student.setInterests(dto.getInterests().trim());
            try {
                student.setSubject(
                        subjectRepository.findByNameAndTerm(dto.getSubject().getName(), dto.getSubject().getTerm()).get()
                );
            } catch (NoSuchElementException ex) {
                throw new InvalidSubjectException();
            }
            try {
                student.clearAbility();
                for (AbilityDto dtoAb : dto.getAbilities()) {
                    Ability ability = abilityRepository.findByAbility(dtoAb.getAbility()).get();
                    student.addAbility(ability);
                    ability.addStudent(student);
                }
            } catch (NoSuchElementException e) {
                throw new InvalidAbilityException();
            }
            return StudentMapper.map(student);
        } catch (NoSuchElementException ex) {
            throw new InvalidStudentException();
        }
    }
}