package com.example.idea.ui.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.idea.User;
import com.example.idea.MyDB;
import com.example.idea.MainActivity;
import com.example.idea.R;

public class LoginActivity extends AppCompatActivity {
    Context c = this;

    //User keys for session
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String ID = "idKey";
    public static final String FullName = "fullnameKey";
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

    private LoginViewModel loginViewModel;

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

                if(checkUser(usernameEditText.getText().toString(), passwordEditText.getText().toString())){

                    startActivity(new Intent(LoginActivity.this, MainActivity.class));

                }
                else{
                    Toast.makeText(getBaseContext(), "Login Failed" , Toast.LENGTH_SHORT ).show();
                }
            }
        });
    }

    public boolean checkUser(String email, String password){
        MyDB db = new MyDB(c);
        SQLiteDatabase database = db.getWritableDatabase();

        // array of columns to fetch
        String[] columns = {
                MyDB._ID,
                MyDB.FULL_NAME,
                MyDB.EMAIL,
                MyDB.SCHOOL_INFO_ID
        };
        // selection criteria
        String selection = MyDB.EMAIL + " = ?" + " AND " + MyDB.PASSWORD + " = ?";
        // selection arguments
        String[] selectionArgs = {email, password};
        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = database.query(MyDB.TABLE_NAME, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order


        if (cursor.moveToFirst()) {
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString(ID, cursor.getString(cursor.getColumnIndex(MyDB._ID)));
            editor.putString(FullName, cursor.getString(cursor.getColumnIndex(MyDB.FULL_NAME)));
            editor.putString(Email, email);
            editor.putString(SchoolInfoID, cursor.getString(cursor.getColumnIndex(MyDB.SCHOOL_INFO_ID)));


            String[] columns2 = {
                    MyDB._ID_SCHOOL,
                    MyDB.SCHOOL_NAME,
                    MyDB.DAYOFTHEWEEK,
                    MyDB.MYTIME,
                    MyDB.THEIRTIME,
                    MyDB.CLASSLEVEL,
                    MyDB.CLASSTYPE
            };

            String selection2 = MyDB._ID_SCHOOL + " =?";
            String[] selectionArgs2 = {cursor.getString(cursor.getColumnIndex(MyDB.SCHOOL_INFO_ID))};
            Cursor cursor2 = database.query(MyDB.TABLE_NAME_SCHOOL,
                    columns2,
                    selection2,
                    selectionArgs2,
                    null,
                    null,
                    null
            );




            if(cursor2.moveToFirst()) {
                Log.d("sdfsdfsd", "sdfsf");
                editor.putString(_ID, cursor2.getString(cursor2.getColumnIndex(MyDB._ID_SCHOOL)));
                editor.putString(SCHOOL_NAME, cursor2.getString(cursor2.getColumnIndex(MyDB.SCHOOL_NAME)));
                editor.putString(DAYOFTHEWEEK, cursor2.getString(cursor2.getColumnIndex(MyDB.DAYOFTHEWEEK)));
                editor.putString(MYTIME, cursor2.getString(cursor2.getColumnIndex(MyDB.MYTIME)));
                editor.putString(THEIRTIME, cursor2.getString(cursor2.getColumnIndex(MyDB.THEIRTIME)));
                editor.putString(CLASSLEVEL, cursor2.getString(cursor2.getColumnIndex(MyDB.CLASSLEVEL)));
                editor.putString(CLASSTYPE, cursor2.getString(cursor2.getColumnIndex(MyDB.CLASSTYPE)));
            }



            editor.commit();

            cursor.close();
            db.close();
            return true;
        }
        return false;
    }
}

  /*      loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);

        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });

        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    return;
                }
                loadingProgressBar.setVisibility(View.GONE);
                if (loginResult.getError() != null) {
                    showLoginFailed(loginResult.getError());
                }
                if (loginResult.getSuccess() != null) {
                    updateUiWithUser(loginResult.getSuccess());
                }
                setResult(Activity.RESULT_OK);

                //Complete and destroy login activity once successful
                finish();
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginViewModel.login(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString());
                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //help
                Intent MainIntent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(MainIntent);

                loadingProgressBar.setVisibility(View.VISIBLE);
                loginViewModel.login(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());

            }
        });
    }

    private void updateUiWithUser(LoggedInUserView model) {
        String welcome = getString(R.string.login_page_title) + model.getDisplayName();
        // TODO : initiate successful logged in experience
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}

   */