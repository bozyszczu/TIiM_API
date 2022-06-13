package pl.edu.wat.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerRequest {

    private String name;
    private String surname;
    private float height;
    private float weight;
}
