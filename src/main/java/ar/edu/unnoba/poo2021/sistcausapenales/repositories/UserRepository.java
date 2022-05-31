package ar.edu.unnoba.poo2021.sistcausapenales.repositories;

import ar.edu.unnoba.poo2021.sistcausapenales.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u ORDER BY u.surname,u.name ASC")
    public Page<User> findAllOrderedAlphabetically(Pageable pageable);

    @Modifying
    @Query("DELETE FROM User u WHERE u.email= :email")
    public void deleteByEmail(String email);

}
