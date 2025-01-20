package cz.uhk.kppro2025.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "results")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "competition_id")
    private Competition competition;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

//    @ElementCollection
//    private List<Integer> scoreParts;

    private int scoreTotal;
    private int rank;
    private String performanceClass;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

//    public List<Integer> getScoreParts() {
//        return scoreParts;
//    }
//
//    public void setScoreParts(List<Integer> scoreParts) {
//        this.scoreParts = scoreParts;
//    }

    public int getScoreTotal() {
        return scoreTotal;
    }

    public void setScoreTotal(int scoreTotal) {
        this.scoreTotal = scoreTotal;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getPerformanceClass() {
        return performanceClass;
    }

    public void setPerformanceClass(String performanceClass) {
        this.performanceClass = performanceClass;
    }
}
