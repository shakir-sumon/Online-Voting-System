package Service;

import model.Candidate;
import model.Voter;
import util.DataStore;

public class VotingService {

    public Voter login(String id, String pass) {
        for (Voter v : DataStore.voters) {
            if (v.getVoterID().equals(id) && v.checkPassword(pass)) {
                return v;
            }
        }
        return null;
    }

    public boolean castVote(Voter voter, int candidateId) {

        if (voter == null) return false;
        if (voter.hasVoted()) return false;

        for (Candidate c : DataStore.candidates) {
            if (c.getId() == candidateId) {
                c.addVote();
                voter.vote();
                return true;
            }
        }
        return false;
    }
}
