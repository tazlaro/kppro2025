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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

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
            // default club for ADMIN and USER (club_id 1)
            addClub(0, "Club Default", "Club", "Club", "99999");
            // other clubs (club_id 2 - 8)
            addClub(192, "SSK Sopka Nová Paka", "Kumburská 846", "Nová Paka", "50901");
            addClub(197, "SSK Jičín", "Brada 35", "Jičín", "50601");
            addClub(214, "SSK Ostroměř", "Hradišťská 423", "Ostroměř", "50752");
            addClub(54, "SSK Třebeš", "V Mlejnku 592", "Hradec Králové 11", "50011");
            addClub(32, "SSK Bohemia Poděbrady", "Zátiší 1477", "Poděbrady", "20901");
            addClub(55, "SSK Loyd", "Střelecká 11", "Jablonec nad Nisou", "46601");
            addClub(200, "SSK Manušice", "Manušice 47", "Česká Lípa", "47111");
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
            addCompetition(sdf.parse("2025-03-08"), "SP", "Zmrzlý střelec", 7L);
            addCompetition(sdf.parse("2025-03-22"), "LM", "Jarní cena", 2L);
            addCompetition(sdf.parse("2025-03-29"), "MaO", "Malá odstřelovačka 1", 3L);
            addCompetition(sdf.parse("2025-04-20"), "VP", "Velikonoční pistole", 5L);
            addCompetition(sdf.parse("2025-05-01"), "VP", "Prvomájová pistole", 6L);
            addCompetition(sdf.parse("2025-08-09"), "HZ", "Perkusy", 5L);
            addCompetition(sdf.parse("2025-08-23"), "SP", "Rychlá pistole", 8L);
            addCompetition(sdf.parse("2025-10-11"), "LM", "Pohár Sopky", 4L);
            addCompetition(sdf.parse("2025-10-18"), "MaO", "Malá odstřelovačka 2",  3L);
            addCompetition(sdf.parse("2025-11-01"), "SP", "Podzimní soutěž", 2L);
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

            // default users ADMIN and USER (in default club) (user_id 1 = ADMIN, user_id 2 = USER)
            addUser("admin", "heslo", "ADMIN", "Admin", "Admin", sdf.parse("1990-01-01"), "Admin", "Admin", "99999", 1L);
            addUser("user", "heslo", "USER", "User", "User", sdf.parse("1990-01-01"), "User", "User", "99999", 1L);
            // other users (user_id 3 - 20)
            addUser("user1", "password1", "USER", "Jan", "Novák", getRandomBirthdate(), "Náměstí Míru 1", "Praha", "12000", 2L);
            addUser("user2", "password2", "USER", "Petr", "Svoboda", getRandomBirthdate(), "Vinohradská 12", "Brno", "60200", 2L);
            addUser("user3", "password3", "USER", "Stanislava", "Tázlarů", getRandomBirthdate(), "Masarykova 3", "Ostrava", "70200", 2L);
            addUser("user4", "password4", "USER", "Martin", "Černý", getRandomBirthdate(), "Smetanova 4", "Plzeň", "30100", 2L);
            addUser("user5", "password5", "USER", "Tomáš", "Procházka", getRandomBirthdate(), "Husova 5", "Liberec", "46001", 2L);
            addUser("user6", "password6", "USER", "Jiří", "Kučera", getRandomBirthdate(), "Jiráskova 6", "Olomouc", "77900", 3L);
            addUser("user7", "password7", "USER", "Jana", "Veselá", getRandomBirthdate(), "Komenského 7", "České Budějovice", "37001", 3L);
            addUser("user8", "password8", "USER", "Václav", "Horák", getRandomBirthdate(), "Palackého 8", "Hradec Králové", "50002", 3L);
            addUser("user9", "password9", "USER", "Karel", "Němec", getRandomBirthdate(), "Tylova 9", "Pardubice", "53002", 4L);
            addUser("user10", "password10", "USER", "Lukáš", "Marek", getRandomBirthdate(), "Štefánikova 10", "Zlín", "76001", 4L);
            addUser("user11", "password11", "USER", "Petra", "Králová", getRandomBirthdate(), "Karlova 11", "Praha", "12001", 4L);
            addUser("user12", "password12", "USER", "Vladimír", "Beneš", getRandomBirthdate(), "Jungmannova 12", "Brno", "60201", 5L);
            addUser("user13", "password13", "USER", "Radek", "Fiala", getRandomBirthdate(), "Havlíčkova 13", "Ostrava", "70201", 5L);
            addUser("user14", "password14", "USER", "Roman", "Krejčí", getRandomBirthdate(), "Nerudova 14", "Plzeň", "30101", 5L);
            addUser("user15", "password15", "USER", "Josef", "Dvořák", getRandomBirthdate(), "F. M. Hilmara 1751", "Nová Paka", "50901", 6L);
            addUser("user16", "password16", "USER", "Vojtěch", "Pokorný", getRandomBirthdate(), "Štefánikova 16", "Olomouc", "77901", 6L);
            addUser("user17", "password17", "USER", "Zdeněk", "Růžička", getRandomBirthdate(), "Křižíkova 17", "České Budějovice", "37002", 7L);
            addUser("user18", "password18", "USER", "Jaroslav", "Šimek", getRandomBirthdate(), "Vrchlického 18", "Hradec Králové", "50003", 7L);
            addUser("user19", "password19", "USER", "Tereza", "Urbanová", getRandomBirthdate(), "Kollárova 19", "Pardubice", "53003", 8L);
            addUser("user20", "password20", "USER", "Marek", "Vávra", getRandomBirthdate(), "Kounicova 20", "Zlín", "76002", 8L);
        };
    }

    private Date getRandomBirthdate() {
        try {
            long startMillis = new SimpleDateFormat("yyyy-MM-dd").parse("1950-01-01").getTime();
            long endMillis = new SimpleDateFormat("yyyy-MM-dd").parse("2010-12-31").getTime();
            long randomMillisSinceEpoch = ThreadLocalRandom.current().nextLong(startMillis, endMillis);
            return new Date(randomMillisSinceEpoch);
        } catch (ParseException e) {
            e.printStackTrace();
            return null; // or handle it in a way that fits your application logic
        }
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
            addResult(430, 0, "", 1L, 3L);
            addResult(564, 0, "", 1L, 5L);
            addResult(345, 0, "", 1L, 7L);
            addResult(289, 0, "", 1L, 8L);
            addResult(531, 0, "", 1L, 9L);
            addResult(297, 0, "", 1L, 12L);
            addResult(312, 0, "", 1L, 15L);

            addResult(298, 0, "", 2L, 4L);
            addResult(275, 0, "", 2L, 5L);
            addResult(263, 0, "", 2L, 6L);
            addResult(271, 0, "", 2L, 10L);
            addResult(286, 0, "", 2L, 11L);
            addResult(257, 0, "", 2L, 17L);

            addResult(548, 0, "", 3L, 3L);
            addResult(318, 0, "", 3L, 7L);
            addResult(578, 0, "", 3L, 9L);
            addResult(455, 0, "", 3L, 11L);
            addResult(574, 0, "", 3L, 14L);
            addResult(541, 0, "", 3L, 15L);
            addResult(399, 0, "", 3L, 18L);

            addResult(279, 0, "", 4L, 3L);
            addResult(246, 0, "", 4L, 7L);
            addResult(194, 0, "", 4L, 9L);
            addResult(272, 0, "", 4L, 11L);
            addResult(223, 0, "", 4L, 14L);
            addResult(244, 0, "", 4L, 15L);
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
