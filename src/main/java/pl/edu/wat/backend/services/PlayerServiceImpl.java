package pl.edu.wat.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.backend.dtos.PlayerDTO;
import pl.edu.wat.backend.entities.Player;
import pl.edu.wat.backend.exceptions.ResourceNotFoundException;
import pl.edu.wat.backend.repositories.PlayerRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    PlayerRepository playerRepository;

    @Override
    public List<Player> getPlayers () { return this.playerRepository.findAll();
    }

    @Override
    public Player getPlayerById(long playerId) {

        Optional<Player> playerDb = this.playerRepository.findById(playerId);
        if (playerDb.isPresent()){
            return playerDb.get();
        } else {
            throw new ResourceNotFoundException("Nie znaleziono piłkarza o takim ID" + playerId);
        }
    }

    @Override
    public Player addPlayer (PlayerDTO request) {
        Player newPlayer = new Player();
        newPlayer.setId(0L);
        newPlayer.setName(request.getName());
        newPlayer.setSurname(request.getSurname());
        newPlayer.setHeight(request.getHeight());
        newPlayer.setWeight(request.getWeight());

        return playerRepository.save(newPlayer);
    }

    @Override
    public Player editPlayer(Player player) {
            Optional<Player> playerDb = this.playerRepository.findById(player.getId());

            if(playerDb.isPresent()) {
                Player editedPlayer = playerDb.get();
            editedPlayer.setName(player.getName());
            editedPlayer.setSurname(player.getSurname());
            editedPlayer.setHeight(player.getHeight());
            editedPlayer.setWeight(player.getWeight());
            playerRepository.save(player);
            return editedPlayer;
        }else{
               throw  new ResourceNotFoundException("Nie znaleziono piłkarza o takim ID: "+ player.getId());
            }
    }

    @Override
    public void deletePlayer(long playerId) {

        Optional<Player> playerDb = this.playerRepository.findById(playerId);
        if (playerDb.isPresent()){
            this.playerRepository.delete(playerDb.get());
        } else {
            throw new ResourceNotFoundException("Nie znaleziono piłkarza o takim ID" + playerDb);
        }
    }
}
