package com.rathana.local_storage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.rathana.local_storage.data.UserPref;
import com.rathana.local_storage.model.User;

public class LoginActivity extends AppCompatActivity {

    EditText userName, password;
    Button btnLogin;
    TextView btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName=findViewById(R.id.userName);
        password=findViewById(R.id.password);
        btnLogin=findViewById(R.id.btnLogin);

        btnRegister=findViewById(R.id.register);
        btnRegister.setOnClickListener(v->{
            startActivity(new Intent(this,RegisterActivity.class));
        });

        btnLogin.setOnClickListener(v->{
            User user = UserPref.read(this);
            if(userName.getText().toString().equals(user.getName())&&
            password.getText().toString().equals(user.getPassword())){
                UserPref.login(this);
                startActivity(new Intent(this,MainActivity.class));
                finish();
            }

        });

    }
}
