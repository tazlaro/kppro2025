package cz.uhk.kppro2025.service;

import cz.uhk.kppro2025.model.Club;
import cz.uhk.kppro2025.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClubServiceImpl implements ClubService {

    private final ClubRepository clubRepository;

    @Autowired
    public ClubServiceImpl(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    @Override
    public List<Club> getAllClubs() {
        return clubRepository.findAll();
    }

    @Override
    public void saveClub(Club club) {
        clubRepository.save(club);
    }

    @Override
    public Club getClubById(Long id) {
        return clubRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteClubById(Long id) {
        clubRepository.deleteById(id);
    }
}
