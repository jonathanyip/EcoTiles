package org.ecoclub;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class BottomNavActivity extends AppCompatActivity {
    private final String SWITCH_TO_MSG = "org.ecotiles.SWITCH_TO_MSG";
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener;

    protected void setupBottomNav() {
        final Intent intent = getIntent();
        final AppCompatActivity self = this;

        // Set up callback to bottom nav button press
        mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;

                switch (item.getItemId()) {
                    case R.id.navigation_tiles:
                        intent = new Intent(self, TilesActivity.class);
                        intent.putExtra(SWITCH_TO_MSG, "tiles");
                        startActivity(intent);
                        return true;
                    case R.id.navigation_maps:
                        intent = new Intent(self, MapsActivity.class);
                        intent.putExtra(SWITCH_TO_MSG, "maps");
                        startActivity(intent);
                        return true;
                    case R.id.navigation_stats:
                        intent = new Intent(self, StatsActivity.class);
                        intent.putExtra(SWITCH_TO_MSG, "stats");
                        startActivity(intent);
                        return true;
                }

                return false;
            }
        };

        BottomNavigationView navigation = findViewById(R.id.navigation);

        // Set the selected bottom nav bar item
        String switchTo = intent.getStringExtra(SWITCH_TO_MSG);

        if(switchTo != null) {
            switch(switchTo) {
                case "tiles":
                    navigation.setSelectedItemId(R.id.navigation_tiles);
                    break;
                case "maps":
                    navigation.setSelectedItemId(R.id.navigation_maps);
                    break;
                case "stats":
                    navigation.setSelectedItemId(R.id.navigation_stats);
                    break;
            }
        }

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
}
