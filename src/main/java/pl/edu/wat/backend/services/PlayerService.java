package pl.edu.wat.backend.services;

import pl.edu.wat.backend.dtos.PlayerRequest;
import pl.edu.wat.backend.dtos.PlayerResponse;
import pl.edu.wat.backend.entities.Player;
import java.util.List;


public interface PlayerService {

    List<PlayerResponse> getPlayers();
    Player getPlayerById(long playerId);
    Player addPlayer(PlayerRequest player);
    PlayerResponse editPlayer(Long id, PlayerRequest request);
    void deletePlayer(long id);
}
