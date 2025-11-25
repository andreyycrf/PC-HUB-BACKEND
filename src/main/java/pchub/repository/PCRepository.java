package pchub.repository;

import pchub.model.PC;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PCRepository extends JpaRepository<PC, Long> {
}
