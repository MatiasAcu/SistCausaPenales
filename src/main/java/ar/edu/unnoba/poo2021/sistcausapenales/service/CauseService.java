package ar.edu.unnoba.poo2021.sistcausapenales.service;

import ar.edu.unnoba.poo2021.sistcausapenales.model.Cause;
import org.springframework.data.domain.Page;

import javax.validation.ValidationException;
import java.util.NoSuchElementException;
import java.util.Optional;

public interface CauseService {

    public Cause create(Cause cause) throws ValidationException;

    public Cause update(Cause cause) throws ValidationException, NoSuchElementException;

    public Page<Cause> getCausesPage(int page, int size);

    public Optional<Cause> getCause(String number);
}
