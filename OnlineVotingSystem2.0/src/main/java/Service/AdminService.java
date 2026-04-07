package Service;

import model.Candidate;
import model.Voter;
import util.DataStore;

public class AdminService {

    public void registerCandidate(int id, String name) {
        DataStore.candidates.add(new Candidate(id, name));
    }

//    public void registerVoter(String id, String pass) {
//        DataStore.voters.add(new Voter(id, pass));
//    }

    public void showResult() {
        for (Candidate c : DataStore.candidates) {
            System.out.println(c.getName() + " : " + c.getVotes());
        }
    }
}
