package pl.kraft.student;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

public class StudentProjectDto {
    @Null(message = "Id has to be null.")
    private Long id;
    @NotBlank(message = "Name may not be blank")
    private String name;
    @NotBlank(message = "Surname may not be blank")
    private String surname;
    @Email(message = "Email has to be correct.")
    private String email;

    public StudentProjectDto() {
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
