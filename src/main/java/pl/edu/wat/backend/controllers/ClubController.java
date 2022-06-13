package pl.edu.wat.backend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.backend.dtos.ClubRequest;
import pl.edu.wat.backend.dtos.ClubResponse;
import pl.edu.wat.backend.entities.Club;
import pl.edu.wat.backend.services.ClubService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class ClubController {

    private final ClubService clubService;

    @GetMapping("/api/clubs")
    public ResponseEntity<List<ClubResponse>> getClubs() {
        List<ClubResponse> clubs = clubService.getClubs();

        return new ResponseEntity<>(clubs, HttpStatus.OK);
    }

    @GetMapping("/api/clubs/{id}")
    public ResponseEntity <Club> getClubsById(@PathVariable long id) {

        return ResponseEntity.ok().body(clubService.getClubById(id));
    }

    @PostMapping("/api/clubs")
    public ResponseEntity<Club> addClub (@RequestBody ClubRequest clubRequest) {

        return ResponseEntity.ok().body(this.clubService.addClub(clubRequest));
    }

    @PutMapping("/api/clubs/{id}")
    public ResponseEntity<Club> editClub (@PathVariable Long id, @RequestBody ClubRequest request) {

        clubService.editClub(id, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/api/clubs/{id}")
    public HttpStatus deleteClub (@PathVariable long id) {

        this.clubService.deleteClub(id);
        return HttpStatus.OK;
    }
}
