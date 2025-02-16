package techin.lt.cats.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "cat_adoptions")
public class CatAdoption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String catName;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private LocalDate applicationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User adopter;

    public CatAdoption() {
    }

    public CatAdoption(String catName, String status, LocalDate applicationDate, User adopter) {
        this.catName = catName;
        this.status = status;
        this.applicationDate = applicationDate;
        this.adopter = adopter;
    }

    public CatAdoption(Long id, @NotNull @Size(max = 60, message = "Maximum 60 characters") @Pattern(regexp = "^[A-Z][a-zA-Z\\s]*$", message = "Must start with an uppercase letter") String s, @NotNull String status, @NotNull LocalDate localDate, User adopter) {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }

    public User getAdopter() {
        return adopter;
    }

    public void setAdopter(User adopter) {
        this.adopter = adopter;
    }
}
