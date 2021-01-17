package com.example.idea.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.idea.DBManager;
import com.example.idea.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

        private EditText emailText;
        private Button addBtn;
        private EditText passwdText;

        private DBManager dbManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Register");
        setContentView(R.layout.activity_register);

        emailText = (EditText) findViewById(R.id.register_email);
        passwdText = (EditText) findViewById(R.id.register_password);

        addBtn = (Button) findViewById(R.id.create_account);

        dbManager = new DBManager(this);
        dbManager.open();
        addBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.create_account:
                final String email = emailText.getText().toString();
                final String passwd = passwdText.getText().toString();
                dbManager.insert(email, passwd);
                Toast.makeText(getBaseContext(), "Your account has been created. Welcome" , Toast.LENGTH_SHORT ).show();


        }
    }

}