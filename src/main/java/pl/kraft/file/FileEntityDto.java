package pl.kraft.file;

import pl.kraft.student.StudentProjectDto;

public class FileEntityDto {
    private Long id;
    private String name;
    private StudentProjectDto student;

    public FileEntityDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StudentProjectDto getStudent() {
        return student;
    }

    public void setStudent(StudentProjectDto student) {
        this.student = student;
    }
}
