package com.example.nut.ui.home.create;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.nut.R;

public class CreateFragment extends DialogFragment {

    private ImageView mIvDismiss;
    private RadioGroup mRGCycle, mRGTag1, mRGTag2, mRGTag3;
    private RatingBar mRating;
    private EditText mETTagMore, mETName;
    private Spinner mSpinnerHour, mSpinnerMin;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, android.R.style.Theme_Holo_Light_Dialog_MinWidth);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_create, container, false);
        findView(v);

        return v;
    }

    private void findView(View parent) {
        mIvDismiss = parent.findViewById(R.id.create_close);
        mIvDismiss.setOnClickListener(v -> {
            dismiss();
        });
    }
}
