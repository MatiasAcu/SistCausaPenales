package ar.edu.unnoba.poo2021.sistcausapenales.repositories;

import ar.edu.unnoba.poo2021.sistcausapenales.model.Cause;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CauseRepository extends JpaRepository<Cause, String> {

    public Optional<Cause> findByNumber(String number);

    @Query("SELECT c FROM Cause c ORDER BY c.date")
    public Page<Cause> findAllCausesOrderByDate(Pageable pageable);

}
