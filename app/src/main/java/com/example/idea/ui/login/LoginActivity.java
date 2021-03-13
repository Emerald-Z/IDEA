package com.example.idea.ui.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;


public class LoginActivity extends MyAppCompactActivity {
    Context c = this;

    private static final String TAG = "MyActivity";
    //User keys for session
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String ID = "idKey";
    public static final String FirstName = "firstnameKey";
    public static final String LastName = "lastnameKey";
    public static final String Email = "emailKey";
    public static final String SchoolInfoID = "schoolInfoIdKey";

    //SchoolInfo Key
    public static final String _ID = "id2Key";
    public static final String SCHOOL_NAME = "schoolNameKey";
    public static final String DAYOFTHEWEEK = "dayOfWeekKey";
    public static final String MYTIME = "myTimeKey";
    public static final String THEIRTIME = "theirTimeKey";
    public static final String CLASSLEVEL = "classLevelKey";
    public static final String CLASSTYPE = "classTypeKey";
    public static final String SCHOOL_SPONSOR = "sponsorKey";

    SharedPreferences sharedpreferences;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //Register button
        Button register = (Button) findViewById(R.id.Register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });


        Button login = (Button) findViewById(R.id.login);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText usernameEditText = findViewById(R.id.username);
                EditText passwordEditText = findViewById(R.id.password);
                String URL = "http://" + IPAddress + "/test.php?action=login";

                StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        if (s.equals("false")) {
                            Toast.makeText(LoginActivity.this, "Incorrect Details", Toast.LENGTH_LONG).show();
                        } else {
                            JSONObject reader = null;
                            String id = null;
                            String firstname = null;
                            String lastname = null;
                            String dayoftheweek = null;
                            String mytime = null;
                            String theirtime = null;
                            String gradelevel = null;
                            String classtype = null;
                            String schoolid = null;
                            String schoolname = null;
                            String sponsor = null;
                            try {
                                reader = new JSONObject(s);
                                JSONObject user  = reader.getJSONObject("result");
                                JSONObject classinfo = reader.getJSONObject("class");
                                JSONObject schoolinfo  = reader.getJSONObject("schoolinfo");

                                id = user.getString("id");
                                firstname = user.getString("first_name");
                                lastname = user.getString("last_name");
                                dayoftheweek = classinfo.getString("day_of_the_week");
                                mytime = classinfo.getString("time");
                                theirtime = classinfo.getString("foreign_time");
                                gradelevel = classinfo.getString("level");
                                classtype = classinfo.getString("name");
                                schoolid = classinfo.getString("school_id");
                                schoolname = schoolinfo.getString("school_name");
                                sponsor = schoolinfo.getString("sponsor");

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.putString(ID, id);
                            editor.putString(FirstName, firstname);
                            editor.putString(LastName, lastname);
                            editor.putString(Email, usernameEditText.getText().toString());
                            editor.putString(DAYOFTHEWEEK, dayoftheweek);
                            editor.putString(MYTIME, mytime);
                            editor.putString(THEIRTIME, theirtime);
                            editor.putString(CLASSLEVEL, gradelevel);
                            editor.putString(CLASSTYPE, classtype);
                            editor.putString(SchoolInfoID, schoolid);
                            editor.putString(SCHOOL_NAME, schoolname);
                            editor.putString(SCHOOL_SPONSOR, sponsor);

                            editor.commit();

                            Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(LoginActivity.this, "Some error occurred -> " + volleyError, Toast.LENGTH_LONG).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> parameters = new HashMap<String, String>();
                        parameters.put("email", usernameEditText.getText().toString());
                        parameters.put("password", passwordEditText.getText().toString());
                        return parameters;
                    }
                };

                RequestQueue rQueue = Volley.newRequestQueue(LoginActivity.this);
                rQueue.add(request);


            }
        });
    }
}


