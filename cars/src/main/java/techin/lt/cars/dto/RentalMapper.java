package techin.lt.cars.dto;

import techin.lt.cars.model.Car;
import techin.lt.cars.model.Rental;
import techin.lt.cars.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class RentalMapper {

    public static List<RentalDTO> toRentalDTOList(List<Rental> rental) {
        return rental.stream()
                .map(RentalMapper::toRentalDTO)
                .collect(Collectors.toList());
    }

    public static RentalDTO toRentalDTO(Rental rental) {
        return new RentalDTO(
                rental.getId(),
                rental.getCar().getId(),
                rental.getUser().getId(),
                rental.getRentalStart(),
                rental.getRentalEnd(),
                rental.getPrice()
        );
    }

    public static Rental toRental(RentalDTO rentalDTO) {
        Rental rental = new Rental();
        rental.setId(rentalDTO.id());


        Car car = new Car();
        car.setId(rentalDTO.carId());
        rental.setCar(car);

        User user = new User();
        user.setId(rentalDTO.userId());
        rental.setUser(user);

        rental.setRentalStart(rentalDTO.rentalStart());
        rental.setRentalEnd(rentalDTO.rentalEnd());
        rental.setPrice(rentalDTO.price());
        return rental;
    }
}
