package pl.kraft.ability.service;

import org.springframework.stereotype.Service;
import pl.kraft.ability.Ability;
import pl.kraft.ability.AbilityDto;
import pl.kraft.ability.AbilityMapper;
import pl.kraft.ability.AbilityRepository;
import pl.kraft.exception.NotExistingIdException;
import pl.kraft.exception.web.DuplicateAbilityException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AbilityServiceImpl implements AbilityService{

    private AbilityRepository abilityRepository;

    public AbilityServiceImpl(AbilityRepository abilityRepository) {
        this.abilityRepository = abilityRepository;
    }

    @Override
    public List<AbilityDto> getAllAbilities() {
        List<AbilityDto> abilityDtoList = new ArrayList<>();
        abilityRepository.findAll().forEach(
                ability -> abilityDtoList.add(AbilityMapper.map(ability))
        );
        return abilityDtoList;
    }

    @Override
    public AbilityDto getAbilityById(Long id) {
        return AbilityMapper.map(abilityRepository.findById(id).orElseThrow(NotExistingIdException::new));
    }

    @Override
    public AbilityDto save(final AbilityDto dto) {
        Optional<Ability> ability = abilityRepository.findByAbility(dto.getAbility());
        ability.ifPresent(
                u -> {throw new DuplicateAbilityException();
                }
        );
        return AbilityMapper.map(abilityRepository.save(AbilityMapper.map(dto)));
    }
}
