package pl.edu.wat.backend.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Data
@Entity
@Getter
@Setter
@Table (name = "Players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Surname")
    private String surname;

    @Column(name = "Height")
    private float height;

    @Column(name = "Weight")
    private float weight;

    public Player () {

    }
    public Player (String name, String surname, float height, float weight) {
        this.name = name;
        this.surname = surname;
        this.height = height;
        this.weight = weight;
    }
}
