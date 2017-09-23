package com.a099post.nira_test_app;

/**
 * Created by DESKTOP on 21-09-2017.
 */

public class Companies {
    String name;
    int score;
    String sector;

    public Companies(String name, int score, String sector) {
        this.name = name;
        this.score = score;
        this.sector = sector;

    }

    public Companies() {


    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }
}
