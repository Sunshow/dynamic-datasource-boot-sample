package com.example.dynamicdatasource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminUserDAO extends JpaRepository<AdminUser, Long> {
}
