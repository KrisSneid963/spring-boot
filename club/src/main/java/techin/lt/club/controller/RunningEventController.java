package techin.lt.club.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import techin.lt.club.model.RunningEvent;
import techin.lt.club.service.RunningEventService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/events")
public class RunningEventController {

    private final RunningEventService runningEventService;

    public RunningEventController(RunningEventService runningEventService) {
        this.runningEventService = runningEventService;
    }

    @GetMapping
    public List<RunningEvent> getAllEvents() {
        return runningEventService.findAllEvents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RunningEvent> getEventById(@PathVariable Long id) {
        return runningEventService.findEventById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<RunningEvent> getEventByName(@PathVariable String name) {
        return runningEventService.findEventByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public RunningEvent createEvent(@RequestBody RunningEvent event) {
        return runningEventService.saveEvent(event);
    }

    @PutMapping("/{id}")
    public RunningEvent updateEvent(@RequestBody RunningEvent event) {
        return runningEventService.updateEvent(event);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Long id) {
        runningEventService.deleteEvent(id);
    }
}
