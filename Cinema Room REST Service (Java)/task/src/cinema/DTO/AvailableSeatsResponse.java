package cinema.DTO;

import lombok.Getter;
import lombok.Setter;
import cinema.model.Cinema;
import cinema.model.Seat;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AvailableSeatsResponse {
    private int total_rows;
    private int total_columns;
    private List<Seat> available_seats;

    public AvailableSeatsResponse(Cinema cinema) {
        this.total_rows = cinema.getTotal_rows();
        this.total_columns = cinema.getTotal_columns();
        this.available_seats = new ArrayList<>();

        for (int i = 0; i < total_rows; i++) {
            for (int j = 0; j < total_columns; j++) {
                if (cinema.getSeats()[i][j].isAvailable())
                    available_seats.add(cinema.getSeats()[i][j]);
            }
        }
    }

}
