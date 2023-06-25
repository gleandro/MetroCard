package com.gleandro.metrocardapplication.repository;

import com.gleandro.metrocardapplication.entity.RechargeAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RechargeAccountRepository extends JpaRepository<RechargeAccountEntity, Long> {

    @Query(value = "SELECT r FROM RechargeAccountEntity r " +
            "WHERE r.accountCode = :accountCode OR :accountCode is null " +
            "AND r.userCode = :userCode OR :userCode is null " +
            "ORDER BY r.createdDate DESC")
    List<RechargeAccountEntity> findByUserCodeOrderByCreatedDateDesc(
            @Param("accountCode") String accountCode,
            @Param("userCode") String userCode);

}
