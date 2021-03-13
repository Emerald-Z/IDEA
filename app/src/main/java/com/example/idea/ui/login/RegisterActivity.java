package com.example.idea.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.idea.MainActivity;
import com.example.idea.MyAppCompactActivity;
import com.example.idea.R;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends MyAppCompactActivity {

        private EditText emailText;
        private Button addBtn;
        private EditText passwdText;
        private EditText firstNameText;
        private EditText lastNameText;

        Context c = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Register");
        setContentView(R.layout.activity_register);

        emailText = (EditText) findViewById(R.id.update_email);
        passwdText = (EditText) findViewById(R.id.update_password);
        firstNameText = (EditText) findViewById(R.id.update_first_name);
        lastNameText = (EditText) findViewById(R.id.update_last_name);

        addBtn = (Button) findViewById(R.id.create_account);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String URL = "http://" + IPAddress + "/test.php?action=register";
                StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        if (s.equals("false")) {
                            Toast.makeText(RegisterActivity.this, "Can't Register", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getBaseContext(), "Your account has been created. Welcome", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(RegisterActivity.this, "Some error occurred -> " + volleyError, Toast.LENGTH_LONG).show();
                        ;
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> parameters = new HashMap<String, String>();
                        parameters.put("email", emailText.getText().toString());
                        parameters.put("password", passwdText.getText().toString());
                        parameters.put("first_name", firstNameText.getText().toString());
                        parameters.put("last_name", lastNameText.getText().toString());
                        return parameters;
                    }
                };

                RequestQueue rQueue = Volley.newRequestQueue(RegisterActivity.this);
                rQueue.add(request);

            }


        });
    }

}