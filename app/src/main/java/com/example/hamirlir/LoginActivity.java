package com.example.hamirlir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hamirlir.databasehelper.DBHelper;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button login;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        username = (EditText) findViewById(R.id.nameEdt);
        password = (EditText) findViewById(R.id.passEdt);

        login = (Button) findViewById(R.id.btnLog);
        DB = new DBHelper(this);

        DB.openDataBase();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(user.equals("")||pass.equals("")){
                    Toast.makeText(LoginActivity.this, "Please fill in user and password fields", Toast.LENGTH_SHORT).show();
                }else {
                    Boolean checkUserPass = DB.checkUsernamePassword(user,pass);
                    if(checkUserPass == true){
                        Toast.makeText(LoginActivity.this, "Sign in Successfully!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void register(View view) {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }
}