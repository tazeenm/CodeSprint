package events.example.tazeen.codesprint;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import events.com.example.tazeen.codesprint.R;

public class AdminLogin extends AppCompatActivity {

    private Button signinBtn;
    private EditText admin_email;
    private EditText admin_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        signinBtn = (Button) findViewById(R.id.admin_signin_btn);
        admin_email = (EditText) findViewById(R.id.admin_email);
        admin_password = (EditText) findViewById(R.id.admin_password);

        signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Intent dbForm = new Intent(AdminLogin.this, )
            }
        });

    }
}
