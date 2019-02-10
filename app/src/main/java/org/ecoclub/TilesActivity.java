package org.ecoclub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import java.util.Date;
import java.util.Calendar;



public class TilesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiles);

        GridView gridView = (GridView) findViewById(R.id.gridView);
        gridView.setNumColumns(12);

        Tile[] tiles = new Tile[12*31];

        String[] activities = {"Recycled", "Used a reusable water bottle"};

        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        tiles[(month - 1) * 31 + day - 1] = new Tile(date, activities, 2);

        TilesAdapter tilesAdapter = new TilesAdapter(this, tiles);

        gridView.setAdapter(tilesAdapter);


        //gridview.setAdapter(new ImageAdapter(this));

        /*
        gridview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(HelloGridView.this, "" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });
        */

    }
}

