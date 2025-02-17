package techin.lt.club.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "running_events")
public class RunningEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(nullable = false)
    private String calendarDate;

    @Column(nullable = false, length = 200)
    private String location;

    private Integer maxParticipants;

    @OneToMany(mappedBy = "runningEvent", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<techin.lt.club.model.Registrations> registrations = new HashSet<>();

    public RunningEvent() {
    }

    public RunningEvent(String name, String calendarDate, String location, Integer maxParticipants) {
        this.name = name;
        this.calendarDate = calendarDate;
        this.location = location;
        this.maxParticipants = maxParticipants;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCalendarDate() {
        return calendarDate;
    }

    public String getLocation() {
        return location;
    }

    public Integer getMaxParticipants() {
        return maxParticipants;
    }

    public Set<techin.lt.club.model.Registrations> getRegistrations() {
        return registrations;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCalendarDate(String calendarDate) {
        this.calendarDate = calendarDate;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setMaxParticipants(Integer maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public void setRegistrations(Set<techin.lt.club.model.Registrations> registrations) {
        this.registrations = registrations;
    }
}
