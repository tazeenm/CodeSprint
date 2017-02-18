package events.example.tazeen.codesprint;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import events.com.example.tazeen.codesprint.R;

public class Register extends AppCompatActivity {

    private EditText register_name;
    private EditText register_username;
    private EditText register_phone;
    private EditText register_password;
    private Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register_name = (EditText) findViewById(R.id.register_name);
        register_username = (EditText) findViewById(R.id.register_username);
        register_phone = (EditText) findViewById(R.id.register_phone);
        register_password = (EditText) findViewById(R.id.register_password);
        registerBtn = (Button) findViewById(R.id.RegisterBtn);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(Register.this, Home.class);
                startActivity(i1);
            }
        });
    }
}
