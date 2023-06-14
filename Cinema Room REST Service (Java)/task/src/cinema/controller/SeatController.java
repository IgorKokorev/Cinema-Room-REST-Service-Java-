package cinema.controller;

import cinema.DTO.*;
import cinema.model.Seat;
import lombok.RequiredArgsConstructor;
import cinema.model.Cinema;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebInputException;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class SeatController {
    private final Cinema cinema;

    @GetMapping("/seats")
    public AvailableSeatsResponse getAvailableSeats() {
        return new AvailableSeatsResponse(cinema);
    }

    @PostMapping("/purchase")
    public Ticket purchaseSeat(@RequestBody PurchaseRequest request) {
        if (request.getRow() <= 0 || request.getRow() > cinema.getTotal_rows() ||
                request.getColumn() <= 0 || request.getColumn() > cinema.getTotal_columns()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The number of a row or a column is out of bounds!");
        }

        Seat seat = cinema.getSeats()[request.getRow() - 1][request.getColumn() - 1];
        if (!seat.isAvailable()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The ticket has been already purchased!");
        }

        seat.setAvailable(false);
        String token = UUID.randomUUID().toString();
        seat.setToken(token);

        return new Ticket(token, seat);
    }

    @PostMapping("/return")
    public ReturnedTicket returnTicket(@RequestBody Token token) {
        boolean isTicketFound = false;
        Seat seat = null;
        for (int row = 0; row < cinema.getTotal_rows(); row ++) {
            for (int column = 0; column < cinema.getTotal_columns(); column++) {
                seat = cinema.getSeats()[row][column];
                if (!seat.isAvailable() && seat.getToken().equals(token.getToken())) {
                    isTicketFound = true;
                    seat.setAvailable(true);
                    seat.setToken(null);
                    break;
                }
            }
            if (isTicketFound) break;
        }

        if (!isTicketFound) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong token!");
        return new ReturnedTicket(seat);
    }

    @PostMapping("/stats")
    public Stat setStat(@RequestParam Optional<String> password) {
        if (password.isEmpty() || !password.get().equals("super_secret"))
            throw new ServerWebInputException("The password is wrong!");

        return new Stat(cinema);
    }

}
