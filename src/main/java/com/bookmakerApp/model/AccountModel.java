package com.bookmakerApp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class AccountModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idAccount;
    private BigDecimal bankBalance;
    @OneToOne(mappedBy = "account")
    private UserModel user;
}
