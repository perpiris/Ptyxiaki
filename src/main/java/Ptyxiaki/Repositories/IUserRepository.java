package Ptyxiaki.Repositories;

import Ptyxiaki.Entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByEmail(String email);
    AppUser findByUsername(String userName);
    AppUser findFirstByUsername(String username);
}
