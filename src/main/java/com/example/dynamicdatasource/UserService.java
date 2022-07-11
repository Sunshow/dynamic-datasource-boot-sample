package com.example.dynamicdatasource;

import java.util.Optional;

public interface UserService {

    Optional<User> getUserById(Long id);

    User createUserByName(String name);

}
