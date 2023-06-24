package com.gleandro.metrocardapplication.entity;

import com.gleandro.metrocardapplication.util.Constants;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Data
@Entity
@Table(name = "transfer")
public class TransferEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, length = 8, nullable = false, name = "transfer_code")
    private String transferCode;
    @Column(length = 10, nullable = false)
    private Double amount;
    @Column(length = 50, nullable = false)
    private LocalDateTime createdDate = LocalDateTime.now(ZoneId.of(Constants.TIME_ZONE_DEFAULT));
    @Column(length = 8, nullable = false, name = "user_code")
    private String userCode;
    @Column(length = 250)
    private String comment;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_code", referencedColumnName = "user_code", insertable = false, updatable = false)
    private UserEntity userEntity;


}
