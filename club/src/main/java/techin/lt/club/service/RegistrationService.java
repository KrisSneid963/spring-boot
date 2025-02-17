package techin.lt.club.service;

import jakarta.servlet.Registration;
import org.springframework.stereotype.Service;

import techin.lt.club.model.Registrations;
import techin.lt.club.repository.RegistrationRepository;

import java.util.List;

@Service
public class RegistrationService {

    private final RegistrationRepository registrationRepository;

    public RegistrationService(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    public Registration saveRegistration(Registrations registration) {
        registrationRepository.save(registration);
        return (Registration) registration;
    }

    public List<Registrations> findAllRegistrations() {
        return registrationRepository.findAll();
    }

    public List<Registrations> findRegistrationsByUserId(Long userId) {
        return registrationRepository.findByUserId(userId);
    }
}
