package techin.lt.club.controller;

import jakarta.servlet.Registration;
import org.springframework.web.bind.annotation.*;

import techin.lt.club.model.Registrations;
import techin.lt.club.service.RegistrationService;

import java.util.List;

@RestController
@RequestMapping("/registrations")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping
    public List<Registrations> getAllRegistrations() {
        return registrationService.findAllRegistrations();
    }

    @GetMapping("/user/{userId}")
    public List<Registrations> getRegistrationsByUserId(@PathVariable Long userId) {
        return registrationService.findRegistrationsByUserId(userId);
    }

    @PostMapping
    public Registration registerUser(@RequestBody Registration registration) {
        return registrationService.saveRegistration((Registrations) registration);
    }
}
