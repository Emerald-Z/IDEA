package com.example.idea.ui.hours;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.idea.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class HoursBottomSheetFragment extends BottomSheetDialogFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_hours,
                container, false);

        Button algo_button = v.findViewById(R.id.teaching_button);
        Button course_button = v.findViewById(R.id.events_button);

        algo_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getActivity(), R.string.text_teaching, Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });

        course_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getActivity(), R.string.text_events_including_donations_and_drives, Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });

        return v;
    }

}