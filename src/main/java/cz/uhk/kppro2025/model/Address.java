package cz.uhk.kppro2025.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "address")
    private Club club;

    @OneToMany(mappedBy = "address")
    private List<Competition> competitions;

    @OneToMany(mappedBy = "address")
    private List<User> users;

    @NotBlank
    @Size(max = 100)
    private String street;
    @NotBlank
    @Size(max = 50)
    private String city;
    @NotBlank
    @Pattern(regexp = "\\d{5}", message = "Invalid ZIP code (5 digits)")
    private String zip;
    @Size(max = 50)
    private String country;

    // NOT working - 404 error - not used in the project
    // For URL redirection after saving the address
    // Field to store the URL to return to after saving the address. It is not stored in the database.
    @Transient
    private String returnUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public List<Competition> getCompetitions() {
        return competitions;
    }

    public void setCompetitions(List<Competition> competitions) {
        this.competitions = competitions;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }
}
