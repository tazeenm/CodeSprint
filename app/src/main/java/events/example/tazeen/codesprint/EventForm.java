package events.example.tazeen.codesprint;

import android.content.Intent;
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

    private TextView categoryTextView;
    public EditText eventEditText;
    private Button submitBtn;
    private String event;

    private static String TAG = "EventForm";

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

        fbdatabase = FirebaseDatabase.getInstance();

        sportsCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                culturalCheckBox.setEnabled(false);
                edcCheckBox.setEnabled(false);
                voiseCheckBox.setEnabled(false);

            }
        });

        culturalCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sportsCheckBox.setEnabled(false);
                edcCheckBox.setEnabled(false);
                voiseCheckBox.setEnabled(false);
            }
        });

        edcCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                culturalCheckBox.setEnabled(false);
                sportsCheckBox.setEnabled(false);
                voiseCheckBox.setEnabled(false);
            }
        });

        voiseCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                culturalCheckBox.setEnabled(false);
                edcCheckBox.setEnabled(false);
                sportsCheckBox.setEnabled(false);
            }
        });


        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // event = eventEditText.getText().toString();
                DatabaseReference myRef = fbdatabase.getReference("sports");
                myRef=myRef.push();
                myRef.child("sports").setValue(eventEditText.getText().toString());
                Toast.makeText(EventForm.this, "Event Added!", Toast.LENGTH_SHORT).show();
                Intent home = new Intent(EventForm.this, MainActivity.class);
                startActivity(home);
            }
        });
    }
}
