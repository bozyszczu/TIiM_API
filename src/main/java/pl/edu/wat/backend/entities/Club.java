package pl.edu.wat.backend.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConstructorBinding;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Getter
@Setter
@Table (name = "Clubs")
@ConstructorBinding
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "ClubName")
    private String clubName;

    @Column(name = "ShortName")
    private String shortname;

    @Column(name = "Coach")
    private String coach;

    @Column(name = "Stadium")
    private String stadium;

    @Column(name = "WebPage")
    private String webPage;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn (name = "club_id", referencedColumnName = "id")
    private List <Player> players = new ArrayList<>();

    public Club (String clubName, String shortname, String coach, String stadium, String webPage) {
        this.clubName = clubName;
        this.shortname = shortname;
        this.coach = coach;
        this.stadium = stadium;
        this.webPage = webPage;
    }

    public Club () {

    }
}