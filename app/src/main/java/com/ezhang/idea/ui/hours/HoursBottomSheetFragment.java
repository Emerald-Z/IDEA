package com.ezhang.idea.ui.hours;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.ezhang.idea.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class HoursBottomSheetFragment extends BottomSheetDialogFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        {
            View v = inflater.inflate(R.layout.hours_open_bottom_sheet, container, false);

            Button class_hours = v.findViewById(R.id.teaching_button);
            Button event_hours = v.findViewById(R.id.events_button);

            class_hours.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), ClassHoursFormActivity.class));
                }
            });

            event_hours.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), EventHoursFormActivity .class));
                }
            });
            return v;
        }

    }
}