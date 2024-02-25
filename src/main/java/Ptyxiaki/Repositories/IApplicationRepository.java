package Ptyxiaki.Repositories;

import Ptyxiaki.Entities.AppUser;
import Ptyxiaki.Entities.Application;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IApplicationRepository extends JpaRepository<Application, Long> {
    Page<Application> findAllByUser(AppUser user, Pageable pageable);
}
