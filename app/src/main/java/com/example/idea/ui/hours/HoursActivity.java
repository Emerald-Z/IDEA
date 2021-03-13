package com.example.idea.ui.hours;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.idea.MyAppCompactActivity;
import com.example.idea.ui.contact_us.ContactFormActivity;
import com.example.idea.MainActivity;
import com.example.idea.R;
import com.example.idea.ui.school_information.SchoolInformationActivity;
import com.example.idea.ui.calendar.CalendarActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class HoursActivity extends MyAppCompactActivity {
Context context = this;
SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hours);
/*
        Button OpenBottomSheet = findViewById(R.id.open_bottom_sheet);
        OpenBottomSheet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        BottomSheetDialog bottomSheet = new BottomSheetDialog(context);
                        bottomSheet.setContentView(R.layout.hours_open_bottom_sheet);
                        bottomSheet.show();
                    }
        });

 */
//BottomNavigationView
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // BottomNavigationViewHelper.disableShiftMode(navView);
        //navView.setOnNavigationItemSelectedListener(this);
        Menu menu = navView.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);


        // Listview
        sharedpreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String userid = sharedpreferences.getString("idKey", null);

        String URL = "http://" + IPAddress + "/test.php?action=show_hours_by_user";
        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                if (s.equals("false")) {
                    Toast.makeText(HoursActivity.this, "Something went wrong with the connection", Toast.LENGTH_LONG).show();
                } else {
                    //List<String> temp = new ArrayList<String>();
                    final ListView ListView = (ListView) findViewById(R.id.hours_listview);
                    String[] hours = new String[] {};
                    final List<String> hours_list = new ArrayList<String>(Arrays.asList(hours));
                    final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                            (context, android.R.layout.simple_list_item_1, hours_list);
                    ListView.setAdapter(arrayAdapter);
                    JSONArray reader = null;
                    int total_event_hours=0;
                    int total_class_hours=0;
                    try {
                        reader = new JSONArray(s);
                        for (int i = 0; i < s.length(); i++) {
                            JSONObject hour_submitted = reader.getJSONObject(i);
                            String date = hour_submitted.getString("date");
                            String user_hours = hour_submitted.getString("hour");
                            String type = hour_submitted.getString("type");
                            String description = hour_submitted.getString("description");
                            String final_string = date + ", " + user_hours + ", " + type + ", " + description;
                            hours_list.add(final_string);

                            if(type.equals("Event Hours")){
                                total_event_hours += parseInt(user_hours);
                            }
                            else{
                                total_class_hours += parseInt(user_hours);
                            }

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    TextView events = (TextView) findViewById(R.id.num_of_hours_events);
                    events.setText(String.valueOf(total_event_hours));

                    TextView class1 = (TextView) findViewById(R.id.num_of_hours_teaching);
                    class1.setText(String.valueOf(total_class_hours));

                    TextView total = (TextView) findViewById(R.id.total_num_hours);
                    total.setText(String.valueOf(total_class_hours + total_event_hours));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(HoursActivity.this, "Some error occurred -> " + volleyError, Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<String, String>();
                parameters.put("id", userid);
                return parameters;
            }
        };

        RequestQueue rQueue = Volley.newRequestQueue(HoursActivity.this);
        rQueue.add(request);



        //login hours debug
        Button class_hours = (Button) findViewById(R.id.button1);

        class_hours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HoursActivity.this, ClassHoursFormActivity.class));
            }
        });

        Button event_hours = (Button) findViewById(R.id.button2);

        event_hours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HoursActivity.this, EventHoursFormActivity.class));
            }
        });


        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Intent intent0 = new Intent(HoursActivity.this, MainActivity.class);
                        startActivity(intent0);
                        break;

                    case R.id.navigation_hours:
                        break;

                    case R.id.navigation_contact_us:
                        Intent intent2 = new Intent(HoursActivity.this, ContactFormActivity.class);
                        startActivity(intent2);
                        break;

                    case R.id.navigation_school_info:
                        Intent intent3 = new Intent(HoursActivity.this, SchoolInformationActivity.class);
                        startActivity(intent3);
                        break;

                    case R.id.navigation_calendar:
                        Intent intent4 = new Intent(HoursActivity.this, CalendarActivity.class);
                        startActivity(intent4);
                        break;
                }


                return false;
            }


        });


    }
}
