package org.ecoclub;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;

public class TipsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        StatsFetcher.fetchStats(new StatsFetcher.OnCompleteFetchEvent() {
            @Override
            public void onComplete(Stats stats) {
                Log.d("TipsActivity", "" + stats.getTodaysDate());
                if(StatsFetcher.canSetStats()) {
                    Log.d("TipsActivity", "Updated stats");
                    StatsFetcher.setStats(10, Arrays.asList("Recycled", "Didn't use a plastic straw", "Reused bag"));
                }
            }
        }, new StatsFetcher.OnStatsUpdate() {
            @Override
            public void onUpdate(Stats stats) {
                Log.d("TipsActivity", "We've updated the stats");
            }
        });
    }

}
