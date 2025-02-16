package techin.lt.cars.dto;

import techin.lt.cars.model.Car;

public class CarMapper {

    public static CarDTO toCarDTO(Car car) {
        return new CarDTO(
                car.getId(),
                car.getBrand(),
                car.getModel(),
                car.getYear(),
                car.getStatus()

        );
    }

    public static Car toCar(CarDTO carDTO) {
        Car car = new Car();
        car.setId(carDTO.id());
        car.setBrand(carDTO.brand());
        car.setModel(carDTO.model());
        car.setYear(carDTO.year());
        car.setStatus(carDTO.status() != null ? carDTO.status() : "AVAILABLE");
        return car;
    }
}
