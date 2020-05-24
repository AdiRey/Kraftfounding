package pl.kraft.subject;

import javax.validation.constraints.*;

public class SubjectDto {
    @Null(message = "Id has to be null.")
    private Long id;
    @NotBlank(message = "Name may not be blank")
    private String name;
    @Min(value = 1, message = "Term has to be greater than or equal to 1")
    @Max(value = 7, message = "Term has to be less than or equal to 7")
    private Integer term;

    public SubjectDto() {
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

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }
}
