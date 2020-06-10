package pl.kraft.academicWorker.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.kraft.ability.Ability;
import pl.kraft.ability.AbilityDto;
import pl.kraft.ability.AbilityRepository;
import pl.kraft.academicWorker.*;
import pl.kraft.exception.InvalidIdException;
import pl.kraft.exception.web.*;
import pl.kraft.student.StudentMapper;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;

@Service
public class AcademicWorkerServiceImpl implements AcademicWorkerService{
    private AcademicWorkerRepository academicWorkerRepository;
    private AbilityRepository abilityRepository;
    private PasswordEncoder passwordEncoder;

    public AcademicWorkerServiceImpl(AcademicWorkerRepository academicWorkerRepository, AbilityRepository abilityRepository,
                                     PasswordEncoder passwordEncoder) {
        this.academicWorkerRepository = academicWorkerRepository;
        this.abilityRepository = abilityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private final String basicImage = "basicImage.jpg";

    @Override
    public Page<AcademicWorkerDto> findAllAcademicWorkersUsingPaging(final Integer page, final String column,
                                                                     final String sortText, final String filter) {
        Sort sort;
        if (sortText.equals("DESC"))
            sort = Sort.by(new Sort.Order(Sort.Direction.DESC, column));
        else
            sort = Sort.by(new Sort.Order(Sort.Direction.ASC, column));
        return academicWorkerRepository.findAllBySurnameContainingIgnoreCaseOrNameContainingIgnoreCase
                (filter,filter, PageRequest.of(page, 20, sort)).map(AcademicWorkerMapper::map);
    }

    @Transactional
    @Override
    public AcademicWorkerDto save(final AcademicWorkerRegisterDto dto) {
        Optional<AcademicWorker> foundWorker = academicWorkerRepository.findByEmail(dto.getEmail());
        foundWorker.ifPresent(
                s -> {throw new DuplicateEmailException();}
        );
        foundWorker = academicWorkerRepository.findByLogin(dto.getLogin());
        foundWorker.ifPresent(
                s -> {throw new DuplicateLoginException();}
        );
        AcademicWorker academicWorker = AcademicWorkerRegisterMapper.map(dto);
        academicWorker.clearAbility();


        try {
            for (AbilityDto dtoAb : dto.getAbilities()) {
                Ability ability = abilityRepository.findByAbility(dtoAb.getAbility()).get();
                academicWorker.addAbility(ability);
                ability.addAcademicWorker(academicWorker);
            }
        } catch (NoSuchElementException e) {
            throw new InvalidAbilityException();
        }
        if (dto.getImageUrl() == null) {
            academicWorker.setImageUrl(basicImage);
        } else {
            try {
                Random random = new Random();
                String[] fileName = dto.getImageUrl().trim().split("\\.");
                System.out.println(fileName[0]);
                System.out.println(fileName[1]);
                academicWorker.setImageUrl(fileName[0] + random.nextInt() % 10_000_000 + "." + fileName[1]);
            } catch (ArrayIndexOutOfBoundsException ex) {
                throw new WrongImageFormatException();
            }
        }

        String hashPassword = passwordEncoder.encode(dto.getPassword());
        academicWorker.setPassword(hashPassword);
        AcademicWorker savedAcademicWorker = academicWorkerRepository.save(academicWorker);
        return AcademicWorkerMapper.map(savedAcademicWorker);
    }

    @Override
    public AcademicWorkerDto findAcademicWorkerById(Long id) {
        Optional<AcademicWorker> foundAcademicWorker = academicWorkerRepository.findById(id);
        return AcademicWorkerMapper.map(foundAcademicWorker.orElseThrow(InvalidIdException::new));
    }

    @Override
    public AcademicWorkerDto editAcademicWorker(Long id, AcademicWorkerDto dto) {
        if (!id.equals(dto.getId())) {
            throw new IdConflictException();
        }
        try {
            AcademicWorker academicWorker = academicWorkerRepository.findById(id).get();
            academicWorker.setEmail(dto.getEmail());
            academicWorker.setName(dto.getName().trim());
            academicWorker.setSurname(dto.getSurname().trim());
            academicWorker.setInterests(dto.getInterests().trim());
            try {
                academicWorker.clearAbility();
                for (AbilityDto dtoAb : dto.getAbilities()) {
                    Ability ability = abilityRepository.findByAbility(dtoAb.getAbility()).get();
                    academicWorker.addAbility(ability);
                    ability.addAcademicWorker(academicWorker);
                }
            } catch (NoSuchElementException e) {
                throw new InvalidAbilityException();
            }
            return AcademicWorkerMapper.map(academicWorker);
        } catch (NoSuchElementException ex) {
            throw new InvalidStudentException();
        }
    }
}
