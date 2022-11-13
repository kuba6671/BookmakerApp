package com.bookmakerApp.repository;

import com.bookmakerApp.model.BetTicketModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BetTicketRepository extends JpaRepository<BetTicketModel, Long> {
    List<BetTicketModel> getBetTicketModelsByUserIdUser(Long id);
    List<BetTicketModel> getBetTicketModelsByUserIdUserAndSuccess(Long id, Boolean success);
    List<BetTicketModel> getBetTicketModelsByUserIdUserAndFinish(Long id, boolean finish);
    List<BetTicketModel> getBetTicketModelsByFinish(boolean finish);
    List<BetTicketModel> getBetTicketModelsByFinishAndAndResultIsChecked(boolean finish, boolean resultIsChecked);
}
