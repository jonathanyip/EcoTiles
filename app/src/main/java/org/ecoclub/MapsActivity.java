package org.ecoclub;

import android.os.Bundle;

public class MapsActivity extends BottomNavActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setupNavbar();
    }

}
