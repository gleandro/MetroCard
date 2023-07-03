package com.gleandro.metrocardapplication.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gleandro.metrocardapplication.util.Constants;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Data
@Entity
@Table(name = "recharge_account")
public class RechargeAccountEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Double amount;
    @Column(length = 50, nullable = false, name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now(ZoneId.of(Constants.TIME_ZONE_DEFAULT));
    @Column(length = 8, nullable = false, name = "account_code")
    private String accountCode;
    @Column(length = 8, nullable = false, name = "user_code")
    private String userCode;

    private String accountNumber;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "account_code", referencedColumnName = "account_code", insertable = false, updatable = false)
    private AccountEntity accountEntity;

}
