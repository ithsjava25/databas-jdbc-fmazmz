package com.example;

import com.example.domain.MissionService;
import com.example.domain.UserService;
import com.example.domain.dto.PasswordUpdateRequest;
import com.example.domain.dto.UserCreationRequest;
import com.example.domain.dto.UserDTOMapper;
import com.example.domain.model.User;
import com.example.domain.repository.UserRepository;
import com.example.jdbc.ConnectionManager;
import com.example.jdbc.MissionJdbcService;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.Console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

import static java.lang.Integer.valueOf;

public class Main {

    static void main(String[] args) {
        if (isDevMode(args)) {
            DevDatabaseInitializer.start();
        }
        new Main().run();
    }

    public void run() {
        // Resolve DB settings with precedence: System properties -> Environment variables
        String jdbcUrl = resolveConfig("APP_JDBC_URL", "APP_JDBC_URL");
        String dbUser = resolveConfig("APP_DB_USER", "APP_DB_USER");
        String dbPass = resolveConfig("APP_DB_PASS", "APP_DB_PASS");

        if (jdbcUrl == null || dbUser == null || dbPass == null) {
            throw new IllegalStateException(
                    "Missing DB configuration. Provide APP_JDBC_URL, APP_DB_USER, APP_DB_PASS " +
                            "as system properties (-Dkey=value) or environment variables.");
        }

        // Singleton ConnectionManager
        ConnectionManager.init(jdbcUrl, dbUser, dbPass);

        // Start Spring application and get Spring beans
        ConfigurableApplicationContext ctx = SpringApplication.run(AppConfig.class);
        UserRepository userRepo = ctx.getBean(UserRepository.class);
        MissionService missionService = ctx.getBean(MissionService.class);
        UserService userService = ctx.getBean(UserService.class);
        PasswordEncoder passwordEncoder = ctx.getBean(PasswordEncoder.class);

        // @kappsegla
        // Example JDBC implementation for MoonMissions (for learning and for requirement purposes)
        // MissionJdbcService missionJdbcService = ctx.getBean(MissionJdbcService.class);
        // System.out.println(missionJdbcService.getAllMissions());


        Scanner scanner = new Scanner(System.in);

        // Login flow (required by tests)
        System.out.println("username:");
        String username = scanner.nextLine();

        System.out.println("password:");
        String password = scanner.nextLine();

        Optional<User> loginUser = userRepo.findByName(username)
                .filter(u -> passwordEncoder.matches(password, u.getPassword()));

        if (loginUser.isEmpty()) {
            System.out.println("Invalid username or password");
            System.out.println("Press 0 to exit...");
            String input = scanner.nextLine();
            if (input.equals("0")) {
                return;
            }
            return;
        }

        boolean running = true;

        while (running) {
            System.out.println("-----------------------------------");
            System.out.println("1) List moon missions");
            System.out.println("2) Get mission by id");
            System.out.println("3) Count missions by year");
            System.out.println("4) Create account");
            System.out.println("5) Update (logged-in) account password");
            System.out.println("6) Delete account");
            System.out.println("0) Exit");
            System.out.println("-----------------------------------");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {

                case "1" -> {
                    var missions = missionService.getAllMissions();

                    System.out.println();
                    System.out.println("==============================================");
                    System.out.printf("%-20s %-6s %-10s%n", "Spacecraft", "Year", "Outcome");
                    System.out.println("==============================================");

                    missions.forEach(m -> {
                        System.out.printf(
                                "%-20s %-6d %-10s%n",
                                m.spacecraft(),
                                m.launchDate().getYear(),
                                m.outcome()
                        );
                    });

                    System.out.println("==============================================");
                    System.out.println();
                }

                case "2" -> {
                    System.out.print("Mission id: ");
                    Integer id = valueOf(scanner.nextLine());
                    missionService.getMissionById(id)
                            .ifPresentOrElse(m -> {
                                System.out.println("\n=== Mission Details ===");
                                System.out.println("Spacecraft:   " + m.spacecraft());
                                System.out.println("Launch date:  " + m.launchDate());
                                System.out.println("Rocket:       " + m.carrierRocket());
                                System.out.println("Operator:     " + m.operator());
                                System.out.println("Type:         " + m.missionType());
                                System.out.println("Outcome:      " + m.outcome());
                                System.out.println("========================\n");
                            }, () -> System.out.println("Mission not found\n"));
                }

                case "3" -> {
                    System.out.print("Year: ");
                    int year = Integer.parseInt(scanner.nextLine());
                    long count = missionService.totalMissionsByYear(year);
                    System.out.println(count + " missions in " + year);
                }

                case "4" -> {
                    System.out.print("First name: ");
                    String fn = scanner.nextLine();

                    System.out.print("Last name: ");
                    String ln = scanner.nextLine();

                    System.out.print("SSN: ");
                    String ssn = scanner.nextLine();

                    System.out.print("Password: ");
                    String pw = scanner.nextLine();

                    userService.createUser(new UserCreationRequest(
                            fn, ln, ssn, pw
                    ));
                    System.out.println("Account created");
                }

                case "5" -> {
                    System.out.print("User id: ");
                    Integer id = valueOf(scanner.nextLine());

                    System.out.print("New password: ");
                    String newPassword = scanner.nextLine();

                    userService.updateUserPassword(new PasswordUpdateRequest(
                            id, newPassword
                    ));
                    System.out.println("Password updated");
                }

                case "6" -> {
                    System.out.print("User id: ");
                    Integer id = valueOf(scanner.nextLine());

                    userService.deleteUser(id);
                    System.out.println("User deleted");
                }

                case "0" -> running = false;

                default -> System.out.println("Invalid option");
            }
        }

        ctx.close();

    }

    /**
     * Determines if the application is running in development mode based on system properties,
     * environment variables, or command-line arguments.
     *
     * @param args an array of command-line arguments
     * @return {@code true} if the application is in development mode; {@code false} otherwise
     */
    private static boolean isDevMode(String[] args) {
        if (Boolean.getBoolean("devMode"))  //Add VM option -DdevMode=true
            return true;
        if ("true".equalsIgnoreCase(System.getenv("DEV_MODE")))  //Environment variable DEV_MODE=true
            return true;
        return Arrays.asList(args).contains("--dev"); //Argument --dev
    }

    /**
     * Reads configuration with precedence: Java system property first, then environment variable.
     * Returns trimmed value or null if neither source provides a non-empty value.
     */
    private static String resolveConfig(String propertyKey, String envKey) {
        String v = System.getProperty(propertyKey);
        if (v == null || v.trim().isEmpty()) {
            v = System.getenv(envKey);
        }
        return (v == null || v.trim().isEmpty()) ? null : v.trim();
    }
}
