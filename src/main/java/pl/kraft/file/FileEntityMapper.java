package pl.kraft.file;

import pl.kraft.student.StudentProjectMapper;

public class FileEntityMapper {
    public static FileEntity map(FileEntityDto dto) {
        if (dto == null) {
            return null;
        }
        FileEntity fileEntity = new FileEntity();
        fileEntity.setId(dto.getId());
        fileEntity.setName(dto.getName());
        fileEntity.setStudent(StudentProjectMapper.map(dto.getStudent()));
        return fileEntity;
    }

    public static FileEntityDto map(FileEntity fileEntity) {
        if (fileEntity == null) {
            return null;
        }
        FileEntityDto dto = new FileEntityDto();
        dto.setId(fileEntity.getId());
        dto.setName(fileEntity.getName());
        dto.setStudent(StudentProjectMapper.map(fileEntity.getStudent()));
        return dto;
    }
}
