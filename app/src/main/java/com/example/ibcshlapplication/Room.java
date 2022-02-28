package com.example.ibcshlapplication;

import androidx.annotation.NonNull;

import java.util.Locale;
import java.util.*;
public class Room implements Comparator<Room> {
    private int hallway;
    private int ind;
    private double weight;
    private int nw;
    private String name;


    public Room(){}
    public Room(int ind, double weight) {
        this.ind = ind;
        this.weight = weight;
    }

    public Room(int ind, String name) {
        nw = 1;
        this.weight = 3;
        this.ind=ind;
        this.name = name;
        this.hallway = ((int) this.name.charAt(0)) - 65;
    }

    public int getHallway() {
        return hallway;
    }

    public int getInd() {
        return ind;
    }

    public void update_weight(int w) {
        weight *= nw;
        weight += w;
        nw++;
        weight /= nw;
    }

    public double getWeight() {
        return weight;
    }

    public String getName() {return name;}

    @Override public int compare(Room r1, Room r2) {
        if (r1.weight < r2.weight)
            return -1;

        if (r1.weight > r2.weight)
            return 1;

        return 0;
    }


}
