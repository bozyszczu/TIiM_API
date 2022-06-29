package pl.edu.wat.backend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.backend.dtos.PlayerDTO;
import pl.edu.wat.backend.entities.Player;
import pl.edu.wat.backend.services.PlayerService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping("players")
    public ResponseEntity<List<Player>> getPlayers() {

        return ResponseEntity.ok().body(playerService.getPlayers());
    }

    @GetMapping("players/{id}")
    public ResponseEntity <Player> getPlayerById(@PathVariable long id) {

        return ResponseEntity.ok().body(playerService.getPlayerById(id));
    }

    @PostMapping("players")
    public ResponseEntity<Player> addPlayer(@RequestBody PlayerDTO player) {

        return (ResponseEntity.ok().body(this.playerService.addPlayer(player)));
    }

    @PutMapping("players/{id}")
    public ResponseEntity<Player> editPlayer (@RequestBody Player player, @PathVariable long id){

        player.setId((int) id);
        return ResponseEntity.ok().body(this.playerService.editPlayer(player));
    }

    @DeleteMapping("players/{id}")
    public ResponseEntity<?> deletePlayer(@PathVariable long id) {
        this.playerService.deletePlayer(id);
        return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.OK);
    }
}
