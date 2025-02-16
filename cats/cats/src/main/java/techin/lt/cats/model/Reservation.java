package techin.lt.cats.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private User user;
    private LocalDate dateOfReservation;
    private String timeSlot;
    private int numGuests;


    public Reservation(User user, LocalDate dateOfReservation, String timeSlot, int numGuests, long id) {
        this.user = user;
        this.dateOfReservation = dateOfReservation;
        this.timeSlot = timeSlot;
        this.numGuests = numGuests;
        this.id = id;
    }

    public User getReservedBy() {
        return this.user;
    }
}
