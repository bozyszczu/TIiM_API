package pl.edu.wat.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.backend.dtos.ClubDTO;
import pl.edu.wat.backend.entities.Club;
import pl.edu.wat.backend.exceptions.ResourceNotFoundException;
import pl.edu.wat.backend.repositories.ClubRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class ClubServiceImpl implements ClubService {

@Autowired
    private ClubRepository clubRepository;

    @Override
    public List<Club> getClubs() {
        return this.clubRepository.findAll();
    }

    @Override
    public Club getClubById(long clubId) {

        Optional<Club> clubDb = this.clubRepository.findById(clubId);
        if (clubDb.isPresent()){
            return clubDb.get();
        } else {
            throw new ResourceNotFoundException("Nie znaleziono klubu o takim ID" + clubId);
        }
    }

    @Override
    public Club addClub (ClubDTO request) {
        Club newClub = new Club();
        newClub.setId(0L);
        newClub.setClubName(request.getClubName());
        newClub.setShortname(request.getShortname());
        newClub.setCoach(request.getCoach());
        newClub.setStadium(request.getStadium());
        newClub.setWebPage(request.getWebPage());
        newClub.setCaptain(request.getCaptain());

        return clubRepository.save(newClub);
    }

    @Override
    public Club editClub (Club club) {
        Optional<Club> clubDb = this.clubRepository.findById(club.getId());
        if (clubDb.isPresent()) {
            Club editedClub = clubDb.get();
            editedClub.setClubName(club.getClubName());
            editedClub.setShortname(club.getShortname());
            editedClub.setStadium(club.getStadium());
            editedClub.setWebPage(club.getWebPage());
            editedClub.setCaptain(club.getCaptain());
            clubRepository.save(club);
            return editedClub;
        } else {
            throw new ResourceNotFoundException("Nie znaleziono klubu o takim id: " + club.getId());
        }
    }
    @Override
    public void deleteClub (long clubId) {
        Optional<Club> clubDb = this.clubRepository.findById(clubId);
        if (clubDb.isPresent()){
            this.clubRepository.delete(clubDb.get());
        } else {
            throw new ResourceNotFoundException("Nie znaleziono klubu o takim ID" + clubId);
        }
    }
}
