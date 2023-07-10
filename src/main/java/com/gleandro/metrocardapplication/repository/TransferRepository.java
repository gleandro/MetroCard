package com.gleandro.metrocardapplication.repository;

import com.gleandro.metrocardapplication.entity.TransferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferRepository extends JpaRepository<TransferEntity, Long> {

    @Query("SELECT t FROM TransferEntity t " +
            "WHERE t.accountCodeFrom = :filter OR :filter is null " +
            "OR t.accountCodeTo = :filter OR :filter is null " +
            "ORDER BY t.createdDate DESC")
    List<TransferEntity> getTransferByFilter(@Param("filter") String filter);

}
