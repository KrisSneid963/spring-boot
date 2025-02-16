package techin.lt.cats.service;

import org.springframework.stereotype.Service;
import techin.lt.cats.dto.ReservationDTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {

    private List<ReservationDTO> reservationList = new ArrayList<>();

    public ReservationDTO createReservation(ReservationDTO reservationDTO) {
        Long generatedId = (long) (reservationList.size() + 1);
        ReservationDTO newReservationDTO = new ReservationDTO(
                generatedId,
                reservationDTO.dateOfReservation(),
                reservationDTO.timeSlot(),
                reservationDTO.numGuests(),
                reservationDTO.reservedById()
        );

        reservationList.add(newReservationDTO);
        return newReservationDTO;
    }

    public List<ReservationDTO> getUserReservations() {
        return reservationList;
    }
}
