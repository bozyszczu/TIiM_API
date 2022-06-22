package pl.edu.wat.backend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.backend.dtos.ClubDTO;
import pl.edu.wat.backend.entities.Club;
import pl.edu.wat.backend.services.ClubService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
public class ClubController {

    private final ClubService clubService;

    @GetMapping("clubs")
    public ResponseEntity<List<Club>> getClubs() {

        return ResponseEntity.ok().body(clubService.getClubs());
    }

    @GetMapping("clubs/{id}")
    public ResponseEntity <Club> getClubById(@PathVariable long id) {

        return ResponseEntity.ok().body(clubService.getClubById(id));
    }

    @PostMapping("clubs")
    public ResponseEntity<Club> addClub(@RequestBody ClubDTO club) {

        return ResponseEntity.ok().body(this.clubService.addClub(club));
    }

    @PutMapping("clubs/{id}")
    public ResponseEntity<Club> editClub (@RequestBody Club club, @PathVariable long id){

        club.setId((int) id);
        return ResponseEntity.ok().body(this.clubService.editClub(club));
    }

    @DeleteMapping("clubs/{id}")
    public HttpStatus deleteClub(@PathVariable long id) {

        this.clubService.deleteClub(id);
        return HttpStatus.OK;
    }
}
