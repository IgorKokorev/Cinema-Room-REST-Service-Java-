package cinema.DTO;

import cinema.model.Cinema;
import cinema.model.Seat;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Stat {
    private int current_income;
    private int number_of_available_seats;
    private int number_of_purchased_tickets;

    public Stat(Cinema cinema) {
        current_income = 0;
        number_of_available_seats = 0;
        number_of_purchased_tickets = 0;

        for (int row = 0; row < cinema.getTotal_rows(); row++) {
            for (int column = 0; column < cinema.getTotal_columns(); column++) {
                Seat seat = cinema.getSeats()[row][column];
                if (seat.isAvailable()) {
                    number_of_available_seats++;
                } else {
                    number_of_purchased_tickets++;
                    current_income += seat.getPrice();
                }
            }
        }
    }
}
