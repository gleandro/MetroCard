package com.gleandro.metrocardapplication.repository;

import com.gleandro.metrocardapplication.entity.TransferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepository extends JpaRepository<TransferEntity, Long> {

}
