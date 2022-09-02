package me.github.crystalyyds.fuckhomework.service;

import me.github.crystalyyds.fuckhomework.entity.Candidate;
import me.github.crystalyyds.fuckhomework.repository.CandidateRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class CandidateService {
    private final CandidateRepository candidateRepository;

    @Inject
    public CandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public Candidate newCandidate(String name, String description) {
        Candidate candidate = new Candidate();
        candidate.setName(name);
        candidate.setDescription(description);
        return candidateRepository.save(candidate);
    }

    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }

    public void updateCandidate(Candidate candidate) {
        candidateRepository.save(candidate);
    }

    public Candidate getCandidate(int id) {
        return candidateRepository.findById(id).orElse(null);
    }
}
