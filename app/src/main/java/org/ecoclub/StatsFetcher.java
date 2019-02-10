package org.ecoclub;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Nullable;


public class StatsFetcher {
    private static String TAG = "StatsFetcher";
    private static FirebaseFirestore db;
    private static Stats currentStats;

    public interface OnCompleteFetchEvent {
        void onComplete(Stats stats);
    }

    public interface OnStatsUpdate {
        void onUpdate(Stats stats);
    }

    private static FirebaseFirestore getDb() {
        if (db == null) {
            db = FirebaseFirestore.getInstance();
        }

        return db;
    }

    public static boolean shouldReset() {
        Calendar c = Calendar.getInstance();

        // Zero out today's date
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        Date today = c.getTime();

        // Zero out stored date time
        Date storedDate = currentStats.getTodaysDate();
        c.setTime(storedDate);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        storedDate = c.getTime();

        return storedDate.before(today);
    }

    private static void checkStats() {
        Calendar c = Calendar.getInstance();

        // Zero out today's date
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        Date today = c.getTime();

        // If stored date is before today, then clear it out
        if(shouldReset()) {
            FirebaseFirestore db = getDb();
            Stats newStats = new Stats(0, currentStats.getTotal(), new ArrayList<String>(), today, currentStats.getTree(), false);
            db.collection("User").document("stats").set(newStats);
        }
    }

    private static boolean inRange(int i, int lower, int upper) {
        return (lower <= i && i < upper);
    }

    public static boolean canSetStats() {
        // Can set stats if it's not updated today, or we need to reset
        return (!currentStats.updatedToday || shouldReset());
    }

    /**
     * @return Whether we've already set the stats for today
     */
    public static void setStats(int count, List<String> activities) {
        int newTotal = currentStats.getTotal() + count;
        int newTree = currentStats.getTree();

        if(inRange(newTotal, 1, 10)) {
            newTree = 1;
        } else if(inRange(newTotal, 10, 50)) {
            newTree = 2;
        } else if(inRange(newTotal, 50, 100)) {
            newTree = 3;
        }

        Stats newStats = new Stats(count, newTotal, activities, currentStats.todaysDate, newTree, true);
        db.collection("User").document("stats").set(newStats);
    }

    public static void fetchStats(final OnCompleteFetchEvent onCompleteEvent, final OnStatsUpdate onStatsUpdate) {
        FirebaseFirestore db = getDb();
        DocumentReference docRef = db.collection("User").document("stats");

        // Get the initial stats
        if(currentStats == null) {
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();

                        if(document.exists()) {
                            Stats stats = document.toObject(Stats.class);
                            currentStats = stats;
                            checkStats();
                            onCompleteEvent.onComplete(currentStats);
                        } else {
                            Log.d(TAG, "No such document");
                        }
                    } else {
                        Log.d(TAG, "get failed with ", task.getException());
                    }
                }
            });
        } else {
            onCompleteEvent.onComplete(currentStats);
        }

        // Check for changes
        if(onStatsUpdate != null) {
            docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirebaseFirestoreException e) {
                    if (e != null) {
                        Log.w(TAG, "Listen failed.", e);
                        return;
                    }

                    if (snapshot != null && snapshot.exists()) {
                        Stats stats = snapshot.toObject(Stats.class);
                        currentStats = stats;
                        checkStats();
                        onStatsUpdate.onUpdate(currentStats);
                    } else {
                        Log.d(TAG, "Current data: null");
                    }
                }
            });
        }
    }

    public static Stats getCurrentStats() {
        return currentStats;
    }
}
