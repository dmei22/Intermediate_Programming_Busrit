package nl.miwnn.ch16.dennis.busrit.repositories;


import nl.miwnn.ch16.dennis.busrit.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BusRepository extends JpaRepository<Bus, Long> {
    Optional<Bus> findByLineNumber(int lineNumber);
}
