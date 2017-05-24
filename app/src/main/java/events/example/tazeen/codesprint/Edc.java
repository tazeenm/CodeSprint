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
import android.widget.EditText;
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

import events.com.example.tazeen.codesprint.R;

public class Edc extends AppCompatActivity {

    private Toolbar ToolBar1;
    private AdapterViewFlipper simpleAdapterViewFlipper;
    //int[] slideImages = {R.drawable.e1, R.drawable.e2, R.drawable.e3, R.drawable.e4, R.drawable.e5};     // array of images
    int[] slideImages = {R.drawable.e1, R.drawable.e2};
    DatabaseReference dref;
    ArrayList<String> edcItems = new ArrayList<>();
    ArrayAdapter<String> adapter3;
    ListView edcList;
    private Button subscribeBtn2;
    private Button unsubscribeBtn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edc);
        //ToolBar1 = (Toolbar) findViewById(R.id.toolbar5);

        edcList = (ListView) findViewById(R.id.edc_list);
        adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, edcItems);
        edcList.setAdapter(adapter3);

        simpleAdapterViewFlipper = (AdapterViewFlipper) findViewById(R.id.simpleAdapterViewFlipper);
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), slideImages);
        simpleAdapterViewFlipper.setAdapter(customAdapter);
        simpleAdapterViewFlipper.setFlipInterval(3000);
        simpleAdapterViewFlipper.setAutoStart(true);

        subscribeBtn2 = (Button) findViewById(R.id.subscribe_edc);
        unsubscribeBtn2 = (Button) findViewById(R.id.unsubscribe_edc);

        subscribeBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseMessaging.getInstance().subscribeToTopic("edc");
                Log.d("Subscribe", "EDC");
                Toast.makeText(Edc.this, "Subscribed to EDC", Toast.LENGTH_SHORT).show();
            }
        });

        unsubscribeBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseMessaging.getInstance().unsubscribeFromTopic("edc");
                Log.d("Unsubscribe", "EDC");
            }
        });

        dref = FirebaseDatabase.getInstance().getReference().child("edc");
        dref.keepSynced(true);
        dref.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                for(DataSnapshot item:dataSnapshot.getChildren()) {
                    Log.d("Snapshot:", item.getValue().toString());
                    edcItems.add(item.getValue().toString());
                }
                adapter3.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                String item = dataSnapshot.getValue(String.class);
                edcItems.remove(item);
                adapter3.notifyDataSetChanged();

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

        Intent intent = new Intent(Edc.this, MainActivity.class);
        startActivity(intent);
    }
}
