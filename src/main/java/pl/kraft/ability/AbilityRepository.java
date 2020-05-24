package pl.kraft.ability;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AbilityRepository extends CrudRepository<Ability, Long> {
    Optional<Ability> findByAbility(String ability);
}
