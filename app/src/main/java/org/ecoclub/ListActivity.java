package org.ecoclub;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;
import java.util.HashMap;

import static android.widget.AbsListView.CHOICE_MODE_MULTIPLE;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitle("Select what you did today!");

        final ListView lv = (ListView) findViewById(R.id.goalsview2);
        lv.setChoiceMode(CHOICE_MODE_MULTIPLE);
        lv.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice, Arrays.asList("Recycling", "Not using a plastic straw")));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("ListActivity", "" + lv.getCheckedItemPositions());

                SparseBooleanArray arr = lv.getCheckedItemPositions();
                int count = 0;
                if(arr.size() != 0) {
                    for (int i = 0; i < arr.size(); i++) {
                        if(arr.valueAt(i) == true) {
                            count++;
                        }
                    }
                }

                Snackbar.make(view, "Added your stats: " + count, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
