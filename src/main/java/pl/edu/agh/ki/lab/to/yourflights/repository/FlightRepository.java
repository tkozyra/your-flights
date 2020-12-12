package pl.edu.agh.ki.lab.to.yourflights.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.ki.lab.to.yourflights.model.Customer;

import java.util.UUID;

/**
 * Klasa definiująca repozytorium ze Spring Data Jpa dla klientów
 * Na późniejszym etapie będzie służyć do pobierania danych z bazy danych
 */
@Repository
public interface FlightRepository extends JpaRepository<Customer, UUID> {
}
