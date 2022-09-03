package me.github.crystalyyds.fuckhomework.service;

import me.github.crystalyyds.fuckhomework.entity.Candidate;
import me.github.crystalyyds.fuckhomework.entity.User;
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

    public List<Candidate> findAll() {
        return candidateRepository.findAll();
    }
    public Candidate update(Candidate candidate,String introduction){
        candidate= candidateRepository.findByName(candidate.getName());
        candidate.setDescription(introduction);
        return candidateRepository.save(candidate);
    }

    public Candidate login(String username) {
        Candidate candidate = candidateRepository.findByName(username);
        if (candidate != null ) {
            return candidate;
        }
        return null;
    }
}
