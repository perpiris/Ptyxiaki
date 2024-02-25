package Ptyxiaki.Services;

import Ptyxiaki.Dtos.ApplicationDto;
import org.springframework.data.domain.Page;

public interface IApplicationService {
    Page<ApplicationDto> findAllForApplicant(int pageNumber, int pageSize, String sortBy);
}
