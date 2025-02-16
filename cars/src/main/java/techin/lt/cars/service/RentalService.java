package techin.lt.cars.service;

import org.springframework.stereotype.Service;
import techin.lt.cars.model.Rental;
import techin.lt.cars.repository.RentalRepository;

import java.util.List;

@Service
public class RentalService {
    private final RentalRepository rentalRepository;

    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    public List<Rental> getAllRental() {
        return rentalRepository.findAll();
    }

    public Rental saveRental(Rental rental) {
        return rentalRepository.save(rental);
    }
}
