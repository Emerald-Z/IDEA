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
import com.example.idea.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;


public class LoginActivity extends AppCompatActivity {
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
                String URL = "http://192.168.254.24/test.php?action=login";

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
                            try {
                                reader = new JSONObject(s);
                                id = reader.getString("id");
                                firstname = reader.getString("first_name");
                                lastname = reader.getString("last_name");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.putString(ID, id);
                            editor.putString(FirstName, firstname);
                            editor.putString(LastName, lastname);
                            editor.putString(Email, usernameEditText.getText().toString());
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


    /*
    public void checkUser(String email, String password) throws IOException {

    }
}

/*OkHttpClient client = new OkHttpClient();

    Request request = new Request.Builder()
            .url(uri)
            .build();

        try (Response response = client.newCall(request).execute()) {
                String result =  response.body().string();
                Log.d("TAG",response.body().string());
                Toast.makeText(getBaseContext(), response.body().string(), Toast.LENGTH_SHORT ).show();
                if(response.body().string().equals("false")) {
                return false;
                }
                return true;
                } catch (IOException e) {
                e.printStackTrace();
                }

                return false;

 */
                /*

                try {
                    if(checkUser(usernameEditText.getText().toString(), passwordEditText.getText().toString())){

                        startActivity(new Intent(LoginActivity.this, MainActivity.class));

                    }
                    else{
                        Toast.makeText(getBaseContext(), "Login Failed" , Toast.LENGTH_SHORT ).show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

 */