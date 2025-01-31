package cz.uhk.kppro2025;

import cz.uhk.kppro2025.model.*;
import cz.uhk.kppro2025.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class Kppro2025Application {

    private AddressService addressService;
    private CompetitionService competitionService;
    private UserService userService;
    private PasswordEncoder passwordEncoder;
    private ClubService clubService;
    private ResultService resultService;

    @Autowired
    public Kppro2025Application(UserService userService, PasswordEncoder passwordEncoder, ClubService clubService, AddressService addressService, CompetitionService competitionService, ResultService resultService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.clubService = clubService;
        this.addressService = addressService;
        this.competitionService = competitionService;
        this.resultService = resultService;
    }

//    @Bean
//    public CommandLineRunner demo() {
//        return args -> {
//            addUser("admin", "heslo", "ADMIN");
//            addUser("user", "heslo", "USER");
//        };
//    }
//
//    private void addUser(String username, String password, String role) {
//        if (userService.findByUsername(username) == null) {
//            User user = new User();
//            user.setUsername(username);
//            user.setPassword(passwordEncoder.encode(password));
//            user.setRole(role);
//            userService.save(user);
//        }
//    }


//    @Order(1)
//    @Bean
//    public CommandLineRunner demo() {
//        return args -> {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            try {
//                demoAddUser("admin", "heslo", "ADMIN", "Admin", "Admin", sdf.parse("1990-01-01"));
//                demoAddUser("user", "heslo", "USER", "User", "User", sdf.parse("1990-01-01"));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        };
//    }
//
//    private void demoAddUser(String username, String password, String role, String firstName, String lastName, Date birthday) {
//        if (userService.findByUsername(username) == null) {
//            User user = new User();
//            user.setUsername(username);
//            user.setPassword(passwordEncoder.encode(password));
//            user.setRole(role);
//            user.setFirstName(firstName);
//            user.setLastName(lastName);
//            user.setBirthday(birthday);
//            userService.save(user);
//        }
//    }

    @Order(2)
    @Bean
    public CommandLineRunner initClubs() {
        if (!clubService.getAllClubs().isEmpty()) {
            return args -> {};
        }
        return args -> {
            // default club for ADMIN and USER
            addClub(0, "Club Default", "Club", "Club", "99999");
            // other clubs
            addClub(1, "Club A", "123 Main St", "Springfield", "12345");
            addClub(2, "Club B", "456 Elm St", "Shelbyville", "67890");
            addClub(3, "Club C", "789 Oak St", "Capital City", "11223");
            addClub(4, "Club D", "101 Pine St", "Ogdenville", "33445");
            addClub(5, "Club E", "202 Maple St", "North Haverbrook", "55667");
        };
    }

    private void addClub(Integer number, String name, String street, String city, String zip) {
        Club club = new Club();
        club.setNumber(number);
        club.setName(name);

        Address address = new Address();
        address.setStreet(street);
        address.setCity(city);
        address.setZip(zip);

        club.setAddress(address);
        clubService.saveClub(club);
    }

    @Order(3)
    @Bean
    public CommandLineRunner initCompetitions() {
        if (!competitionService.getAllCompetitions().isEmpty()) {
            return args -> {};
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return args -> {
            addCompetition(sdf.parse("2025-01-01"), "VP", "Competition A", 1L);
            addCompetition(sdf.parse("2025-02-01"), "SP", "Competition B", 2L);
            addCompetition(sdf.parse("2025-03-01"), "SP", "Competition C", 3L);
            addCompetition(sdf.parse("2025-04-01"), "MaO", "Competition D", 4L);
            addCompetition(sdf.parse("2025-05-01"), "SP", "Competition E", 5L);
            addCompetition(sdf.parse("2025-06-01"), "HZ", "Competition F", 1L);
            addCompetition(sdf.parse("2025-07-01"), "LM", "Competition G", 2L);
            addCompetition(sdf.parse("2025-08-01"), "MaO", "Competition H", 3L);
            addCompetition(sdf.parse("2025-09-01"), "LM", "Competition I",  4L);
            addCompetition(sdf.parse("2025-10-01"), "SP", "Competition J", 5L);
        };
    }

    private void addCompetition(Date date, String discipline, String name, Long club_id) {
        Competition competition = new Competition();
        competition.setDate(date);
        competition.setDiscipline(discipline);

        competition.setClub(clubService.getClubById(club_id));

        competition.setName(name);
        competitionService.saveCompetition(competition);
    }

    @Order(4)
    @Bean
    public CommandLineRunner initUsers() {
//        if (!clubService.getAllClubs().isEmpty()) { return args -> {}; };
//        if (clubService.getAllClubs().size() > 2) { return args -> {}; };

//        return args -> {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            try {
//                for (int i = 1; i <= 20; i++) {
//                    addUser(
//                            "user" + i,
//                            "password" + i,
//                            "USER",
//                            "FirstName" + i,
//                            "LastName" + i,
//                            sdf.parse("1990-01-01"),
//                            "Street " + i,
//                            "City " + i,
//                            String.valueOf(10000 + i)
//                    );
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        };

        // user_id 1 = ADMIN, user_id 2 = USER
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return args -> {

            // default users ADMIN and USER (in default club)
            addUser("admin", "heslo", "ADMIN", "Admin", "Admin", sdf.parse("1990-01-01"), "Admin", "Admin", "99999", 1L);
            addUser("user", "heslo", "USER", "User", "User", sdf.parse("1990-01-01"), "User", "User", "99999", 1L);
            // other users
            addUser("user1", "password1", "USER", "FirstName1", "LastName1", new Date(), "Street 1", "City 1", "10001", 2L);
            addUser("user2", "password2", "USER", "FirstName2", "LastName2", new Date(), "Street 2", "City 2", "10002", 2L);
            addUser("user3", "password3", "USER", "FirstName3", "LastName3", new Date(), "Street 3", "City 3", "10003", 3L);
            addUser("user4", "password4", "USER", "FirstName4", "LastName4", new Date(), "Street 4", "City 4", "10004", 3L);
            addUser("user5", "password5", "USER", "FirstName5", "LastName5", new Date(), "Street 5", "City 5", "10005", 4L);
            addUser("user6", "password6", "USER", "FirstName6", "LastName6", new Date(), "Street 6", "City 6", "10006", 4L);
            addUser("user7", "password7", "USER", "FirstName7", "LastName7", new Date(), "Street 7", "City 7", "10007", 5L);
            addUser("user8", "password8", "USER", "FirstName8", "LastName8", new Date(), "Street 8", "City 8", "10008", 5L);
            addUser("user9", "password9", "USER", "FirstName9", "LastName9", new Date(), "Street 9", "City 9", "10009", 6L);
            addUser("user10", "password10", "USER", "FirstName10", "LastName10", new Date(), "Street 10", "City 10", "10010", 6L);
        };

    }

    private void addUser(String username, String password, String role, String firstName, String lastName, Date birthday, String street, String city, String zip, Long club_id) {
        if (userService.findByUsername(username) == null) {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setRole(role);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setBirthday(birthday);

            Address address = new Address();
            address.setStreet(street);
            address.setCity(city);
            address.setZip(zip);

            user.setClub(clubService.getClubById(club_id));

            user.setAddress(address);
            userService.saveUser(user);
        }
    }

    @Order(5)
    @Bean
    public CommandLineRunner initResults(ResultService resultService) {
        if (!resultService.getAllResults().isEmpty()) {
            return args -> {};
        }
        return args -> {
            addResult(0, 0, "", 1L, 3L);
            addResult(0, 0, "", 1L, 3L);
            addResult(0, 0, "", 1L, 4L);
            addResult(0, 0, "", 2L, 5L);
            addResult(0, 0, "", 2L, 6L);
        };
    }

    private void addResult(Integer scoreTotal, Integer rank, String performanceClass, Long competition_id, Long user_id) {
        Result result = new Result();
        result.setScoreTotal(scoreTotal);
        result.setRank(rank);
        result.setPerformanceClass(performanceClass);

        result.setCompetition(competitionService.getCompetitionById(competition_id));
        result.setUser(userService.getUserById(user_id));

        resultService.saveResult(result);
    }

    public static void main(String[] args) {
        SpringApplication.run(Kppro2025Application.class, args);
    }
}
