package com.rathana.local_storage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.rathana.local_storage.data.UserPref;
import com.rathana.local_storage.model.User;

import org.w3c.dom.Text;

public class RegisterActivity extends AppCompatActivity {

    EditText userName, password;
    Button btnRegister;
    TextView btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userName = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        btnRegister = findViewById(R.id.btnRegister);
        btnLogin = findViewById(R.id.login);

        btnLogin.setOnClickListener(v->{
            startActivity(new Intent(this,LoginActivity.class));
            finish();
        });

        btnRegister.setOnClickListener(v->{
            User user = new User(
                    userName.getText().toString(),
                    password.getText().toString(),
                    false
            );
            UserPref.save(this,user);
            startActivity(new Intent(this,LoginActivity.class));
            finish();
        });
    }
}
