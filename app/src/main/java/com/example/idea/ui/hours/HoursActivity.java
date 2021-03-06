package com.example.idea.ui.hours;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.idea.ui.contact_us.ContactFormActivity;
import com.example.idea.MainActivity;
import com.example.idea.R;
import com.example.idea.ui.school_information.SchoolInformationActivity;
import com.example.idea.ui.calendar.CalendarActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class HoursActivity extends AppCompatActivity {
Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hours);
//ModalBottomSheet Configuration
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
//BottomNavigationView
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // BottomNavigationViewHelper.disableShiftMode(navView);
        //navView.setOnNavigationItemSelectedListener(this);
        Menu menu = navView.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);

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
