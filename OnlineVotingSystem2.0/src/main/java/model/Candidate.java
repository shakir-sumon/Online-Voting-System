package model;

import java.io.Serializable;


public class Candidate implements Serializable {

    private int id;
    private String name;
    private int votes;

    public Candidate(int id, String name) {
        this.id = id;
        this.name = name;
        this.votes = 0;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getVotes() { return votes; }
    public void addVote() { votes++; }

    private static final long serialVersionUID = 1L;

}
