package pl.edu.wat.backend.services;

import org.springframework.stereotype.Service;
import pl.edu.wat.backend.dtos.ClubDTO;
import pl.edu.wat.backend.entities.Club;
import java.util.List;

@Service
public interface ClubService {

    List<Club> getClubs();
    Club getClubById(long clubId);
    Club addClub(ClubDTO club);
    Club editClub(Club club);
    void deleteClub(long id);
}
