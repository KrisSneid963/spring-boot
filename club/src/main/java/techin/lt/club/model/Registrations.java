package techin.lt.club.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "registrations")
public class Registrations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "running_event_id", nullable = false)
    private RunningEvent runningEvent;

    @Column(nullable = false)
    private LocalDateTime registrationDate;

    public Registrations() {
    }

    public Registrations(User user, RunningEvent runningEvent, LocalDateTime registrationDate) {
        this.user = user;
        this.runningEvent = runningEvent;
        this.registrationDate = registrationDate;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public RunningEvent getRunningEvent() {
        return runningEvent;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setRunningEvent(RunningEvent runningEvent) {
        this.runningEvent = runningEvent;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }
}
