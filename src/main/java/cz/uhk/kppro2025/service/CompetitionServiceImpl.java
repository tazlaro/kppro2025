package cz.uhk.kppro2025.service;

import cz.uhk.kppro2025.model.Competition;
import cz.uhk.kppro2025.repository.CompetitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetitionServiceImpl implements CompetitionService {

    private final CompetitionRepository competitionRepository;

    @Autowired
    public CompetitionServiceImpl(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    @Override
    public List<Competition> getAllCompetitions() {
        return competitionRepository.findAll();
    }

    @Override
    public void saveCompetition(Competition competition) {
        competitionRepository.save(competition);
    }

    @Override
    public Competition getCompetitionById(Long id) {
        return competitionRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteCompetitionById(Long id) {
        competitionRepository.deleteById(id);
    }
}
