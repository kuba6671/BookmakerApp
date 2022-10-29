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
    private Long idAccount;
    private BigDecimal bankBalance;
    @OneToOne
    @JoinColumn(name = "idUser")
    private UserModel user;
}
