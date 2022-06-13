package pl.edu.wat.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.backend.dtos.PlayerRequest;
import pl.edu.wat.backend.dtos.PlayerResponse;
import pl.edu.wat.backend.entities.Player;
import pl.edu.wat.backend.exceptions.ResourceNotFoundException;
import pl.edu.wat.backend.repositories.PlayerRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }

    @Override
    public List<PlayerResponse> getPlayers () {
        return StreamSupport.stream(playerRepository.findAll().spliterator(), false)
                .map(entity -> new PlayerResponse(entity.getName(), entity.getSurname(), entity.getHeight(), entity.getWeight()))
                .collect(Collectors.toList());
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
    public Player addPlayer (PlayerRequest request) {
        Player newPlayer = new Player();
        newPlayer.setId(0L);
        newPlayer.setName(request.getName());
        newPlayer.setSurname(request.getSurname());
        newPlayer.setHeight(request.getHeight());
        newPlayer.setWeight(request.getWeight());

        return playerRepository.save(newPlayer);
    }

    @Override
    public PlayerResponse editPlayer(Long id, PlayerRequest request) {
        return playerRepository.findById(id).map(player -> {
            player.setName(request.getName());
            player.setSurname(request.getSurname());
            player.setHeight(request.getHeight());
            player.setWeight(request.getWeight());
            Player p = playerRepository.save(player);
            return new PlayerResponse(p.getName(),p.getSurname(),p.getHeight(),p.getWeight());
        }).orElseThrow(()-> new ResourceNotFoundException("Nie znaleziono piłkarza o takim ID: "+ id));
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
