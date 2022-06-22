package pl.edu.wat.backend.services;

import pl.edu.wat.backend.dtos.PlayerDTO;
import pl.edu.wat.backend.entities.Player;
import java.util.List;


public interface PlayerService {

    List<Player> getPlayers();
    Player getPlayerById(long playerId);
    Player addPlayer(PlayerDTO player);
    Player editPlayer(Player player);
    void deletePlayer(long id);
}
