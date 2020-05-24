package pl.kraft.student;

import pl.kraft.ability.AbilityDto;
import pl.kraft.subject.SubjectDto;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDto {
    @NotNull(message = "Id has to be not null.")
    private Long id;
    @Email(message = "Email has to be correct.")
    private String email;
    @NotBlank(message = "Name may not be blank.")
    @Size(min = 3, max = 40)
    private String name;
    @NotBlank(message = "Surname may not be blank.")
    private String surname;
    private String imageUrl;
    @Size(max = 250, message = "Too long interests.")
    private String interests;
    @Min(value = 0, message = "Min availability is 0.")
    @Max(value = 168, message = "Max availability is 168.")
    private Integer availability;
    @Valid
    private SubjectDto subject;
    private List<@Valid  AbilityDto> abilities = new ArrayList<>();

    public StudentDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public Integer getAvailability() {
        return availability;
    }

    public void setAvailability(Integer availability) {
        this.availability = availability;
    }

    public SubjectDto getSubject() {
        return subject;
    }

    public void setSubject(SubjectDto subject) {
        this.subject = subject;
    }

    public List<AbilityDto> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<AbilityDto> abilities) {
        this.abilities = abilities;
    }
}
