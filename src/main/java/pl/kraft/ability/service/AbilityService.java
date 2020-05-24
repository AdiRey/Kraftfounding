package pl.kraft.ability.service;

import pl.kraft.ability.AbilityDto;

import java.util.List;

public interface AbilityService {
    List<AbilityDto> getAllAbilities();
    AbilityDto save(AbilityDto dto);
    AbilityDto getAbilityById(Long id);
}
