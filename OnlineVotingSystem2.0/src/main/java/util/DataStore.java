package util;

import model.Candidate;
import model.Voter;
import java.util.ArrayList;

import java.io.*;


public class DataStore {

    private static int candidateIdCounter = 1;

    private static final String VOTER_FILE =
            System.getProperty("user.dir") + "/voters.dat";

    private static final String CANDIDATE_FILE =
            System.getProperty("user.dir") + "/candidates.dat";



    public static ArrayList<Candidate> candidates = new ArrayList<>();
    public static ArrayList<Voter> voters = new ArrayList<>();

    public static void addCandidate(String name) {
        candidates.add(new Candidate(candidateIdCounter++, name));
    }

    public static ArrayList<Candidate> getCandidates() {
        return candidates;
    }

    public static Voter getOrCreateVoter(String voterId, String password) {
        for (Voter v : voters) {
            if (v.getVoterID().equals(voterId)) {
                if (!v.checkPassword(password)) {
                    return null; // wrong password
                }
                return v;
            }
        }

        Voter v = new Voter(voterId, password);
        voters.add(v);
        return v;
    }

    public static boolean registerVoter(String voterId, String password) {

        for (Voter v : voters) {
            if (v.getVoterID().equals(voterId)) {
                return false; // duplicate ID
            }
        }

        voters.add(new Voter(voterId, password));
        return true;
    }

    public static Voter authenticateVoter(String voterId, String password) {

        for (Voter v : voters) {
            if (v.getVoterID().equals(voterId) && v.checkPassword(password)) {
                return v; // login success
            }
        }

        return null; // wrong ID or password
    }


    public static boolean castVote(int candidateId, String voterId, String password) {
        Voter voter = getOrCreateVoter(voterId, password);

        if (voter == null) return false; // wrong password
        if (voter.hasVoted()) return false;

        for (Candidate c : candidates) {
            if (c.getId() == candidateId) {
                c.addVote();
                voter.vote();
                return true;
            }
        }
        return false;
    }


    public static void saveData() {
        try {
            System.out.println(">>> SAVING DATA");

            ObjectOutputStream voterOut =
                    new ObjectOutputStream(new FileOutputStream(VOTER_FILE));
            voterOut.writeObject(voters);
            voterOut.close();

            ObjectOutputStream candidateOut =
                    new ObjectOutputStream(new FileOutputStream(CANDIDATE_FILE));
            candidateOut.writeObject(candidates);
            candidateOut.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void loadData() {
        try {
            System.out.println(">>> LOADING DATA");

            File voterFile = new File(VOTER_FILE);
            File candidateFile = new File(CANDIDATE_FILE);

            if (voterFile.exists()) {
                ObjectInputStream voterIn =
                        new ObjectInputStream(new FileInputStream(VOTER_FILE));
                voters = (ArrayList<Voter>) voterIn.readObject();
                voterIn.close();
            }

            if (candidateFile.exists()) {
                ObjectInputStream candidateIn =
                        new ObjectInputStream(new FileInputStream(CANDIDATE_FILE));
                candidates = (ArrayList<Candidate>) candidateIn.readObject();
                candidateIn.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
