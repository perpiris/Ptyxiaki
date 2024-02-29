package Ptyxiaki.Repositories;

import Ptyxiaki.Entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICompanyRepository extends JpaRepository<Company, Long> {
    Company findByName(String name);
}
