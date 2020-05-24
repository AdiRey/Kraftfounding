package pl.kraft.ability;

public class AbilityMapper {
    public static Ability map(AbilityDto dto) {
        if(dto == null) {
            return null;
        }
        Ability ability = new Ability();
        ability.setId(dto.getId());
        ability.setAbility(dto.getAbility());
        return ability;
    }

    public static AbilityDto map(Ability ability) {
        if (ability == null) {
            return null;
        }
        AbilityDto dto = new AbilityDto();
        dto.setId(ability.getId());
        dto.setAbility(ability.getAbility());
        return dto;
    }
}
