package org.ecoclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import org.ecoclub.R;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MainActivity extends BottomNavActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Tile.setTile(Arrays.asList("act1", "act2"), 5);
        setupBottomNav();

        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }
}
