package pl.kraft.subject;

public class SubjectMapper {
    public static Subject map(SubjectDto dto) {
        if (dto == null) {
            return null;
        }
        Subject subject = new Subject();
        subject.setId(dto.getId());
        subject.setName(dto.getName());
        subject.setTerm(dto.getTerm());
        return subject;
    }

    public static SubjectDto map(Subject subject) {
        if (subject == null) {
            return null;
        }
        SubjectDto dto = new SubjectDto();
        dto.setId(subject.getId());
        dto.setName(subject.getName());
        dto.setTerm(subject.getTerm());
        return dto;
    }
}