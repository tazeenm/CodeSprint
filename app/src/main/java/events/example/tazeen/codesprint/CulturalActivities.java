package events.example.tazeen.codesprint;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterViewFlipper;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;

import events.com.example.tazeen.codesprint.R;

public class CulturalActivities extends AppCompatActivity {
    private Toolbar ToolBar3;

    DatabaseReference dref;
    ArrayList<String> culturalItems = new ArrayList<>();
    ArrayAdapter<String> adapter2;
    ListView culturalList;
    private Button subscribeBtn1;
    private Button unsubscribeBtn1;

    private AdapterViewFlipper simpleAdapterViewFlipper;
    //int[] slideImages = {R.drawable.c2, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4, R.drawable.pic5};     // array of images
    int[] slideImages = {R.drawable.c1, R.drawable.c2};

    AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private TimePicker alarmTimePicker;
    private static CulturalActivities inst;
    //private TextView alarmTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cultural_activities);
        //ToolBar3 = (Toolbar) findViewById(R.id.toolbar4);
        culturalList = (ListView) findViewById(R.id.cultural_list);
        adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, culturalItems);
        culturalList.setAdapter(adapter2);

        simpleAdapterViewFlipper = (AdapterViewFlipper) findViewById(R.id.simpleAdapterViewFlipper);
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(),slideImages);
        simpleAdapterViewFlipper.setAdapter(customAdapter);
        simpleAdapterViewFlipper.setFlipInterval(3000);
        simpleAdapterViewFlipper.setAutoStart(true);

        subscribeBtn1 = (Button) findViewById(R.id.subscribe_cultural_activities);
        unsubscribeBtn1 = (Button) findViewById(R.id.unsubscribe_cultural_activities);

        subscribeBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseMessaging.getInstance().subscribeToTopic("culturalActivities");
                Log.d("Subscribe", "Cultural Activities");
                Toast.makeText(CulturalActivities.this, "Subscribed to Cultural Activities", Toast.LENGTH_SHORT).show();
            }
        });

        unsubscribeBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseMessaging.getInstance().unsubscribeFromTopic("culturalActivities");
                Log.d("Unsubscribe","Cultual Activities");
            }
        });

        dref = FirebaseDatabase.getInstance().getReference().child("culturalActivities");
        dref.keepSynced(true);
        dref.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                for (DataSnapshot item : dataSnapshot.getChildren()) {
                    Log.d("Snapshot:", item.getValue().toString());
                    culturalItems.add(item.getValue().toString());
                }
                adapter2.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                String item = dataSnapshot.getValue(String.class);
                culturalItems.remove(item);
                adapter2.notifyDataSetChanged();

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        backButtonHandler();
        return;
    }

    private void backButtonHandler() {

        Intent intent = new Intent(CulturalActivities.this, MainActivity.class);
        startActivity(intent);
    }
}
