package com.gleandro.metrocardapplication.repository;

import com.gleandro.metrocardapplication.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    List<AccountEntity> findByUserCode(String userCode);

    Optional<AccountEntity> getByAccountCode(String accountCode);

}
