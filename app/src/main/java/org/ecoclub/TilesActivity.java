package org.ecoclub;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.widget.TextView;

public class TilesActivity extends BottomNavActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiles);
        setupBottomNav();
    }

}
