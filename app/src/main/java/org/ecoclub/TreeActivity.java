package org.ecoclub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ProgressBar;


public class TreeActivity extends AppCompatActivity {
    int tree = 6;
    int current = 1;
    int today = 1;
    int goal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        TextView goalTextView = (TextView) findViewById(R.id.goalTextView);
        TextView dailyCountTextView = (TextView) findViewById(R.id.todayCountTextView);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);


        switch(tree) {
            case 1:
                goal = 1;
                goalTextView.setText("1");
                imageView.setImageResource(R.drawable.tree1);
                break;
            case 2:
                goal = 5;
                goalTextView.setText("5");
                imageView.setImageResource(R.drawable.tree2);
                break;
            case 3:
                goal = 20;
                goalTextView.setText("20");
                imageView.setImageResource(R.drawable.tree3);
                break;
            case 4:
                goal = 50;
                goalTextView.setText("50");
                imageView.setImageResource(R.drawable.tree4);
                break;
            case 5:
                goal = 100;
                goalTextView.setText("100");
                imageView.setImageResource(R.drawable.tree5);
                break;
            case 6:
                goal = 200;
                goalTextView.setText("200");
                imageView.setImageResource(R.drawable.tree6);
                break;
        }


        dailyCountTextView.setText(Integer.toString(today));
        progressBar.setProgress((current * 100)/(goal));



    }
}
