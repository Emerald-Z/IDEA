package com.example.idea.ui.hours;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.idea.EditAccountLogoutActivity;
import com.example.idea.MainActivity;
import com.example.idea.R;
import com.example.idea.ui.login.LoginActivity;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class HoursBottomSheetFragment extends BottomSheetDialogFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.hours_open_bottom_sheet, container, false);

        Button class_button = v.findViewById(R.id.teaching_button);
        Button event_button = v.findViewById(R.id.events_button);

        class_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getActivity(), R.string.text_teaching, Toast.LENGTH_SHORT).show();
                //dismiss();
                startActivity(new Intent(getActivity(), ClassHoursFormActivity.class));
            }
        });

        event_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getActivity(), R.string.text_events_including_donations_and_drives, Toast.LENGTH_SHORT).show();
                //dismiss();
                startActivity(new Intent(getActivity(), EventHoursFormActivity.class));
            }
        });

        return v;
    }

}