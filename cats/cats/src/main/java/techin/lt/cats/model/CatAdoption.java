package techin.lt.cats.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "cat_adoptions")
public class CatAdoption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String catName;
    private String status;
    private LocalDate applicationDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public CatAdoption() {
    }

    public CatAdoption(String catName, String status, LocalDate applicationDate, User user) {
        this.catName = catName;
        this.status = status;
        this.applicationDate = applicationDate;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
