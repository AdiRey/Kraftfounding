package pl.kraft.academicWorker;

import pl.kraft.ability.AbilityMapper;

import java.util.stream.Collectors;

public class AcademicWorkerRegisterMapper {
    public static AcademicWorkerRegisterDto map(AcademicWorker academicWorker) {
        if (academicWorker == null) {
            return null;
        }
        AcademicWorkerRegisterDto dto = new AcademicWorkerRegisterDto();
        dto.setId(academicWorker.getId());
        dto.setEmail(academicWorker.getEmail());
        dto.setPassword(academicWorker.getPassword());
        dto.setName(academicWorker.getName());
        dto.setSurname(academicWorker.getSurname());
        dto.setInterests(academicWorker.getInterests());
        dto.setImageUrl(academicWorker.getImageUrl());
        dto.setLogin(academicWorker.getLogin());
        dto.setAbilities(academicWorker.getAbilities().stream().map(AbilityMapper::map).collect(Collectors.toList()));
        return dto;
    }

    public static AcademicWorker map(AcademicWorkerRegisterDto dto) {
        if (dto == null) {
            return null;
        }
        AcademicWorker academicWorker = new AcademicWorker();
        academicWorker.setId(dto.getId());
        academicWorker.setEmail(dto.getEmail().trim());
        academicWorker.setPassword(dto.getPassword().trim());
        academicWorker.setName(dto.getName().trim());
        academicWorker.setSurname(dto.getSurname().trim());
        academicWorker.setInterests(dto.getInterests().trim());
        academicWorker.setImageUrl(dto.getImageUrl());
        academicWorker.setLogin(dto.getLogin().trim());
        academicWorker.setAbilities(dto.getAbilities().stream().map(AbilityMapper::map).collect(Collectors.toList()));
        return academicWorker;
    }
}
