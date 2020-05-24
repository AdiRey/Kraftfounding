package pl.kraft.subject.service;

import org.springframework.stereotype.Service;
import pl.kraft.exception.NotExistingIdException;
import pl.kraft.subject.Subject;
import pl.kraft.subject.SubjectDto;
import pl.kraft.subject.SubjectMapper;
import pl.kraft.subject.SubjectRepository;
import pl.kraft.subject.exception.DuplicateSubjectException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubjectServiceImpl implements SubjectService {

    private SubjectRepository subjectRepository;

    public SubjectServiceImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public SubjectDto save(final SubjectDto dto) {
        List<Subject> subjects = subjectRepository.findAllByName(dto.getName());
        if (!subjects.isEmpty()) {
            for (Subject subject : subjects) {
                if (subject.getTerm().equals(dto.getTerm())) {
                    throw new DuplicateSubjectException();
                }
            }
        }
        dto.setName(dto.getName().trim());
        return SubjectMapper.map(subjectRepository.save(SubjectMapper.map(dto)));
    }

    @Override
    public SubjectDto findById(final Long id) {
        try {
            Optional<Subject> foundSubject = subjectRepository.findById(id);
            Subject subject = foundSubject.get();
            return SubjectMapper.map(subject);
        } catch (NoSuchElementException e) {
            throw new NotExistingIdException();
        }
    }

    @Override
    public List<SubjectDto> getAllSubjectNames() {
        return subjectRepository.findAllDistinctName().stream().map(SubjectMapper::map).collect(Collectors.toList());
    }

    @Override
    public List<SubjectDto> getAllSubject() {
        List<SubjectDto> list = new ArrayList<>();
        subjectRepository.findAll().forEach(
                subject -> list.add(SubjectMapper.map(subject))
        );
        return list;
    }
}
