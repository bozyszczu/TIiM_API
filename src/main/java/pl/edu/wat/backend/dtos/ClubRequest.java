package pl.edu.wat.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.edu.wat.backend.entities.Player;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClubRequest {

    private String clubName;
    private String shortname;
    private String coach;
    private String stadium;
    private String webPage;
    private List<Player> players = new ArrayList<>();
}
