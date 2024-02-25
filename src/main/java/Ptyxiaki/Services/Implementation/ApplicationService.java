package Ptyxiaki.Services.Implementation;

import Ptyxiaki.Dtos.ApplicationDto;
import Ptyxiaki.Entities.AppUser;
import Ptyxiaki.Entities.Application;
import Ptyxiaki.Repositories.IApplicationRepository;
import Ptyxiaki.Repositories.IUserRepository;
import Ptyxiaki.Security.SecurityUtility;
import Ptyxiaki.Services.IApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService implements IApplicationService {

    @Autowired
    private IApplicationRepository applicationRepository;
    @Autowired
    private IUserRepository userRepository;

    @Override
    public Page<ApplicationDto> findAllForApplicant(int pageNumber, int pageSize, String sortBy) {
        String username = SecurityUtility.getSessionUser();
        AppUser user = userRepository.findByUsername(username);

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
        Page<Application> applications = applicationRepository.findAllByUser(user, pageable);
        return applications.map(application -> mapToDto(application, new ApplicationDto()));
    }

    private ApplicationDto mapToDto(final Application application, final ApplicationDto applicationDto) {
        applicationDto.setId(application.getId());
        applicationDto.setPostTitle(application.getPost().getTitle());
        applicationDto.setStatus(application.getStatus().getDisplayName());
        return applicationDto;
    }

    private void mapToEntity(final ApplicationDto applicationDto, final Application application) {

    }
}
