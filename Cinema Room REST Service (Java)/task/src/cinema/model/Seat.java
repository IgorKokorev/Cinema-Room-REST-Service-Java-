package cinema.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Seat {
    private int row;
    private int column;
    private int price;
    @JsonIgnore
    private String token;
    @JsonIgnore
    private boolean isAvailable;

    public Seat(int row, int column) {
        this.row = row + 1;
        this.column = column + 1;

        if (this.row <= 4) this.price = 10;
        else this.price = 8;

        token = null;
        this.isAvailable = true;
    }
}
