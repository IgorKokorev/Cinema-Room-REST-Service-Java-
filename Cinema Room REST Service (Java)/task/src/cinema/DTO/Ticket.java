package cinema.DTO;

import cinema.model.Seat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class Ticket {
    private String token;
    private Seat ticket;
}
