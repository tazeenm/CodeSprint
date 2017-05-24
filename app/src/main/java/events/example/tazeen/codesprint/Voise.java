package events.example.tazeen.codesprint;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.view.ViewPager;
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
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import events.com.example.tazeen.codesprint.R;

public class Voise extends AppCompatActivity {

    private Toolbar mToolBar;
    ListView voiseList;
    DatabaseReference dref;
    ArrayList<String> voiseItems = new ArrayList<>();
    ArrayAdapter<String> adapter4;
    private Button subscribeBtn3;
    private Button unsubscribeBtn3;

    private AdapterViewFlipper simpleAdapterViewFlipper;
    //int[] slideImages = {R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4, R.drawable.pic5};     // array of images
    int[] slideImages = {R.drawable.v1, R.drawable.v2};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voise);

        //mToolBar = (Toolbar) findViewById(R.id.toolbar2);
        voiseList = (ListView) findViewById(R.id.voise_list);
        adapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, voiseItems);
        voiseList.setAdapter(adapter4);

        simpleAdapterViewFlipper = (AdapterViewFlipper) findViewById(R.id.simpleAdapterViewFlipper);
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), slideImages);
        simpleAdapterViewFlipper.setAdapter(customAdapter);
        simpleAdapterViewFlipper.setFlipInterval(3000);
        simpleAdapterViewFlipper.setAutoStart(true);

        subscribeBtn3 = (Button) findViewById(R.id.subscribe_voise);
        unsubscribeBtn3 = (Button) findViewById(R.id.unsubscribe_voise);

        subscribeBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseMessaging.getInstance().subscribeToTopic("voise");
                Log.d("Subscribe", "Vo'ISE");
                Toast.makeText(Voise.this, "Subscribed to Vo'ISE", Toast.LENGTH_SHORT).show();
            }
        });

        unsubscribeBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseMessaging.getInstance().unsubscribeFromTopic("voise");
                Log.d("Unsubscribe", "Vo'ISE");
            }
        });

        dref = FirebaseDatabase.getInstance().getReference().child("voise");
        dref.keepSynced(true);
        dref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                for(DataSnapshot item:dataSnapshot.getChildren()) {
                    Log.d("Snapshot:", item.getValue().toString());
                    voiseItems.add(item.getValue().toString());
                }
                adapter4.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                String item = dataSnapshot.getValue(String.class);
                voiseItems.remove(item);
                adapter4.notifyDataSetChanged();

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

        Intent intent = new Intent(Voise.this, MainActivity.class);
        startActivity(intent);
    }
}
