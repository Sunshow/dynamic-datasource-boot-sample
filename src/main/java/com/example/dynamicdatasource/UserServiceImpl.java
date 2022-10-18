package com.example.dynamicdatasource;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final EntityManager entityManager;

    private final UserDAO userDAO;

    private final AdminUserDAO adminUserDAO;

    @Transactional
    @Override
    public Optional<User> getUserById(Long id) {
        Optional<User> optional = userDAO.findById(id);
        if (optional.isPresent()) {
            User user = optional.get();
            entityManager.lock(user, LockModeType.PESSIMISTIC_WRITE);
            return Optional.of(user);
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public User createUserByName(String name) {
        User user = new User();
        user.setName(name);
        return userDAO.save(user);
    }

    @Override
    public AdminUser createAdminUserByName(String name) {
        AdminUser user = new AdminUser();
        user.setName(name);
        user.setAddress("address");
        return adminUserDAO.save(user);
    }

    @Transactional
    @Override
    public Optional<AdminUser> getAdminUserByIdForUpdate(Long id) {
        return adminUserDAO.findByIdForUpdate(id);
    }
}
