package pl.kraft.academicWorker;

import pl.kraft.ability.AbilityMapper;

import java.util.stream.Collectors;

public class AcademicWorkerMapper {
    public static AcademicWorkerDto map(AcademicWorker academicWorker) {
        if (academicWorker == null) {
            return null;
        }
        AcademicWorkerDto dto = new AcademicWorkerDto();
        dto.setId(academicWorker.getId());
        dto.setEmail(academicWorker.getEmail());
        dto.setName(academicWorker.getName());
        dto.setSurname(academicWorker.getSurname());
        dto.setInterests(academicWorker.getInterests());
        dto.setImageUrl(academicWorker.getImageUrl());
        dto.setAbilities(academicWorker.getAbilities().stream().map(AbilityMapper::map).collect(Collectors.toList()));
        return dto;
    }

    public static AcademicWorker map(AcademicWorkerDto dto) {
        if (dto == null) {
            return null;
        }
        AcademicWorker academicWorker = new AcademicWorker();
        academicWorker.setId(dto.getId());
        academicWorker.setEmail(dto.getEmail());
        academicWorker.setName(dto.getName());
        academicWorker.setSurname(dto.getSurname());
        academicWorker.setInterests(dto.getInterests());
        academicWorker.setImageUrl(dto.getImageUrl());
        academicWorker.setAbilities(dto.getAbilities().stream().map(AbilityMapper::map).collect(Collectors.toList()));
        return academicWorker;
    }
}
