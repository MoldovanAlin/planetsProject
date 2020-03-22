package com.alin.model;

import java.util.ArrayList;

public class Crew {

    private Captain captain;

    private ArrayList<Robots> robots;

    public Crew(Captain captain, ArrayList<Robots> robots) {
        this.captain = captain;
        this.robots = robots;
    }

    public Captain getCaptain() {
        return captain;
    }

    public void setCaptain(Captain captain) {
        this.captain = captain;
    }

    public ArrayList<Robots> getRobots() {
        return robots;
    }

    public void setRobots(ArrayList<Robots> robots) {
        this.robots = robots;
    }
}
