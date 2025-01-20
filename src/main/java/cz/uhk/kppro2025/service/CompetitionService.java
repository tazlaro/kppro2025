package cz.uhk.kppro2025.service;

import cz.uhk.kppro2025.model.Competition;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface CompetitionService {

    List<Competition> getAllCompetitions();
    void saveCompetition(Competition competition);
    Competition getCompetitionById(Long id);
    void deleteCompetitionById(Long id);
}
