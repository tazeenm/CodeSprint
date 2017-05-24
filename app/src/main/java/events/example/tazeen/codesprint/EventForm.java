package events.example.tazeen.codesprint;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.EventListener;

import events.com.example.tazeen.codesprint.R;

public class EventForm extends AppCompatActivity {

    public CheckBox sportsCheckBox;
    public CheckBox culturalCheckBox;
    public CheckBox edcCheckBox;
    public CheckBox voiseCheckBox;

    public TextView categoryTextView;
    public EditText eventEditText;
    private Button submitBtn;
    //private String event;

    private static String TAG = "EventForm";
    private static final String eventState = "EventText";

    FirebaseDatabase fbdatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_form);

        sportsCheckBox = (CheckBox) findViewById(R.id.sports_checkBox);
        culturalCheckBox = (CheckBox) findViewById(R.id.cultural_checkBox);
        edcCheckBox = (CheckBox) findViewById(R.id.edc_checkBox);
        voiseCheckBox = (CheckBox) findViewById(R.id.voise_checkBox);

        categoryTextView = (TextView) findViewById(R.id.category_textView);
        eventEditText = (EditText) findViewById(R.id.event_editText);
        submitBtn = (Button) findViewById(R.id.event_submit_btn);
        //FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        fbdatabase = FirebaseDatabase.getInstance();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sportsCheckBox.isChecked()) {
                    DatabaseReference myRef = fbdatabase.getReference("sports");
                    myRef = myRef.push();
                    myRef.child("sports").setValue(eventEditText.getText().toString());
                } else if(culturalCheckBox.isChecked()) {
                    DatabaseReference myRef = fbdatabase.getReference("culturalActivities");
                    myRef = myRef.push();
                    myRef.child("culturalActivities").setValue(eventEditText.getText().toString());
                } else if(edcCheckBox.isChecked()) {
                    DatabaseReference myRef = fbdatabase.getReference("edc");
                    myRef = myRef.push();
                    myRef.child("edc").setValue(eventEditText.getText().toString());
                } else if(voiseCheckBox.isChecked()) {
                    DatabaseReference myRef = fbdatabase.getReference("voise");
                    myRef = myRef.push();
                    myRef.child("voise").setValue(eventEditText.getText().toString());
                }
                Toast.makeText(EventForm.this, "Event Added!", Toast.LENGTH_SHORT).show();
                Intent home = new Intent(EventForm.this, MainActivity.class);
                startActivity(home);
            }
        });


    }

    @Override
    public void onBackPressed() {
        backButtonHandler();
        return;
    }

    private void backButtonHandler() {

        Intent intent = new Intent(EventForm.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(eventState, eventEditText.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        eventEditText.setText(savedInstanceState.getString(eventState));
        super.onRestoreInstanceState(savedInstanceState);

    }
}
