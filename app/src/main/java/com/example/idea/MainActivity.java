package com.example.idea;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.example.idea.ui.calendar.CalendarActivity;
import com.example.idea.ui.contact_us.ContactFormActivity;
import com.example.idea.ui.hours.HoursActivity;
import com.example.idea.ui.school_information.SchoolInformationActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Button
        Button button = (Button) findViewById(R.id.HelloButton);
        sharedpreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        button.setText("Hello " + sharedpreferences.getString("fullnameKey", null) + sharedpreferences.getString("idKey", null) +
                sharedpreferences.getString("schoolNameKey", null));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, EditAccountLogoutActivity.class));
            }
        });

//Configure Webview
        WebView myWebView = findViewById(R.id.ideaportal_view);
        //enable javascript
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new MyWebViewClient());
        myWebView.loadUrl("http://www.ideaportal.org/");

//BottomNavigation
        BottomNavigationView navView = findViewById(R.id.nav_view);
       // BottomNavigationViewHelper.disableShiftMode(navView);
        //navView.setOnNavigationItemSelectedListener(this);
        Menu menu = navView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        break;

                    case R.id.navigation_hours:
                        Intent intent1 = new Intent(MainActivity.this, HoursActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.navigation_contact_us:
                        Intent intent2 = new Intent(MainActivity.this, ContactFormActivity.class);
                        startActivity(intent2);
                        break;

                    case R.id.navigation_school_info:
                        Intent intent3 = new Intent(MainActivity.this, SchoolInformationActivity.class);
                        startActivity(intent3);
                        break;

                    case R.id.navigation_calendar:
                        Intent intent4 = new Intent(MainActivity.this, CalendarActivity.class);
                        startActivity(intent4);
                        break;
                }


                return false;
            }


        });
    }
}

class MyWebViewClient extends WebViewClient {
    @Override
    //show the web page in webview but not in web browser
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl (url);
        return true;
    }
}



            // Passing each menu ID as a set of Ids because each
            // menu should be considered as top level destinations.
      /*  AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_school_info)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

       */






