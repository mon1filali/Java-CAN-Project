package Logic.Management;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Logic.MatchState;
import Logic.Person.PersonSubClasses.Arbitre;
import Logic.Person.PersonSubClasses.Origanisateur;
import Logic.Sport.Equipe;
import Logic.Sport.Match;
import Logic.Sport.Stade;

public class CompetitionManager {
    // We usually want to make getters and setterd for this List down here, to not
    // directly access them "In the future, we could refactore"
    public ArrayList<Match> allMatches = new ArrayList<Match>();
    public ArrayList<Equipe> allEquipes = new ArrayList<Equipe>();
    public ArrayList<Stade> allStades = new ArrayList<Stade>();
    public ArrayList<Arbitre> allArbitres = new ArrayList<Arbitre>();
    public ArrayList<Origanisateur> allOriganisateurs = new ArrayList<Origanisateur>();

    public ArrayList<Match> rechercheParArbitre(int id) {
        ArrayList<Match> matches = new ArrayList<>();

        for (Match m : allMatches) {
            if (m.statut != MatchState.ANNULATED &&
                    m.arbitre.getId() == id) {
                matches.add(m);
            }
        }
        return matches;
    }

    public ArrayList<Match> rechercheParEquipe(int id) {
        ArrayList<Match> matches = new ArrayList<>();

        for (Equipe e : allEquipes) {
            if (e.getId() == id) {
                for (Match m : e.getAllMatches()) {
                    if (m.statut != MatchState.ANNULATED) {
                        matches.add(m);
                    }
                }
                break;
            }
        }
        return matches;
    }

    public ArrayList<Match> rechercheParStade(int id) {
        ArrayList<Match> matches = new ArrayList<>();

        for (Stade s : allStades) {
            if (s.getId() == id) {
                for (Match m : s.getAffectationList()) {
                    if (m.statut != MatchState.ANNULATED) {
                        matches.add(m);
                    }
                }
                break;
            }
        }
        return matches;
    }

    public ArrayList<Match> rechercheParDate(LocalDate d) {
        ArrayList<Match> matches = new ArrayList<Match>();

        for (int i = 0; i < allMatches.size(); i++) {
            if (allMatches.get(i).creneau.date.equals(d) && allMatches.get(i).statut != MatchState.ANNULATED) {
                matches.add(allMatches.get(i));
            }
        }
        return matches;
    }

    public List<Match> triParDate() {
        List<Match> matchToTri = new ArrayList<Match>();

        for (int i = 0; i < allMatches.size(); i++) {
            matchToTri.add(allMatches.get(i));
        }

        for (int i = 0; i < matchToTri.size(); i++) {
            int minIndex = i;
            for (int j = i + 1; j < matchToTri.size(); j++) {
                if (matchToTri.get(j).creneau.date
                        .isBefore(matchToTri.get(minIndex).creneau.date)) {
                    minIndex = j;
                }
            }

            // The swap
            Match temp = matchToTri.get(i);
            matchToTri.set(i, matchToTri.get(minIndex));
            matchToTri.set(minIndex, temp);
        }
        return matchToTri;
    }

    public void addMatch(Match m) {
        this.allMatches.add(m);
    }

    // This is the function needed for the Team creation
    public Equipe findTeamByName(String teamName) {
        Equipe team = null;

        for (Equipe e : this.allEquipes) {
            if (e.getNom().equalsIgnoreCase(teamName)) {
                team = e;
                break;
            }
        }

        return team;
    }

    public boolean checkTeamExistence(String teamName) {
        boolean check = false;

        for (Equipe e : this.allEquipes) {
            if (e.getNom().equalsIgnoreCase(teamName)) {
                check = true;
                break;
            }
        }

        return check;
    }

    public boolean checkDuplicateTeam(String name) {
        boolean isDuplicated = false;

        for (Equipe e : this.allEquipes) {
            if (e.getNom().equalsIgnoreCase(name)) {
                isDuplicated = true;
                break;
            }
        }
        return isDuplicated;
    }

    public void addTeam(Equipe e) {
        this.allEquipes.add(e);
    }

    // This is hte functions needed to create Stadium
    public void addStadium(Stade s) {
        this.allStades.add(s);
    }

    public boolean stadeExistanceCheck(String name, String city) {
        boolean check = false;

        for (Stade s : this.allStades) {
            if (s.nom.equalsIgnoreCase(name) && s.ville.equalsIgnoreCase(city)) {
                check = true;
            }
        }

        return check;
    }

    public boolean checkStadiumExistence(String name) {
        boolean isDuplicated = false;

        for (Stade s : this.allStades) {
            if (s.getNom().equalsIgnoreCase(name)) {
                isDuplicated = true;
                break;
            }
        }
        return isDuplicated;
    }

    // This is the functions needed to create Referee
    public void addReferee(Arbitre a) {
        this.allArbitres.add(a);
    }

    public boolean checkDuplicateReferee(String name) {
        boolean isDuplicated = false;

        for (Arbitre a : this.allArbitres) {
            if (a.getNom().equalsIgnoreCase(name)) {
                isDuplicated = true;
                break;
            }
        }
        return isDuplicated;
    }

    public boolean checkRefereeExistence(String name) {
        boolean isDuplicated = false;

        for (Arbitre a : this.allArbitres) {
            if (a.getNom().equalsIgnoreCase(name)) {
                isDuplicated = true;
                break;
            }
        }
        return isDuplicated;
    }

    public boolean checkRefereeIsAvailable(String name) { // Usually we would compare with Id but for now let us think
                                                          // that the name is unique
        boolean isAvailable = true;

        for (Match a : this.allMatches) {
            if (a.arbitre.getNom().equalsIgnoreCase(name) && a.statut != MatchState.ANNULATED) {
                isAvailable = false;
                break;
            }
        }

        return isAvailable;
    }

    // This is the functions needed to create Referee
    public void addOrganizer(Origanisateur o) {
        this.allOriganisateurs.add(o);
    }

    public Map<LocalDate, Integer> matchesPerDay() {
        Map<LocalDate, Integer> report = new HashMap<>();
        for (Match m : allMatches) {
            report.put(m.creneau.date, report.getOrDefault(m.creneau.date, 0) + 1);
        }
        return report;
    }

    public List<Stade> topStadiums() {
        return allStades.stream()
                .sorted((s1, s2) -> Integer.compare(s2.affectationList.size(), s1.affectationList.size()))
                .toList();
    }

    // Functions for match
    public Match findMatchById(int matchId) {
        Match match = null;

        for (Match m : this.allMatches) {
            if (m.codeMatch == matchId) {
                match = m;
                break;
            }
        }

        return match;
    }
}

/*
 * [10,5,4,8,9,3]->[3,10,5,8,9,4]
 * 
 * for(
 * 
 * int i = 0;i<size;i++){
 * start = a[i]; = 10
 * for(int j = (i+1); j < (size - i); j++){
 * if(start > a[j]){
 * temp = a[j]; = 3
 * a[j] = start; = 4
 * start = temp; = 3
 * }
 * }
 * }
 */

/* The id identifies the object, no need to use the "nom, équipe, and stade" */