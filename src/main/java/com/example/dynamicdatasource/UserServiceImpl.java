package com.example.dynamicdatasource;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    @Override
    public Optional<User> getUserById(Long id) {
        return userDAO.findById(id);
    }

    @Transactional
    @Override
    public User createUserByName(String name) {
        User user = new User();
        user.setName(name);
        return userDAO.save(user);
    }

}
