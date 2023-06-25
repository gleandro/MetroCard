package com.gleandro.metrocardapplication.entity;

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
@Table(name = "user")
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, length = 8, nullable = false, name = "user_code")
    private String userCode;
    @Column(length = 50, nullable = false)
    private String name;
    @Column(length = 50, nullable = false, name = "last_name")
    private String lastName;
    @Column(length = 50)
    private String address;
    @Column(length = 50, nullable = false)
    private String email;
    @Column(length = 50, nullable = false)
    private String password;
    @Column(length = 10, name = "date_of_birth")
    private String dateOfBirth;
    @Column(length = 8, nullable = false)
    private String dni;
    @Column(length = 50, nullable = false, name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now(ZoneId.of(Constants.TIME_ZONE_DEFAULT));

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_code", referencedColumnName = "user_code")
    private List<TransferEntity> transferEntity = new ArrayList<>();

}
