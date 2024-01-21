package com.bookmakerApp.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PaymentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idPayment;
    private String orderId;
    private BigDecimal totalAmount;
    private String status;
    private boolean isChecked;
    private String currencyCode;
    @Column(columnDefinition = "TEXT")
    private String redirectUri;
    @ManyToOne
    @JoinColumn(name = "idUser")
    private UserModel user;
}
