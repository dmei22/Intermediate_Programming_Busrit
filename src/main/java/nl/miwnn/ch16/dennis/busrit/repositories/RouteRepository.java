package nl.miwnn.ch16.dennis.busrit.repositories;


import nl.miwnn.ch16.dennis.busrit.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route, Long> {
}
