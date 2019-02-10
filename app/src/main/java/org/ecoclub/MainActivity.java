package org.ecoclub;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import org.ecoclub.MapsFragment;
import org.ecoclub.R;
import org.ecoclub.StatsFragment;
import org.ecoclub.TilesFragment;

public class MainActivity extends AppCompatActivity {
    private final FragmentManager fragmentManager = getSupportFragmentManager();
    private Fragment activeFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_tiles:
                    switchToFragment("tiles", TilesFragment.class);
                    return true;
                case R.id.navigation_maps:
                    switchToFragment("maps", MapsFragment.class);
                    return true;
                case R.id.navigation_stats:
                    switchToFragment("stats", StatsFragment.class);
                    return true;
            }

            return false;
        }
    };

    private void switchToFragment(String fragmentTag, Class className) {
        Fragment fragment = fragmentManager.findFragmentByTag(fragmentTag);
        boolean isNew = false;

        // If fragment does not exist yet, create a new instance of it
        if(fragment == null) {
            try {
                fragment = (Fragment) className.newInstance();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }

            isNew = true;
        }

        // If it's new, add it to the fragment manager first
        if(isNew) {
            fragmentManager.beginTransaction().add(R.id.fragmentContainer, fragment, fragmentTag).hide(activeFragment).show(fragment).commit();
        } else {
            fragmentManager.beginTransaction().hide(activeFragment).show(fragment).commit();
        }

        // Set the new fragment!
        activeFragment = fragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Start off with the maps fragment
        activeFragment = new MapsFragment();
        fragmentManager.beginTransaction().add(R.id.fragmentContainer, activeFragment, "maps").show(activeFragment).commit();

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
}
