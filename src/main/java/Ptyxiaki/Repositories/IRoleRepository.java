package Ptyxiaki.Repositories;

import Ptyxiaki.Entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<AppRole, Long> {

    AppRole findByName(String name);
}
