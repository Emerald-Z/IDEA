package com.example.idea.ui.login;

import androidx.appcompat.app.AppCompatActivity;

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
        private EditText fullNameText;

        private DBManager dbManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Register");
        setContentView(R.layout.activity_register);

        emailText = (EditText) findViewById(R.id.update_email);
        passwdText = (EditText) findViewById(R.id.update_password);
        fullNameText = (EditText) findViewById(R.id.update_full_name);

        addBtn = (Button) findViewById(R.id.logout);

        dbManager = new DBManager(this);
        dbManager.open();
        addBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.logout:
                final String email = emailText.getText().toString();
                final String passwd = passwdText.getText().toString();
                final String full_name = fullNameText.getText().toString();
                dbManager.insert(full_name, email, passwd);
                Toast.makeText(getBaseContext(), "Your account has been created. Welcome" , Toast.LENGTH_SHORT ).show();


        }
    }

}