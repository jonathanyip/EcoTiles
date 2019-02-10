package org.ecoclub;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.List;

public class Tile {
    private List<String> activities;
    private int rank;
    private Date timestamp;

    public Tile() {}

    public Tile(Date timestamp, List<String> activities, int rank) {
        this.activities = activities;
        this.rank = rank;
        this.timestamp = timestamp;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public List<String> getActivities() {
        return activities;
    }

    public int getRank() {
        return rank;
    }

    public static void setTile(List<String> activities, int rank) {
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        Tile tile = new Tile(new Date(), activities, rank);
        db.collection("tiles").add(tile);
    }

    public static List<Tile> getTiles() {
        return null;
    }
}
