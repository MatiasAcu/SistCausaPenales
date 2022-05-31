package ar.edu.unnoba.poo2021.sistcausapenales.service;

import ar.edu.unnoba.poo2021.sistcausapenales.model.User;
import ar.edu.unnoba.poo2021.sistcausapenales.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Validator validator;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).get();
    }


    @Override
    public User create(User user) throws ValidationException {
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        if (violations.isEmpty()) {
            if (userRepository.findByEmail(user.getEmail()).isEmpty()) {
                user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
                return userRepository.save(user);
            }
            throw new ValidationException("Email is already used");
        }
        throw new ValidationException(violations.stream().map(x -> x.getMessage()).findFirst().get());
    }

    @Override
    @Transactional
    public void delete(User sessionUser, String email) throws ValidationException {
        if (Objects.equals(sessionUser.getEmail(), email)) {
            throw new ValidationException("User cant delete his own account");
        }
        userRepository.deleteByEmail(email);
    }

    @Override
    public User update(User user) {
        User userDB = userRepository.findById(user.getId()).get();
        userDB.setName(user.getName());
        userDB.setSurname(user.getSurname());
        return userRepository.save(userDB);
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public Page<User> getUsersPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAllOrderedAlphabetically(pageable);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
