package techin.lt.cars.controller;

import org.springframework.web.bind.annotation.*;
import techin.lt.cars.model.Rental;
import techin.lt.cars.service.RentalService;

import java.util.List;

@RestController
@RequestMapping("/api/rental")
public class RentalController {
    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping
    public List<Rental> getAllRental() {
        return rentalService.getAllRental();
    }

    @PostMapping
    public Rental createRental(@RequestBody Rental rental) {
        return rentalService.saveRental(rental);
    }
}

