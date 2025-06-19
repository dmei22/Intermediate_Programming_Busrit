package nl.miwnn.ch16.dennis.busrit.repositories;


import nl.miwnn.ch16.dennis.busrit.model.BusritUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BusritUserRepository extends JpaRepository<BusritUser, Long> {
    Optional<BusritUser> findByUsername(String username);
    boolean existsByUsername(String username);
}
