package events.example.tazeen.codesprint;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.AdapterViewFlipper;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import events.com.example.tazeen.codesprint.R;

public class CulturalActivities extends AppCompatActivity {
    private Toolbar ToolBar3;

    DatabaseReference dref;
    ArrayList<String> culturalItems = new ArrayList<>();
    ArrayAdapter<String> adapter;
    ListView culturalList;

    private AdapterViewFlipper simpleAdapterViewFlipper;
    int[] slideImages = {R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4, R.drawable.pic5};     // array of images

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cultural_activities);
        ToolBar3 = (Toolbar) findViewById(R.id.toolbar4);
        culturalList = (ListView) findViewById(R.id.cultural_list);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, culturalItems);
        culturalList.setAdapter(adapter);

        simpleAdapterViewFlipper = (AdapterViewFlipper) findViewById(R.id.simpleAdapterViewFlipper);
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(),slideImages);
        simpleAdapterViewFlipper.setAdapter(customAdapter);
        simpleAdapterViewFlipper.setFlipInterval(3000);
        simpleAdapterViewFlipper.setAutoStart(true);

        dref = FirebaseDatabase.getInstance().getReference().child("culturalActivities");
        dref.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                for (DataSnapshot item : dataSnapshot.getChildren()) {
                    Log.d("Snapshot:", item.getValue().toString());
                    culturalItems.add(item.getValue().toString());
                }

                  adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                String item = dataSnapshot.getValue(String.class);
                culturalItems.remove(item);
                adapter.notifyDataSetChanged();

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
