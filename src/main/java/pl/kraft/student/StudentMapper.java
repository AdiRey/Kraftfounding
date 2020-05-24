package pl.kraft.student;

import pl.kraft.ability.AbilityMapper;
import pl.kraft.subject.SubjectMapper;

import java.util.stream.Collectors;

public class StudentMapper {
    public static Student map(StudentDto dto) {
        if (dto == null) {
            return null;
        }
        Student student = new Student();
        student.setId(dto.getId());
        student.setEmail(dto.getEmail());
        student.setName(dto.getName());
        student.setSurname(dto.getSurname());
        student.setAvailability(dto.getAvailability());
        student.setInterests(dto.getInterests());
        student.setImageUrl(dto.getImageUrl());
        student.setSubject(SubjectMapper.map(dto.getSubject()));
        student.setAbilities(dto.getAbilities().stream().map(AbilityMapper::map).collect(Collectors.toList()));
        return student;
    }

    public static StudentDto map(Student student) {
        if (student == null) {
            return null;
        }
        StudentDto dto = new StudentDto();
        dto.setId(student.getId());
        dto.setEmail(student.getEmail());
        dto.setName(student.getName());
        dto.setSurname(student.getSurname());
        dto.setAvailability(student.getAvailability());
        dto.setInterests(student.getInterests());
        dto.setImageUrl(student.getImageUrl());
        dto.setSubject(SubjectMapper.map(student.getSubject()));
        dto.setAbilities(student.getAbilities().stream().map(AbilityMapper::map).collect(Collectors.toList()));
        return dto;
    }
}
