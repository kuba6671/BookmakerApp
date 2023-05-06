package com.bookmakerApp.repository;

import com.bookmakerApp.model.BetTicketModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BetTicketRepository extends JpaRepository<BetTicketModel, Long> {
    Page<BetTicketModel> getBetTicketModelsByUserIdUser(Long id, Pageable pageable);

    Page<BetTicketModel> getBetTicketModelsByUserIdUserAndSuccess(Long id, Boolean success, Pageable pageable);

    Page<BetTicketModel> getBetTicketModelsByUserIdUserAndFinish(Long id, boolean finish, Pageable pageable);

    List<BetTicketModel> getBetTicketModelsByFinish(boolean finish);

    List<BetTicketModel> getBetTicketModelsByFinishAndAndResultIsChecked(boolean finish, boolean resultIsChecked);
}
