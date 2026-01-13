package Main;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

import Logic.MatchImportance;
import Logic.MatchState;
import Logic.SoccerPosition;
import Logic.UsersRole;
import Logic.Exceptions.ConflitCalendrierException;
import Logic.Exceptions.DonneeInvalideException;
import Logic.Exceptions.DroitsInsuffisantsException;
import Logic.Exceptions.DuplicateRefereeException;
import Logic.Exceptions.EquipeAlreadyExistsException;
import Logic.Exceptions.EquipeNotFoundException;
import Logic.Exceptions.ExceededNumberOfPlayersException;
import Logic.Exceptions.MatchInvalideException;
import Logic.Exceptions.StadiumAlreadyExistsException;
import Logic.Management.CompetitionManager;
import Logic.Person.PersonSubClasses.Arbitre;
import Logic.Person.PersonSubClasses.Joueur;
import Logic.Person.PersonSubClasses.Origanisateur;
import Logic.Sport.Equipe;
import Logic.Sport.Match;
import Logic.Sport.Stade;
import Logic.Sport.DateHandler.CalendrierCAN;
import Logic.Sport.TimeHandler.Creneau;
import Logic.storedUsers.AuthService;
import Logic.storedUsers.User;

public class CANService {

    public final CompetitionManager manager;
    CalendrierCAN calendar;
    public User currentUser;

    public CANService(CompetitionManager manager) {
        this.manager = manager;
        this.calendar = new CalendrierCAN(manager);

    }

    /*
     * public boolean start() {
     * 
     * Scanner sc = new Scanner(System.in);
     * 
     * while (true) {
     * System.out.print("Username: ");
     * String username = sc.nextLine();
     * 
     * System.out.print("Password: ");
     * String password = sc.nextLine();
     * 
     * return login(username, password);
     * }
     * 
     * }
     * 
     * public boolean login(String username, String password) {
     * try {
     * User user = AuthService.login(username, password);
     * 
     * if (user == null) {
     * return false;
     * }
     * 
     * this.currentUser = user;
     * return true;
     * 
     * } catch (IOException e) {
     * System.out.println("Error reading users file");
     * return false;
     * }
     * }
     */

    public void addTeam(String name)
            throws EquipeAlreadyExistsException, DonneeInvalideException, DroitsInsuffisantsException {
        if (currentUser.role != UsersRole.ORGANISATEUR) {
            throw new DroitsInsuffisantsException("You are not allowed to call this method");
        }
        // Checking if there is a team with the same name
        boolean theCheck = manager.checkDuplicateTeam(name);
        if (!theCheck) {
            Equipe e = new Equipe(name);
            manager.addTeam(e);
        } else {
            throw new EquipeAlreadyExistsException("A Team already go the name you entered");
        }
    }

    public void addPlayer(String name, String nationality, String placement, String teamName)
            throws DonneeInvalideException, EquipeNotFoundException, ExceededNumberOfPlayersException,
            DroitsInsuffisantsException {

        if (currentUser.role != UsersRole.ORGANISATEUR) {
            throw new DroitsInsuffisantsException("You are not allowed to call this method");
        }
        Joueur createdPlayer;

        /*
         * We could replace the below switch with this built in enum methods:
         * -> SoccerPosition position;
         * -> position = SoccerPosition.valueOf(placement.toUpperCase().replace(" ",
         * "_"));
         * This will take the value entered by the user and generate the enum value of
         * it, and we could avoid the Switch Case
         */

        switch (placement) {
            case "GOALKEEPER":
                createdPlayer = new Joueur(name, nationality, SoccerPosition.GOALKEEPER);
                break;
            case "CENTER BACK":
                createdPlayer = new Joueur(name, nationality, SoccerPosition.CENTER_BACK);
                break;
            case "LEFT BACK":
                createdPlayer = new Joueur(name, nationality, SoccerPosition.LEFT_BACK);
                break;
            case "RIGHT BACK":
                createdPlayer = new Joueur(name, nationality, SoccerPosition.RIGHT_BACK);
                break;
            case "DEFENSIVE MIDFIELDER":
                createdPlayer = new Joueur(name, nationality, SoccerPosition.DEFENSIVE_MIDFIELDER);
                break;
            case "CENTRAL MIDFIELDER":
                createdPlayer = new Joueur(name, nationality, SoccerPosition.CENTRAL_MIDFIELDER);
                break;
            case "ATTACKING MIDFIELDER":
                createdPlayer = new Joueur(name, nationality, SoccerPosition.ATTACKING_MIDFIELDER);
                break;
            case "WINGER":
                createdPlayer = new Joueur(name, nationality, SoccerPosition.WINGER);
                break;
            case "STRIKER":
                createdPlayer = new Joueur(name, nationality, SoccerPosition.STRIKER);
                break;
            default:
                throw new DonneeInvalideException("You didn't enter the placement correctly");
        }

        Equipe teamSelected = manager.findTeamByName(teamName);

        if (teamSelected == null) {
            throw new EquipeNotFoundException("There is no team with the name you entered in the system" + teamName); // Better
                                                                                                                      // practice
                                                                                                                      // to
                                                                                                                      // show
                                                                                                                      // the
                                                                                                                      // teamName,
                                                                                                                      // if
                                                                                                                      // not
                                                                                                                      // it
                                                                                                                      // is
                                                                                                                      // called
                                                                                                                      // "NULL"
        }

        teamSelected.addPlayer(createdPlayer);
    }

    // We will see, if we want to add capacity threshold..
    public void addStadium(String name, String city, int capacite, String categorie)
            throws StadiumAlreadyExistsException, DroitsInsuffisantsException {

        if (currentUser.role != UsersRole.ORGANISATEUR) {
            throw new DroitsInsuffisantsException("You are not allowed to call this method");
        }
        Stade stadium = null;

        boolean isExists = manager.stadeExistanceCheck(name, city); // Always handle operations on collections at the
                                                                    // class where these lives in

        if (isExists) {
            throw new StadiumAlreadyExistsException("This stadium already exits for this city: " + city);
        }

        stadium = new Stade(name, city, capacite, categorie);
        manager.addStadium(stadium);
        /* manager.allStades.add(stadium); */ // Best practice ot not access collections directly, leave the class
                                              // contained into handles them
    }

    public void addReferee(String refereeName, String nationality)
            throws DuplicateRefereeException, DroitsInsuffisantsException {
        if (currentUser.role != UsersRole.ORGANISATEUR) {
            throw new DroitsInsuffisantsException("You are not allowed to call this method");
        }

        boolean check = manager.checkDuplicateReferee(refereeName);
        if (check) {
            throw new DuplicateRefereeException(
                    "A referee with the name '" + refereeName + "' already exists in the system");
        }

        Arbitre createdReferee = new Arbitre(refereeName, nationality);
        manager.addReferee(createdReferee);
    }

    public void addOrganizer(String organizerName, String nationality) throws DroitsInsuffisantsException {
        if (currentUser.role != UsersRole.ORGANISATEUR) {
            throw new DroitsInsuffisantsException("You are not allowed to call this method");
        }

        // I am not handling hte the exception where they have same name, since it is
        // fine, and i am sure the id will no be the same
        Origanisateur createdOriganizer = new Origanisateur(organizerName, nationality);
        manager.addOrganizer(createdOriganizer);
    }

    public void programMatch(Equipe equipeA, Equipe equipeB, Stade stade, Arbitre arbitre, Creneau creneau,
            MatchImportance importance)
            throws MatchInvalideException, ConflitCalendrierException, DroitsInsuffisantsException {
        if (currentUser.role != UsersRole.ORGANISATEUR) {
            throw new DroitsInsuffisantsException("You are not allowed to call this method");
        }

        if (equipeA.getNom().equalsIgnoreCase(equipeB.getNom())) {
            throw new MatchInvalideException("You can't make a match with 1 team");
        }

        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();
        if (creneau.date.isBefore(today) || (creneau.date.equals(today) && creneau.debut.isBefore(now))) {
            throw new MatchInvalideException("The time of playing you set is not right");
        }
        if (creneau.dureeMinutes < 90) {
            throw new MatchInvalideException("The match lifetime you set, is below the canonical of 90 minutes");
        }

        boolean teamCheck1 = manager.checkTeamExistence(equipeA.getNom());
        boolean teamCheck2 = manager.checkTeamExistence(equipeB.getNom());
        boolean stadeCheck = manager.checkStadiumExistence(stade.getNom());
        boolean refereeCheck = manager.checkRefereeExistence(arbitre.getNom());
        if (!teamCheck1 || !teamCheck2 || !stadeCheck || !refereeCheck) {
            throw new MatchInvalideException("Here what failed consecutively -> " + "teamCheckA: " + teamCheck1
                    + "teamCheckB: " + teamCheck2 + "stadeCheck: " + stadeCheck + "refereeCheck: " + refereeCheck);
        }

        // Here it will Check access rights, until we figure out how to make the app
        // role based, then we will come back

        Match theMatch = new Match(equipeA, equipeB, stade, arbitre, creneau, importance);
        // If an exception raised in one of the below methods, theMatch will be left to
        // garbage collector
        calendar.verifierConflit(theMatch);
        calendar.verifierEligibilite(theMatch);

        manager.addMatch(theMatch);
        stade.addMatch(theMatch);
        equipeA.addMatch(theMatch);
        equipeB.addMatch(theMatch);
    }

    public void annulerMatch(int matchId) throws MatchInvalideException, DroitsInsuffisantsException {
        if (currentUser.role != UsersRole.ORGANISATEUR) {
            throw new DroitsInsuffisantsException("You are not allowed to call this method");
        }

        Match match = manager.findMatchById(matchId);

        if (match == null) {
            throw new MatchInvalideException("Match does not exist");
        }

        if (match.statut == MatchState.ANNULATED) {
            throw new MatchInvalideException("Match already cancelled");
        }

        match.statut = MatchState.ANNULATED;

        match.stade.removeMatch(match);
        match.equipeA.removeMatch(match);
        match.equipeB.removeMatch(match);
    }

    public void afficherCalendrierParEquipe(int equipeId) {
        List<Match> matches = manager.rechercheParEquipe(equipeId);

        if (matches.isEmpty()) {
            System.out.println("No matches found.");
            return;
        }

        matches = manager.triParDate();
        for (Match m : matches) {
            System.out.println(m);
        }
    }

    public void afficherCalendrierParStade(int satadeId) {
        List<Match> matches = manager.rechercheParStade(satadeId);

        if (matches.isEmpty()) {
            System.out.println("No matches found.");
            return;
        }

        matches = manager.triParDate();
        for (Match m : matches) {
            System.out.println(m);
        }
    }

    public void afficherCalendrierParArbitre(int arbitreId) {
        List<Match> matches = manager.rechercheParArbitre(arbitreId);

        if (matches.isEmpty()) {
            System.out.println("No matches found.");
            return;
        }

        matches = manager.triParDate();
        for (Match m : matches) {
            System.out.println(m);
        }
    }

    public void afficherCalendrierParDate(LocalDate d) {
        List<Match> matches = manager.rechercheParDate(d);

        if (matches.isEmpty()) {
            System.out.println("No matches found.");
            return;
        }

        matches = manager.triParDate();
        for (Match m : matches) {
            System.out.println(m);
        }
    }

    public void generateReports() {
        System.out.println("Matches per day:");
        manager.matchesPerDay().forEach((date, count) -> System.out.println(date + ": " + count + " match(es)"));

        System.out.println("\nTop stadiums:");
        manager.topStadiums().forEach(s -> System.out.println(s.nom + ": " + s.affectationList.size() + " matches"));

        System.out.println("\nConflict incidents:");
        System.out.println(calendar.getConflitCount());
    }
}
