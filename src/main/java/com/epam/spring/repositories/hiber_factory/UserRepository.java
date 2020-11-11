package com.epam.spring.repositories.hiber_factory;

import com.epam.spring.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
