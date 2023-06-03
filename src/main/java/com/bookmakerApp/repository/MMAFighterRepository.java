package com.bookmakerApp.repository;

import com.bookmakerApp.model.mma.MMAFighterModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MMAFighterRepository extends JpaRepository<MMAFighterModel, Long> {
}
