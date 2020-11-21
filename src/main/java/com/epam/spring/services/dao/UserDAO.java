package com.epam.spring.services.dao;

import com.epam.spring.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserDAO {

    User findByLastName(String lastName);

    List<User> findAll();

    User findById(Long id);

    User save(User object);

    void update(User object);

    void delete(User object);

    void deleteById(Long id);
}
