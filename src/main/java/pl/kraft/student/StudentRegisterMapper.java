package pl.kraft.student;

import pl.kraft.ability.AbilityMapper;
import pl.kraft.subject.SubjectMapper;

import java.util.stream.Collectors;

public class StudentRegisterMapper {
    public static Student map(StudentRegisterDto dto) {
        if (dto == null) {
            return null;
        }
        Student student = new Student();
        student.setId(dto.getId());
        student.setEmail(dto.getEmail().trim());
        student.setPassword(dto.getPassword().trim());
        student.setName(dto.getName().trim());
        student.setSurname(dto.getSurname().trim());
        student.setAvailability(dto.getAvailability());
        student.setInterests(dto.getInterests().trim());
        student.setImageUrl(dto.getImageUrl());
        student.setSubject(SubjectMapper.map(dto.getSubject()));
        student.setLogin(dto.getLogin().trim());
        student.setAbilities(dto.getAbilities().stream().map(AbilityMapper::map).collect(Collectors.toList()));
        return student;
    }

    public static StudentRegisterDto map(Student student) {
        if (student == null) {
            return null;
        }
        StudentRegisterDto dto = new StudentRegisterDto();
        dto.setId(student.getId());
        dto.setEmail(student.getEmail());
        dto.setPassword(student.getPassword());
        dto.setName(student.getName());
        dto.setSurname(student.getSurname());
        dto.setAvailability(student.getAvailability());
        dto.setInterests(student.getInterests());
        dto.setImageUrl(student.getImageUrl());
        dto.setSubject(SubjectMapper.map(student.getSubject()));
        dto.setLogin(student.getLogin());
        dto.setAbilities(student.getAbilities().stream().map(AbilityMapper::map).collect(Collectors.toList()));
        return dto;
    }
}
