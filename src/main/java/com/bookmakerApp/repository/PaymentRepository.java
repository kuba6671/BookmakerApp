package com.bookmakerApp.repository;

import com.bookmakerApp.model.PaymentModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<PaymentModel, Long> {
    List<PaymentModel> getPaymentModelsByIsChecked(Boolean isChecked);
}
