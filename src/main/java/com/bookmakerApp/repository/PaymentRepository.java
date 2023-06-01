package com.bookmakerApp.repository;

import com.bookmakerApp.model.PaymentModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<PaymentModel, Long> {
    List<PaymentModel> getPaymentModelsByIsCheckedAndStatusIsNotIn(Boolean isChecked, List<String> status);

    Page<PaymentModel> getPaymentModelsByUser_IdUser(Long idUser, Pageable pageable);
}
