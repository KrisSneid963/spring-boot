package techin.lt.cars.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import techin.lt.cars.model.Car;
import techin.lt.cars.service.CarService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    public List<Car> getAvailableCars() {
        return carService.getAvailableCars();
    }

    @GetMapping("/model/{model}")
    public ResponseEntity<List<Car>> getCarsByModel(@PathVariable String model) {
        List<Car> cars = carService.getCarsByModel(model);
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/brand/{brand}/model/{model}")
    public ResponseEntity<List<Car>> getCarsByBrandAndModel(@PathVariable String brand, @PathVariable String model) {
        List<Car> cars = carService.getCarsByBrandAndModel(brand, model);
        return ResponseEntity.ok(cars);
    }
}
