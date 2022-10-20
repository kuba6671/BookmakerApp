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
    private Long id;
    private BigDecimal bankBalance;
    @OneToOne
    @JoinColumn(name = "id")
    private UserModel user;
}
