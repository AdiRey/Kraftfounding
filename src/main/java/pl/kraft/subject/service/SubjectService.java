package pl.kraft.subject.service;

import pl.kraft.subject.SubjectDto;

import java.util.List;

public interface SubjectService {
    SubjectDto save(SubjectDto dto);
    SubjectDto findById(Long id);
    List<SubjectDto> getAllSubjectNames();
    List<SubjectDto> getAllSubject();
}
