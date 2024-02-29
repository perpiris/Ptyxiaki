package Ptyxiaki.Repositories;

import Ptyxiaki.Entities.Requirement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRequirementRepository extends JpaRepository<Requirement, Long> {
    Requirement findByLabel(String label);
}
