package pl.kraft.student.service;

import org.springframework.data.domain.Page;
import pl.kraft.student.StudentDto;
import pl.kraft.student.StudentRegisterDto;

public interface StudentService {
    Page<StudentDto> findAllStudentsUsingPaging(Integer page, String column, String sort, String filter);
    StudentDto save(final StudentRegisterDto dto);
    StudentDto findStudentById(final Long id);
    StudentDto editStudent(final Long id, final StudentDto dto);
}
