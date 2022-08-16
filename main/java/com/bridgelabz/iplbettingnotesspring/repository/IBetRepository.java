package com.bridgelabz.iplbettingnotesspring.repository;

import com.bridgelabz.iplbettingnotesspring.Model.BetModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IBetRepository extends JpaRepository<BetModel,Long> {
    Optional<BetModel> findByEmailId(String email);
}
