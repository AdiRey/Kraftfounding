package pl.kraft.academicWorker.service;

import org.springframework.data.domain.Page;
import pl.kraft.academicWorker.AcademicWorkerDto;
import pl.kraft.academicWorker.AcademicWorkerRegisterDto;

public interface AcademicWorkerService {
    Page<AcademicWorkerDto> findAllAcademicWorkersUsingPaging(Integer page, String column, String sort, String filter);
    AcademicWorkerDto save(final AcademicWorkerRegisterDto dto);
    AcademicWorkerDto findAcademicWorkerById(final Long id);
    AcademicWorkerDto editAcademicWorker(final Long id, final AcademicWorkerDto dto);
}
