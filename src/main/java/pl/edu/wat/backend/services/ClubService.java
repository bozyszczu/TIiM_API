package pl.edu.wat.backend.services;

import pl.edu.wat.backend.dtos.ClubRequest;
import pl.edu.wat.backend.dtos.ClubResponse;
import pl.edu.wat.backend.entities.Club;
import java.util.List;


public interface ClubService {

    List<ClubResponse> getClubs();
    Club getClubById(long clubId);
    Club addClub(ClubRequest club);
    ClubResponse editClub(Long id, ClubRequest request);
    void deleteClub(long id);
}
