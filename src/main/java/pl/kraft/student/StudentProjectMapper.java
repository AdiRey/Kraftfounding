package pl.kraft.student;

public class StudentProjectMapper {
    public static StudentProjectDto map(Student student) {
        StudentProjectDto dto = new StudentProjectDto();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setSurname(student.getSurname());
        dto.setEmail(student.getEmail());
        return dto;
    }

    public static Student map(StudentProjectDto dto) {
        Student student = new Student();
        student.setId(dto.getId());
        student.setName(dto.getName());
        student.setSurname(dto.getSurname());
        student.setEmail(dto.getEmail());
        return student;
    }
}
