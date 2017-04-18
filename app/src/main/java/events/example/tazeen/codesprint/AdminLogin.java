package events.example.tazeen.codesprint;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import events.com.example.tazeen.codesprint.R;

public class AdminLogin extends AppCompatActivity {

    private Button signinBtn;
    private EditText admin_username;
    private EditText admin_password;
    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        signinBtn = (Button) findViewById(R.id.admin_signin_btn);
        admin_username = (EditText) findViewById(R.id.admin_username);
        admin_password = (EditText) findViewById(R.id.admin_password);

        signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = admin_username.getText().toString();
                password = admin_password.getText().toString();
                if(username.equals("Admin") && password.equals("root")) {
                    Intent dbForm = new Intent(AdminLogin.this, EventForm.class);
                    startActivity(dbForm);
                    Toast.makeText(getApplicationContext(),"Signed in",Toast.LENGTH_SHORT).show();
                    Log.d("Success","Signin");
                } else {
                    Intent dbForm1 = new Intent(AdminLogin.this,AdminLogin.class);
                    startActivity(dbForm1);
                    Toast.makeText(getApplicationContext(),"Cannot Sign in",Toast.LENGTH_SHORT).show();
                    Log.d("Cancelled","Signin");
                }
            }
        });

    }
}
