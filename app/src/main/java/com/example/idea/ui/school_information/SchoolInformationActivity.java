package com.example.idea.ui.school_information;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.idea.MainActivity;
import com.example.idea.R;
import com.example.idea.ui.calendar.CalendarActivity;
import com.example.idea.ui.contact_us.ContactFormActivity;
import com.example.idea.ui.hours.HoursActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SchoolInformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_information);











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

