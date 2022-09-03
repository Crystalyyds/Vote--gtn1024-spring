package me.github.crystalyyds.fuckhomework.repository;

import me.github.crystalyyds.fuckhomework.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Integer> {
    Candidate findByName(String name);
}
