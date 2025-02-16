package techin.lt.cats.dto;

import techin.lt.cats.model.Reservation;
import techin.lt.cats.model.User;

public class ReservationMapper {


    public static ReservationDTO toReservationDTO(Reservation reservation) {
        return new ReservationDTO(
                reservation.getId(),
                reservation.getDateOfReservation(),
                reservation.getTimeSlot(),
                reservation.getNumGuests(),
                reservation.getReservedBy().getId()
        );
    }


    public static Reservation toReservation(ReservationDTO reservationDTO, User reservedBy) {
        return new Reservation(
                reservedBy,
                reservationDTO.dateOfReservation(),
                reservationDTO.timeSlot(),
                reservationDTO.numGuests(),
                reservationDTO.id()
        );
    }
}
