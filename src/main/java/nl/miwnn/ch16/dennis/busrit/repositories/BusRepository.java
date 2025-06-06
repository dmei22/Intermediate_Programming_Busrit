package nl.miwnn.ch16.dennis.busrit.repositories;


import nl.miwnn.ch16.dennis.busrit.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusRepository extends JpaRepository<Bus, Long> {

}
