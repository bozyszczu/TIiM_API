package pl.edu.wat.backend.dtos;

import lombok.Data;
import pl.edu.wat.backend.entities.Player;

import java.util.ArrayList;
import java.util.List;

@Data
public class ClubDTO {

    private String clubName;
    private String shortname;
    private String coach;
    private String stadium;
    private String webPage;
    private List<Player> players = new ArrayList<>();
}
