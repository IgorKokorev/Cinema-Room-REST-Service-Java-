package cinema.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class Cinema {
    private int total_rows = 9;
    private int total_columns = 9;
    private Seat[][] seats = new Seat[total_rows][total_columns];

    Cinema() {
        for (int i = 0; i < total_rows; i++) {
            for (int j = 0; j < total_columns; j++) {
                seats[i][j] = new Seat(i, j);
            }
        }
    }

}
