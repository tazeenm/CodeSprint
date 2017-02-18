package events.example.tazeen.codesprint;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import events.com.example.tazeen.codesprint.R;

public class Home extends AppCompatActivity {

    private Button voise;
    private Button edc;
    private Button sports;
    private Button cultural_activities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        voise = (Button) findViewById(R.id.voise);
        edc = (Button) findViewById(R.id.edc);
        sports = (Button) findViewById(R.id.sports);
        cultural_activities = (Button) findViewById(R.id.c_activities);

        voise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(Home.this, Voise.class);
                startActivity(i2);
            }
        });

        edc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i3 = new Intent(Home.this, Edc.class);
                startActivity(i3);
            }
        });

        sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i4 = new Intent(Home.this, Sports.class);
                startActivity(i4);
            }
        });

        cultural_activities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i5 = new Intent(Home.this, CulturalActivities.class);
                startActivity(i5);
            }
        });
    }
}
