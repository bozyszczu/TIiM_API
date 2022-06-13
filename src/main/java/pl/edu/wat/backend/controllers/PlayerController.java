package pl.edu.wat.backend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.backend.dtos.PlayerRequest;
import pl.edu.wat.backend.dtos.PlayerResponse;
import pl.edu.wat.backend.entities.Player;
import pl.edu.wat.backend.services.PlayerService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping("/api/players")
    public ResponseEntity<List<PlayerResponse>> getPlayers(){
        List<PlayerResponse> players = playerService.getPlayers();

        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    @GetMapping("/api/players/{id}")
    public ResponseEntity <Player> getOrdersById(@PathVariable long id) {

        return ResponseEntity.ok().body(playerService.getPlayerById(id));
    }

    @PostMapping("/api/players")
    public ResponseEntity<Player> addOrder(@RequestBody PlayerRequest playerRequest) {

        return ResponseEntity.ok().body(this.playerService.addPlayer(playerRequest));
    }

    @PutMapping ("/api/players/{id}")
    public ResponseEntity<Player> editOrder (@PathVariable long id, @RequestBody PlayerRequest request){

        playerService.editPlayer(id,request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/api/players/{id}")
    public HttpStatus deleteOrder(@PathVariable long id) {

        this.playerService.deletePlayer(id);
        return HttpStatus.OK;
    }
}