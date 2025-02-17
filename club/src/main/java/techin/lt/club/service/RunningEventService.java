package techin.lt.club.service;

import org.springframework.stereotype.Service;
import techin.lt.club.model.RunningEvent;
import techin.lt.club.repository.RunningEventRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RunningEventService {

    private final RunningEventRepository runningEventRepository;

    public RunningEventService(RunningEventRepository runningEventRepository) {
        this.runningEventRepository = runningEventRepository;
    }

    public RunningEvent saveEvent(RunningEvent event) {
        return runningEventRepository.save(event);
    }

    public List<RunningEvent> findAllEvents() {
        return runningEventRepository.findAll();
    }

    public Optional<RunningEvent> findEventByName(String name) {
        return runningEventRepository.findByName(name);
    }


    public Optional<RunningEvent> findEventById(Long id) {
        return runningEventRepository.findById(id);
    }

    public RunningEvent updateEvent(RunningEvent updatedEvent) {
        return runningEventRepository.save(updatedEvent);
    }

    public void deleteEvent(Long id) {
        runningEventRepository.deleteById(id);
    }
}
