package com.ezhang.idea.ui.calendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ezhang.idea.ui.contact_us.ContactFormActivity;
import com.ezhang.idea.MainActivity;
import com.ezhang.idea.R;
import com.ezhang.idea.ui.school_information.SchoolInformationActivity;
import com.ezhang.idea.ui.hours.HoursActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CalendarActivity extends AppCompatActivity {
    SharedPreferences sharedpreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        //feed info from database

        TextView dayOftheWeek = (TextView) findViewById(R.id.calendar_day);
        sharedpreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        dayOftheWeek.setText(sharedpreferences.getString("dayOfWeekKey", null));

        TextView mytime = (TextView) findViewById(R.id.my_time);
        mytime.setText(sharedpreferences.getString("myTimeKey", null));

        TextView theirtime = (TextView) findViewById(R.id.calendar_their_time);
        theirtime.setText(sharedpreferences.getString("theirTimeKey", null));

        TextView gradelevel = (TextView) findViewById(R.id.calendar_grade_level);
        gradelevel.setText(sharedpreferences.getString("classLevelKey", null));

        TextView classtype = (TextView) findViewById(R.id.calendar_class_type);
        classtype.setText(sharedpreferences.getString("classTypeKey", null));

        TextView schoolname = (TextView) findViewById(R.id.school_name);
        schoolname.setText(sharedpreferences.getString("schoolNameKey", null));


        BottomNavigationView navView = findViewById(R.id.nav_view);
        // BottomNavigationViewHelper.disableShiftMode(navView);
        //navView.setOnNavigationItemSelectedListener(this);
        Menu menu = navView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        Button notifications = (Button) findViewById(R.id.class_notifications_button);

        notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tittle="Notifications";
                String subject="Welcome!";
                String body="You have subscribed to get push notifications from us!";

                NotificationManager notif=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                Notification notify=new Notification.Builder
                        (getApplicationContext()).setContentTitle(tittle).setContentText(body).
                        setContentTitle(subject).setSmallIcon(R.drawable.idea_logo_2_1).build();

                notify.flags |= Notification.FLAG_AUTO_CANCEL;
                notif.notify(0, notify);
            }
        });
        
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Intent intent0 = new Intent(CalendarActivity.this, MainActivity.class);
                        startActivity(intent0);
                        break;

                    case R.id.navigation_hours:
                        Intent intent1 = new Intent(CalendarActivity.this, HoursActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.navigation_contact_us:
                        Intent intent2 = new Intent(CalendarActivity.this, ContactFormActivity.class);
                        startActivity(intent2);
                        break;

                    case R.id.navigation_school_info:
                        Intent intent3 = new Intent(CalendarActivity.this, SchoolInformationActivity.class);
                        startActivity(intent3);
                        break;

                    case R.id.navigation_calendar:

                        break;
                }


                return false;
            }


        });
    }
}
