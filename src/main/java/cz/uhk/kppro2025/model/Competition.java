package cz.uhk.kppro2025.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "competitions")
public class Competition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "address_id")
//    private Address address;

    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;

    @OneToMany(mappedBy = "competition", cascade = CascadeType.ALL, orphanRemoval = true) // if a competition is deleted, delete all its results
    private List<Result> results;

//    @ManyToMany(mappedBy = "competitions")
//    private List<User> users;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    @NotEmpty
    @Size(max = 10)
    private String discipline;
    @NotEmpty
    @Size(max = 50)
    private String name;
    @Size(max = 255)
    private String description;
    private String proposition;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Address getAddress() {
//        return address;
//    }
//
//    public void setAddress(Address address) {
//        this.address = address;
//    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

//    public List<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(List<User> users) {
//        this.users = users;
//    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProposition() {
        return proposition;
    }

    public void setProposition(String proposition) {
        this.proposition = proposition;
    }
}
