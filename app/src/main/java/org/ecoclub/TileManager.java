package org.ecoclub;

import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TileManager {
    private static Tile[] tiles;
    private static FirebaseFirestore db;

    public static Tile[] getTiles() {
        FirebaseFirestore db = getDb();

        return null;
    }

    private static FirebaseFirestore getDb() {
        if(db == null) {
            db = FirebaseFirestore.getInstance();
        }

        return db;
    }
}
