package ar.edu.unnoba.poo2021.sistcausapenales.service;

import ar.edu.unnoba.poo2021.sistcausapenales.model.Cause;
import ar.edu.unnoba.poo2021.sistcausapenales.repositories.CauseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@Service
public class CauseServiceImpl implements CauseService {

    @Autowired
    private CauseRepository causeRepository;

    @Autowired
    private Validator validator;

    public CauseServiceImpl() {
    }

    @Override
    public Cause create(Cause cause) throws ValidationException {
        if (causeRepository.findByNumber(cause.getNumber()).isEmpty()) {
            Set<ConstraintViolation<Cause>> violations = validator.validate(cause);
            if (violations.isEmpty()) {
                return causeRepository.save(cause);
            }
            throw new ValidationException(violations.stream().map(x -> x.getMessage()).findFirst().get());
        }
        throw new ValidationException("Number already used");
    }

    @Override
    public Cause update(Cause cause) throws NoSuchElementException, ValidationException {
        Set<ConstraintViolation<Cause>> violations = validator.validate(cause);
        if (violations.isEmpty()) {
            Cause causeDB = causeRepository.findById(cause.getNumber()).get();
            causeDB.setDescription(cause.getDescription());
            return causeRepository.save(causeDB);
        }
        throw new ValidationException(violations.stream().map(x -> x.getMessage()).findFirst().get());
    }

    @Override
    public Page<Cause> getCausesPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return causeRepository.findAllCausesOrderByDate(pageable);
    }

    @Override
    public Optional<Cause> getCause(String number) {
        return causeRepository.findById(number);
    }
}
