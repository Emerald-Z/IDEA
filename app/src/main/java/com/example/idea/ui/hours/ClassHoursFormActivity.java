package com.example.idea.ui.hours;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
import com.example.idea.R;
import com.example.idea.ui.login.LoginActivity;
import com.example.idea.ui.login.RegisterActivity;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

public class ClassHoursFormActivity extends AppCompatActivity {

    private String IPAddress = "192.168.254.24";
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_hours_form);
        sharedpreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String user_id = sharedpreferences.getString("idKey", null);

        EditText hoursEditText = findViewById(R.id.class_hours);
        EditText dateEditText = findViewById(R.id.log_date);
        EditText descriptionEditText = findViewById(R.id.class_hours_description);


        Button submit = (Button) findViewById(R.id.submit_class_hours);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String URL = "http://" + IPAddress + "/test.php?action=login_hours";
                StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        if (s.equals("true")) {
                            Toast.makeText(getBaseContext(), "Thank you!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ClassHoursFormActivity.this, HoursActivity.class));
                        } else {
                            Toast.makeText(ClassHoursFormActivity.this, "Can't Submit Hours", Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(ClassHoursFormActivity.this, "Some error occurred -> " + volleyError, Toast.LENGTH_LONG).show();
                        ;
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> parameters = new HashMap<String, String>();
                        parameters.put("user_id", user_id);
                        parameters.put("hour", hoursEditText.getText().toString());
                        parameters.put("date", dateEditText.getText().toString());
                        parameters.put("description", descriptionEditText.getText().toString());
                        parameters.put("type", "Class/Teaching Hours");
                        return parameters;
                    }
                };

                RequestQueue rQueue = Volley.newRequestQueue(ClassHoursFormActivity.this);
                rQueue.add(request);
            }
        });
    }
}