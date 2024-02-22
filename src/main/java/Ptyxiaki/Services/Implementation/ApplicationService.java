package Ptyxiaki.Services.Implementation;

import Ptyxiaki.Repositories.IApplicationRepository;
import Ptyxiaki.Services.IApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService implements IApplicationService {

    @Autowired
    private IApplicationRepository applicationRepository;
}
