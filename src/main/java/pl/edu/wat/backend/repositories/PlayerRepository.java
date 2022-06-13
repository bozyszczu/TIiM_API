package pl.edu.wat.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wat.backend.entities.Player;

@Repository
public interface PlayerRepository extends JpaRepository <Player, Long> {
    Player getPlayerById (long playerId);
}
