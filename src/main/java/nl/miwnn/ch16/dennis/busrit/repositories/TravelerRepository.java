package nl.miwnn.ch16.dennis.busrit.repositories;


import nl.miwnn.ch16.dennis.busrit.model.Traveler;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelerRepository extends JpaRepository<Traveler, Long> {
}
