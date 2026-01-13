import java.util.Scanner;

import Logic.MatchImportance;
import Logic.UsersRole;
import Logic.Management.CompetitionManager;
import Logic.Person.PersonSubClasses.Arbitre;
import Logic.Sport.Equipe;
import Logic.Sport.Stade;
import Logic.Sport.TimeHandler.Creneau;
import Logic.storedUsers.AuthService;
import Main.CANService;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class Entery {

    private static CANService canService = new CANService(new CompetitionManager());
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        System.out.println("=== Welcome to CAN Management System ===");

        // Login first
        login();

        // Main menu loop
        boolean exit = false;
        while (!exit) {
            showMenu();
            System.out.print("Choose an option: ");
            String input = scanner.nextLine();

            try {
                exit = handleMenuChoice(input);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

            System.out.println();
        }

        System.out.println("Goodbye!");
    }

    private static void login() throws IOException {
        while (true) {
            System.out.print("Username: ");
            String username = scanner.nextLine();

            System.out.print("Password: ");
            String password = scanner.nextLine();

            try {
                canService.currentUser = AuthService.login(username, password);
                if (canService.currentUser != null) {
                    System.out.println("Login successful! Role: " + canService.currentUser.role);
                    break;
                } else {
                    System.out.println("Invalid username or password. Try again.");
                }
            } catch (IOException e) {
                System.out.println("Error reading users file: " + e.getMessage());
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n--- Main Menu ---");
        if (canService.currentUser.role == UsersRole.ORGANISATEUR) {
            System.out.println("1. Ajouter équipe / joueur");
            System.out.println("2. Ajouter stade");
            System.out.println("3. Ajouter arbitre / organisateur");
            System.out.println("4. Programmer un match");
            System.out.println("5. Annuler un match");
            System.out.println("6. Afficher calendrier");
            System.out.println("7. Rapports");
            System.out.println("8. Quitter");
        } else {
            System.out.println("1. Afficher calendrier");
            System.out.println("2. Quitter");
        }
    }

    private static boolean handleMenuChoice(String input) throws Exception {
        if (canService.currentUser.role == UsersRole.ORGANISATEUR) {
            switch (input) {
                case "1":
                    addTeamOrPlayer();
                    break;
                case "2":
                    addStadium();
                    break;
                case "3":
                    addRefereeOrOrganizer();
                    break;
                case "4":
                    programMatch();
                    break;
                case "5":
                    cancelMatch();
                    break;
                case "6":
                    showCalendar();
                    break;
                case "7":
                    showReports();
                    break;
                case "8":
                    return true;
                default:
                    System.out.println("Invalid option");
            }
        } else {
            switch (input) {
                case "1":
                    showCalendar();
                    break;
                case "2":
                    return true;
                default:
                    System.out.println("Invalid option");
            }
        }
        return false;
    }

    // ==== Organizer actions ====

    private static void addTeamOrPlayer() {
        System.out.println("Add Team or Player");
        System.out.print("Do you want to add a Team or a Player? (T/P): ");
        String choice = scanner.nextLine().toUpperCase();

        try {
            if (choice.equals("T")) {
                // Add Team
                System.out.print("Enter team name: ");
                String teamName = scanner.nextLine();
                canService.addTeam(teamName);
                System.out.println("Team added successfully!");
            } else if (choice.equals("P")) {
                // Add Player
                System.out.print("Enter player name: ");
                String playerName = scanner.nextLine();
                System.out.print("Enter nationality: ");
                String nationality = scanner.nextLine();
                System.out.print("Enter placement (GOALKEEPER, STRIKER, WINGER, etc.): ");
                String placement = scanner.nextLine();
                System.out.print("Enter team name: ");
                String teamName = scanner.nextLine();

                canService.addPlayer(playerName, nationality, placement, teamName);
                System.out.println("Player added successfully!");
            } else {
                System.out.println("Invalid choice.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void addStadium() {
        System.out.println("Add Stadium");

        try {
            System.out.print("Enter stadium name: ");
            String name = scanner.nextLine();
            System.out.print("Enter city: ");
            String city = scanner.nextLine();
            System.out.print("Enter capacity: ");
            int capacity = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter category: ");
            String category = scanner.nextLine();

            canService.addStadium(name, city, capacity, category);
            System.out.println("Stadium added successfully!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void addRefereeOrOrganizer() {
        System.out.println("Add Referee or Organizer");
        System.out.print("Do you want to add a Referee or Organizer? (R/O): ");
        String choice = scanner.nextLine().toUpperCase();

        try {
            System.out.print("Enter name: ");
            String name = scanner.nextLine();
            System.out.print("Enter nationality: ");
            String nationality = scanner.nextLine();

            if (choice.equals("R")) {
                canService.addReferee(name, nationality);
                System.out.println("Referee added successfully!");
            } else if (choice.equals("O")) {
                canService.addOrganizer(name, nationality);
                System.out.println("Organizer added successfully!");
            } else {
                System.out.println("Invalid choice.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void programMatch() {
        System.out.println("Program Match");

        try {
            // Teams
            System.out.print("Enter Team A name: ");
            String teamAName = scanner.nextLine();
            System.out.print("Enter Team B name: ");
            String teamBName = scanner.nextLine();

            Equipe teamA = canService.manager.findTeamByName(teamAName);
            Equipe teamB = canService.manager.findTeamByName(teamBName);

            if (teamA == null || teamB == null) {
                System.out.println("One or both teams not found!");
                return;
            }

            // Stadium
            System.out.print("Enter stadium name: ");
            String stadiumName = scanner.nextLine();
            Stade stade = null;
            for (Stade s : canService.manager.allStades) {
                if (s.getNom().equalsIgnoreCase(stadiumName)) {
                    stade = s;
                    break;
                }
            }
            if (stade == null) {
                System.out.println("Stadium not found!");
                return;
            }

            // Referee
            System.out.print("Enter referee name: ");
            String refName = scanner.nextLine();
            Arbitre arbitre = null;
            for (Arbitre a : canService.manager.allArbitres) {
                if (a.getNom().equalsIgnoreCase(refName)) {
                    arbitre = a;
                    break;
                }
            }
            if (arbitre == null) {
                System.out.println("Referee not found!");
                return;
            }

            // Creneau
            System.out.print("Enter match date (YYYY-MM-DD): ");
            LocalDate date = LocalDate.parse(scanner.nextLine());
            System.out.print("Enter match start hour (HH:mm): ");
            String timeStr = scanner.nextLine();
            LocalTime time = LocalTime.parse(timeStr);
            System.out.print("Enter match duration in minutes: ");
            int duration = Integer.parseInt(scanner.nextLine());

            Creneau creneau = new Creneau(date, time, duration);

            // Importance
            System.out.print("Enter match importance (GROUP, ROUND_OF_16, QUARTER_FINAL, SEMI_FINAL, FINAL): ");
            MatchImportance importance = MatchImportance.valueOf(scanner.nextLine().toUpperCase());

            // Program the match
            canService.programMatch(teamA, teamB, stade, arbitre, creneau, importance);
            System.out.println("Match programmed successfully!");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void cancelMatch() {
        System.out.println("Cancel Match");

        try {
            System.out.print("Enter match ID to cancel: ");
            int matchId = Integer.parseInt(scanner.nextLine());
            canService.annulerMatch(matchId);
            System.out.println("Match cancelled successfully!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ==== All users actions ====

    private static void showCalendar() {
        System.out.println("Show Calendar");
        System.out.print("Filter by (1-Date, 2-Stadium, 3-Team, 4-All): ");
        String choice = scanner.nextLine();
        try {
            switch (choice) {
                case "1":
                    System.out.print("Enter date (YYYY-MM-DD): ");
                    LocalDate date = LocalDate.parse(scanner.nextLine());
                    canService.afficherCalendrierParDate(date);
                    break;
                case "2":
                    System.out.print("Enter stadium name: ");
                    int stadeId = Integer.parseInt(scanner.nextLine());
                    canService.afficherCalendrierParStade(stadeId);
                    break;
                case "3":
                    System.out.print("Enter team name: ");
                    int teamId = Integer.parseInt(scanner.nextLine());
                    canService.afficherCalendrierParEquipe(teamId);
                    break;
                case "4":
                    System.out.print("Enter the Referee name: ");
                    int refereeId = Integer.parseInt(scanner.nextLine());
                    canService.afficherCalendrierParArbitre(refereeId);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format");
        }
    }

    private static void showReports() {
        System.out.println("Show Reports");
        canService.generateReports();
    }

}
