package events.example.tazeen.codesprint;

import android.os.Bundle;
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

import events.com.example.tazeen.codesprint.R;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

public class Sports extends AppCompatActivity {

    private Toolbar ToolBar1;

    private AdapterViewFlipper simpleAdapterViewFlipper;
    int[] slideImages = {R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4, R.drawable.pic5};     // array of images
    ListView sportsList;
    DatabaseReference dref;
    ArrayList<String> sportsItems = new ArrayList<>();
    ArrayAdapter<String> adapter1;
    private Button subscribeBtn;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports);

        ToolBar1 = (Toolbar) findViewById(R.id.toolbar3);
        sportsList = (ListView) findViewById(R.id.sports_list);
        adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, sportsItems);
        sportsList.setAdapter(adapter1);

        simpleAdapterViewFlipper = (AdapterViewFlipper) findViewById(R.id.simpleAdapterViewFlipper);
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), /*fruitNames,*/ slideImages);
        simpleAdapterViewFlipper.setAdapter(customAdapter);
        simpleAdapterViewFlipper.setFlipInterval(3000);
        simpleAdapterViewFlipper.setAutoStart(true);

        subscribeBtn = (Button) findViewById(R.id.subscribe_sports);

        /*if (getIntent().getExtras() != null) {
            for (String key : getIntent().getExtras().keySet()) {
                Object value = getIntent().getExtras().get(key);
                Log.d(TAG, "Key: " + key + " Value: " + value);
            }
        }*/

        subscribeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseMessaging.getInstance().subscribeToTopic("sports");
                Log.d("Subscribe", "Sports");

                /*String msg = getString(R.string.msg_subscribed);
                Log.d(TAG, msg);
                Toast.makeText(Sports.this, msg, Toast.LENGTH_SHORT).show();*/
            }
        });

        dref = FirebaseDatabase.getInstance().getReference().child("sports");
        dref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("Something:",dataSnapshot.toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
                dref.addChildEventListener(new ChildEventListener() {

                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                        for(DataSnapshot item:dataSnapshot.getChildren()) {
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
}
