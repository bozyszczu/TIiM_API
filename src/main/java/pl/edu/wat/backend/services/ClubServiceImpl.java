package pl.edu.wat.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.backend.dtos.ClubRequest;
import pl.edu.wat.backend.dtos.ClubResponse;
import pl.edu.wat.backend.entities.Club;
import pl.edu.wat.backend.exceptions.ResourceNotFoundException;
import pl.edu.wat.backend.repositories.ClubRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class ClubServiceImpl implements ClubService {

    private final ClubRepository clubRepository;

    @Autowired
    public ClubServiceImpl(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    @Override
    public List<ClubResponse> getClubs() {
        return StreamSupport.stream(clubRepository.findAll().spliterator(), false)
                .map(entity -> new ClubResponse(entity.getClubName(), entity.getShortname(), entity.getCoach(), entity.getStadium(), entity.getWebPage(), entity.getPlayers()))
                .collect(Collectors.toList());
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
    public Club addClub (ClubRequest request) {
        Club newClub = new Club();
        newClub.setId(0L);
        newClub.setClubName(request.getClubName());
        newClub.setShortname(request.getShortname());
        newClub.setCoach(request.getCoach());
        newClub.setStadium(request.getStadium());
        newClub.setWebPage(request.getWebPage());
        newClub.setPlayers(request.getPlayers());

        return clubRepository.save(newClub);
    }

    @Override
    public ClubResponse editClub (Long id, ClubRequest request) {
        return clubRepository.findById(id).map(club -> {
            club.setClubName(request.getClubName());
            club.setShortname(request.getShortname());
            club.setStadium(request.getStadium());
            club.setWebPage(request.getWebPage());
            club.setPlayers(request.getPlayers());
            Club c = clubRepository.save(club);
            return new ClubResponse(c.getClubName(), c.getShortname(), c.getCoach(), c.getStadium(), c.getWebPage(), c.getPlayers());
        }).orElseThrow(()-> new ResourceNotFoundException("Nie znaleziono klubu o takim id: " + id));
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
