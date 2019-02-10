//Source: Google Maps API code (isServicesOk) adopted from Android Google Maps Course on youtube

package org.ecoclub;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import org.ecoclub.MapsFragment;
import org.ecoclub.R;
import org.ecoclub.StatsFragment;
import org.ecoclub.TilesFragment;

import java.awt.font.TextAttribute;

public class MainActivity extends AppCompatActivity {
    private final FragmentManager fragmentManager = getSupportFragmentManager();
    private Fragment activeFragment;
    private static final String TAG = "MainActivity";

    private static final int ERROR_DIALOG_REQUEST = 9001;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
//                case R.id.navigation_tiles:
//                    switchToFragment("tiles", TilesFragment.class);
//                    return true;
                case R.id.navigation_maps:
                    startActivity(new Intent(MainActivity.this,  MapsActivity.class));
                    return true;
//                case R.id.navigation_stats:
//                    switchToFragment("stats", StatsFragment.class);
//                    return true;
            }

            return false;
        }
    };

//    private void switchToFragment(String fragmentTag, Class className) {
//        Fragment fragment = fragmentManager.findFragmentByTag(fragmentTag);
//        boolean isNew = false;
//
//        // If fragment does not exist yet, create a new instance of it
//        if(fragment == null) {
//            try {
//                fragment = (Fragment) className.newInstance();
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            } catch (InstantiationException e) {
//                e.printStackTrace();
//            }
//
//            isNew = true;
//        }
//
//        // If it's new, add it to the fragment manager first
//        if(isNew) {
//            fragmentManager.beginTransaction().add(R.id.fragmentContainer, fragment, fragmentTag).hide(activeFragment).show(fragment).commit();
//        } else {
//            fragmentManager.beginTransaction().hide(activeFragment).show(fragment).commit();
//        }
//
//        // Set the new fragment!
//        activeFragment = fragment;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(isServicesOk()) {

            // Start off with the maps activity
            Intent intent = new Intent(MainActivity.this, MapsActivity.class);
            startActivity(intent);
            //fragmentManager.beginTransaction().add(R.id.fragmentContainer, activeFragment, "maps").commit();
        }
            BottomNavigationView navigation = findViewById(R.id.navigation);
            navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }




    //Check for errors with the Google API - adopted from course
    private boolean isServicesOk(){
        Log.d(TAG, "isServicesOK: checking google services version");
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);

        if(available == ConnectionResult.SUCCESS){
            //everything is okay, user can make app requests
            Log.d(TAG, "isServicesOK: Google Play Services is working");
            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            //an error occurred but we can fix it
            Log.d(TAG, "isServicesOK: An error occurred but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        } else{
            Toast.makeText(this,"You can't make map requests", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
