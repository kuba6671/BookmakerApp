package com.bookmakerApp.repository;

import com.bookmakerApp.model.BettingTicketModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BettingTicketRepository extends JpaRepository<BettingTicketModel, Long> {
    List<BettingTicketModel> getBettingTicketModelByUser(Long id);
    List<BettingTicketModel> getBettingTicketModelByUserAndSuccess(Long id, Boolean success);
    List<BettingTicketModel> getBettingTicketModelByUserAndFinish(Long id, boolean finish);
}
