package com.example.idea.ui.school_information;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.idea.MainActivity;
import com.example.idea.R;
import com.example.idea.ui.calendar.CalendarActivity;
import com.example.idea.ui.contact_us.ContactFormActivity;
import com.example.idea.ui.hours.HoursActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SchoolInformationActivity extends AppCompatActivity {
    SharedPreferences sharedpreferences;

    private String IPAddress = "192.168.254.24";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_information);

        //database fill in

        sharedpreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String schoolid = sharedpreferences.getString("schoolInfoIdKey", null);

        String URL = "http://www.ideaportal.org/test.php?action=show_user_by_school";
                StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        if (s.equals("false")) {
                            Toast.makeText(SchoolInformationActivity.this, "Something went wrong with the connection", Toast.LENGTH_LONG).show();
                        } else {
                            JSONArray reader = null;
                            try {
                                reader = new JSONArray(s);
                                for (int i = 0; i < s.length(); i++) {
                                    JSONObject user = reader.getJSONObject(i);
                                    String first_name = user.getString("first_name");
                                    String last_name = user.getString("last_name");
                                    String email = user.getString("email");
                                    if (i == 0) {
                                        TextView firstname = (TextView) findViewById(R.id.teacher_1);
                                        firstname.setText(first_name + " " + last_name);
                                        TextView emailtext = (TextView) findViewById(R.id.teacher_1_email);
                                        emailtext.setText(email);
                                    }
                                    else if(i == 1){
                                        TextView firstname2 = (TextView) findViewById(R.id.teacher_2);
                                        firstname2.setText(first_name + " " + last_name);
                                        TextView emailtext2 = (TextView) findViewById(R.id.teacher_2_email);
                                        emailtext2.setText(email);
                                    }


                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(SchoolInformationActivity.this, "Some error occurred -> " + volleyError, Toast.LENGTH_LONG).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> parameters = new HashMap<String, String>();
                        parameters.put("school_id", schoolid);
                        return parameters;
                    }
                };

                RequestQueue rQueue = Volley.newRequestQueue(SchoolInformationActivity.this);
                rQueue.add(request);

        String schoolnamedata = sharedpreferences.getString("schoolNameKey", null);
        TextView schoolname = (TextView) findViewById(R.id.school_info_schoolname);
        schoolname.setText(schoolnamedata);
        String sponsorname = sharedpreferences.getString("sponsorKey", null);
        TextView sponsor = (TextView) findViewById(R.id.sponsor_name);
        sponsor.setText(sponsorname);


        BottomNavigationView navView = findViewById(R.id.nav_view);
        // BottomNavigationViewHelper.disableShiftMode(navView);
        //navView.setOnNavigationItemSelectedListener(this);
        Menu menu = navView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);

        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Intent intent0 = new Intent(SchoolInformationActivity.this, MainActivity.class);
                        startActivity(intent0);
                        break;

                    case R.id.navigation_hours:
                        Intent intent1 = new Intent(SchoolInformationActivity.this, HoursActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.navigation_contact_us:
                        Intent intent2 = new Intent(SchoolInformationActivity.this, ContactFormActivity.class);
                        startActivity(intent2);
                        break;

                    case R.id.navigation_school_info:
                        break;

                    case R.id.navigation_calendar:
                        Intent intent4 = new Intent(SchoolInformationActivity.this, CalendarActivity.class);
                        startActivity(intent4);
                        break;
                }


                return false;
            }


        });

    }
}

