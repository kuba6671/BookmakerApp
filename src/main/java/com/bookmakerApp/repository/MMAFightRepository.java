package com.bookmakerApp.repository;

import com.bookmakerApp.model.mma.MMAFightModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MMAFightRepository extends JpaRepository<MMAFightModel, Long> {
}
