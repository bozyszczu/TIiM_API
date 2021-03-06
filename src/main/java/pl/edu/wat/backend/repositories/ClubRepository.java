package pl.edu.wat.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wat.backend.entities.Club;

@Repository
public interface ClubRepository extends JpaRepository <Club, Long> {

}
