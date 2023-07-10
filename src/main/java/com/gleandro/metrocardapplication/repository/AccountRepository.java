package com.gleandro.metrocardapplication.repository;

import com.gleandro.metrocardapplication.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    List<AccountEntity> findByUserCode(String userCode);

    @Query("SELECT a FROM AccountEntity a " +
            "WHERE a.accountCode = :filter OR :filter is null " +
            "OR a.accountNumber = :filter OR :filter is null")
    Optional<AccountEntity> getByAccountCode(@Param("filter") String filter);


}
