package com.example.dynamicdatasource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.LockModeType;
import java.util.Optional;

@NoRepositoryBean
public interface BaseDAO<T, ID> extends JpaRepository<T, ID> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT t FROM #{#entityName} t WHERE t.id = ?1")
    Optional<T> findByIdForUpdate(ID id);

}
