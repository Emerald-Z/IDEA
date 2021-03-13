package com.ezhang.idea.ui.login;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.ezhang.idea.MyAppCompactActivity;
import com.ezhang.idea.R;

import java.util.HashMap;
import java.util.Map;

public class UpdateAccountActivity extends MyAppCompactActivity {
    private EditText emailText;
    private Button addBtn;
    private EditText passwdText;
    private EditText firstNameText;
    private EditText lastNameText;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_account);

        emailText = (EditText) findViewById(R.id.change_email);
        passwdText = (EditText) findViewById(R.id.change_password);
        firstNameText = (EditText) findViewById(R.id.change_firstname);
        lastNameText = (EditText) findViewById(R.id.change_lastname);

        sharedpreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String first_name = sharedpreferences.getString("first_name", null);
        String last_name = sharedpreferences.getString("last_name", null);
        String email = sharedpreferences.getString("email", null);
        String password = sharedpreferences.getString("password", null);
        String id = sharedpreferences.getString("idKey", null);

        addBtn = (Button) findViewById(R.id.real_update_account);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String URL = "http://" + IPAddress + "/test.php?action=update_account";
                StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        if (s.equals("true")) {
                            Toast.makeText(getBaseContext(), "Your account has been updated", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(UpdateAccountActivity.this, EditAccountLogoutActivity.class));
                        } else {
                            Toast.makeText(UpdateAccountActivity.this, "Can't Update your account", Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(UpdateAccountActivity.this, "Some error occurred -> " + volleyError, Toast.LENGTH_LONG).show();
                        ;
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> parameters = new HashMap<String, String>();
                        if(emailText.getText().toString().matches("")) {
                            parameters.put("email", email);
                        } else { parameters.put("email", emailText.getText().toString());}

                        if(passwdText.getText().toString().matches("")) {
                            parameters.put("password", password);
                        } else {parameters.put("password", passwdText.getText().toString());}

                        if(emailText.getText().toString().matches("")) {
                            parameters.put("first_name", first_name);
                        } else {parameters.put("first_name", firstNameText.getText().toString());}

                        if(emailText.getText().toString().matches("")) {
                            parameters.put("last_name", last_name);
                        } else {parameters.put("last_name", lastNameText.getText().toString());}

                        parameters.put("id", id);
                        return parameters;
                    }
                };

                RequestQueue rQueue = Volley.newRequestQueue(UpdateAccountActivity.this);
                rQueue.add(request);

            }


        });
    }
}