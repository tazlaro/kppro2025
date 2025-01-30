package cz.uhk.kppro2025.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "clubs")
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL) // if a club is deleted, delete its address
    @JoinColumn(name = "address_id")
    @Valid // validate the address object as well (without this annotation, only the address id is validated)
    private Address address;

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL, orphanRemoval = true) // if a club is deleted, delete all its competitions
    private List<Competition> competitions;

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL, orphanRemoval = true) // if a club is deleted, delete all its users
    private List<User> users;

    @NotNull
    @Min(0)
    @Max(99999)
    private int number;
    @NotEmpty
    @Size(min = 3, max = 50)
    private String name;
    @Size(max = 255)
    private String description;
    @Email
    private String email;
//    @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "Invalid phone number")
    @Pattern(regexp = "^$|^\\+?[0-9. ()-]{7,25}$", message = "Invalid phone number") // empty or valid phone number
    private String phone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
