package com.gleandro.metrocardapplication.repository;

import com.gleandro.metrocardapplication.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> getByDni(String dni);

    Optional<UserEntity> getByUserCode(String userCode);
}
