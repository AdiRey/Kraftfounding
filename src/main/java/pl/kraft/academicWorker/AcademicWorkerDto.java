package pl.kraft.academicWorker;

import pl.kraft.ability.AbilityDto;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class AcademicWorkerDto {
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
    private List<@Valid AbilityDto> abilities = new ArrayList<>();

    public AcademicWorkerDto() {
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

    public List<AbilityDto> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<AbilityDto> abilities) {
        this.abilities = abilities;
    }
}
