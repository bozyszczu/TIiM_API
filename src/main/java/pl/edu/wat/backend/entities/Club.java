package pl.edu.wat.backend.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Getter
@Setter
@Table (name = "Clubs")

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
}