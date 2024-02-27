package Ptyxiaki;

import Ptyxiaki.Entities.AppRole;
import Ptyxiaki.Repositories.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataSeed implements CommandLineRunner {

    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.count() == 0) {

            AppRole developerRole = new AppRole();
            developerRole.setName("DEVELOPER");

            AppRole recruiterRole = new AppRole();
            recruiterRole.setName("RECRUITER");

            roleRepository.saveAll(List.of(developerRole, recruiterRole));
        }
    }
}
