package com.gleandro.metrocardapplication.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gleandro.metrocardapplication.util.Constants;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "account")
public class AccountEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, length = 8, nullable = false, name = "account_code")
    private String accountCode;
    @Column(unique = true, length = 16, nullable = false, name = "account_number")
    private String accountNumber;
    @Column(nullable = false, name = "account_default")
    private Boolean accountDefault;
    @Column(length = 50, nullable = false, name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now(ZoneId.of(Constants.TIME_ZONE_DEFAULT));
    @Column(length = 8, nullable = false, name = "user_code")
    private String userCode;
    @Column(nullable = false)
    private Double balance;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_code", referencedColumnName = "user_code", insertable = false, updatable = false)
    private UserEntity userEntity;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "account_code", referencedColumnName = "account_code", insertable = false, updatable = false)
    private List<RechargeAccountEntity> listRechargeAccount = new ArrayList<>();

}
