package org.ecoclub;

import java.util.Date;
import java.util.List;

public class Stats {
    public int todaysCount;
    public int total;
    public int tree;

    public List<String> activities;

    public Date todaysDate;

    public boolean updatedToday;

    public Stats() {}

    public Stats(int todaysCount, int total, List<String> activities, Date todaysDate, int tree, boolean updatedToday) {
        this.todaysCount = todaysCount;
        this.total = total;
        this.activities = activities;
        this.todaysDate = todaysDate;
        this.updatedToday = updatedToday;
        this.tree = tree;
    }

    public boolean isUpdatedToday() {
        return updatedToday;
    }

    public int getTodaysCount() {
        return todaysCount;
    }

    public int getTree() {
        return todaysCount;
    }

    public int getTotal() {
        return total;
    }

    public List<String> getActivities() {
        return activities;
    }

    public Date getTodaysDate() {
        return todaysDate;
    }
}
