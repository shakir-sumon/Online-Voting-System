package model;
import java.io.Serializable;

public class Voter implements Serializable {


    private String voterID;
    private String password;
    private boolean hasVoted;

    public Voter(String voterID, String password) {
        this.voterID = voterID;
        this.password = password;
        this.hasVoted = false;
    }

    public String getVoterID() {
        return voterID;
    }

    public String getPassword() {
        return password;
    }

    public boolean checkPassword(String inputPassword) {
        return password.equals(inputPassword);
    }

    public boolean hasVoted() {
        return hasVoted;
    }

    public void vote() {
        this.hasVoted = true;
    }

    private static final long serialVersionUID = 1L;

}
