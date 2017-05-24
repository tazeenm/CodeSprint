package events.example.tazeen.codesprint;

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
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.OnDisconnect;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;

import events.com.example.tazeen.codesprint.R;


public class Sports extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    //int[] slideImages = {R.drawable.s1, R.drawable.s2, R.drawable.s3, R.drawable.s4, R.drawable.s5};
    int[] slideImages = {R.drawable.s1, R.drawable.s2}; // array of images
    ListView sportsList;
    DatabaseReference dref;
    ArrayList<String> sportsItems = new ArrayList<>();
    ArrayAdapter<String> adapter1;
    //private Toolbar ToolBar1;
    private AdapterViewFlipper simpleAdapterViewFlipper;
    private Button subscribeBtn;
    private Button unsubscribeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports);

       //ToolBar1 = (Toolbar) findViewById(R.id.toolbar3);
        sportsList = (ListView) findViewById(R.id.sports_list);
        adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, sportsItems);
        sportsList.setAdapter(adapter1);

        simpleAdapterViewFlipper = (AdapterViewFlipper) findViewById(R.id.simpleAdapterViewFlipper);
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), slideImages);
        simpleAdapterViewFlipper.setAdapter(customAdapter);
        simpleAdapterViewFlipper.setFlipInterval(3000);
        simpleAdapterViewFlipper.setAutoStart(true);

        subscribeBtn = (Button) findViewById(R.id.subscribe_sports);
        unsubscribeBtn = (Button) findViewById(R.id.unsubscribe_sports);

        subscribeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseMessaging.getInstance().subscribeToTopic("sports");
                Log.d("Subscribe", "Sports");
                Toast.makeText(Sports.this, "Subscribed to Sports", Toast.LENGTH_SHORT).show();
            }
        });

        unsubscribeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseMessaging.getInstance().unsubscribeFromTopic("sports");
                Log.d("Unsubscribe", "Sports");
            }
        });

        dref = FirebaseDatabase.getInstance().getReference().child("sports");
        dref.keepSynced(true);
        /*dref.onDisconnect().setValue("Disconnected!");*/

        dref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("Something:", dataSnapshot.toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        /*OnDisconnect onDisconnectRef = dref.onDisconnect();
        onDisconnectRef.cancel();*/

        dref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                for (DataSnapshot item : dataSnapshot.getChildren()) {
                    Log.d("Snapshot:", item.getValue().toString());
                    sportsItems.add(item.getValue().toString());
                }

                adapter1.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                String item = dataSnapshot.getValue(String.class);
                sportsItems.remove(item);
                adapter1.notifyDataSetChanged();

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

        Intent intent = new Intent(Sports.this, MainActivity.class);
        startActivity(intent);
    }
}
