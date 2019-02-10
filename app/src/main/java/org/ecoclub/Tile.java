package org.ecoclub;

import java.util.Date;

public class Tile {
    Date date;
    String[] activities;
    int rank;

    public Tile(Date date, String[] activities, int rank) {
        this.date = date;
        this.activities = activities;
        this.rank = rank;
    }
}
