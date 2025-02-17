package techin.lt.club.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import techin.lt.club.model.RunningEvent;
import techin.lt.club.service.RunningEventService;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class RunningEventController {

    private final RunningEventService runningEventService;

    public RunningEventController(RunningEventService runningEventService) {
        this.runningEventService = runningEventService;
    }

    @GetMapping
    public ResponseEntity<List<RunningEvent>> getAllEvents() {
        List<RunningEvent> events = runningEventService.findAllEvents();
        return ResponseEntity.ok(events);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RunningEvent> getEventById(@PathVariable Long id) {
        return runningEventService.findEventById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
