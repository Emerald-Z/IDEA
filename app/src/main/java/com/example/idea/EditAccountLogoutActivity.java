package com.example.idea;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.idea.ui.login.LoginActivity;

public class EditAccountLogoutActivity extends AppCompatActivity {
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account_logout);

        Button logout = (Button) findViewById(R.id.logout);
        preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.commit();
                startActivity(new Intent(EditAccountLogoutActivity.this, LoginActivity.class));
            }
        });

    }
}