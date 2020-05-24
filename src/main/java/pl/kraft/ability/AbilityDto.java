package pl.kraft.ability;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

public class AbilityDto {
    @Null(message = "Id has to be null.")
    private Long id;
    @NotBlank(message = "Ability may not be blank")
    private String ability;

    public AbilityDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }
}
