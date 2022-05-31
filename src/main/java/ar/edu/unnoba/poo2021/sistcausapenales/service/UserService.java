package ar.edu.unnoba.poo2021.sistcausapenales.service;

import ar.edu.unnoba.poo2021.sistcausapenales.model.User;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.ValidationException;
import java.util.List;

public interface UserService {
    public User create(User user) throws ValidationException;

    public User update(User user);

    public void delete(User sessionUser, String email) throws ValidationException;

    public Page<User> getUsersPage(int page, int size);

    public List<User> getUsers();

    public User getUser(Long id);

    public UserDetails loadUserByUsername(String email);

}
