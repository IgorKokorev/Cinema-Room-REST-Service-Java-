package cinema.DTO;

import cinema.model.Seat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReturnedTicket {
    private Seat returned_ticket;
}
