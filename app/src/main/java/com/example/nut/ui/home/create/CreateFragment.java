package com.example.nut.ui.home.create;

import android.app.TaskStackBuilder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.nut.R;
import com.example.nut.database.Task;
import com.example.nut.ui.home.view.HomeFragment;

import java.util.Date;
import java.util.List;

public class CreateFragment extends DialogFragment {

    private ImageView mIvDismiss;
    private RadioGroup mRGType, mRGTag1, mRGTag2, mRGTag3;
    private RadioButton mRBStudy, mRBWork, mRBSport, mRBLife, mRBHappy, mRBOutgoing, mRBOther;
    private RatingBar mRating;
    private EditText mETTagCustom, mETName;
    private EditText mETHour, mEDMin;
    private Button mConfirm;
    private String mType;
    private String mContent;
    private int star;
    private String mTag;
    private OnConfirmListener mListener;

    public CreateFragment(OnConfirmListener listener) {
        mListener = listener;
    }

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

    private void findView(View root) {
        mIvDismiss = root.findViewById(R.id.create_close);
        mIvDismiss.setOnClickListener(v -> {
            dismiss();
        });

        mRGType = root.findViewById(R.id.create_type_group);
        mRGType.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.create_day:
                    mType = "day";
                    break;
                case R.id.create_week:
                    mType = "week";
                    break;
                case R.id.create_month:
                    mType = "month";
                    break;
                case R.id.create_year:
                    mType = "year";
                    break;
                case R.id.create_custom:
                    mType = "custom";
                    break;
            }
        });

        mRating = root.findViewById(R.id.create_rating);
        mRating.setStepSize(1.0f);
        mRating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                star = (int) rating;
            }
        });
        mETName = root.findViewById(R.id.create_edit_content);

        //tag
        mRGTag1 = root.findViewById(R.id.create_tag_group1);
        mRGTag2 = root.findViewById(R.id.create_tag_group2);
        mRGTag3 = root.findViewById(R.id.create_tag_group3);
        mRBStudy = root.findViewById(R.id.create_tag_study);
        mRBWork = root.findViewById(R.id.create_tag_work);
        mRBSport = root.findViewById(R.id.create_tag_sport);
        mRBLife = root.findViewById(R.id.create_tag_life);
        mRBHappy = root.findViewById(R.id.create_tag_happy);
        mRBOutgoing = root.findViewById(R.id.create_tag_outgoing);
        mRBOther = root.findViewById(R.id.create_tag_other);
        mRGTag1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.create_tag_study:
                        if (mRBStudy.isChecked()) {
                            mRGTag2.clearCheck();
                            mRGTag3.clearCheck();
                            mTag = "学习";
                        }
                        break;
                    case R.id.create_tag_work:
                        if (mRBWork.isChecked()) {
                            mRGTag2.clearCheck();
                            mRGTag3.clearCheck();
                            mTag = "工作";
                        }
                        break;
                    case R.id.create_tag_sport:
                        if (mRBSport.isChecked()) {
                            mRGTag2.clearCheck();
                            mRGTag3.clearCheck();
                            mTag = "运动";
                        }
                        break;
                }
            }
        });
        mRGTag2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.create_tag_life:
                        if (mRBStudy.isChecked()) {
                            mRGTag1.clearCheck();
                            mRGTag3.clearCheck();
                            mTag = "生活";
                        }
                        break;
                    case R.id.create_tag_happy:
                        if (mRBWork.isChecked()) {
                            mRGTag1.clearCheck();
                            mRGTag3.clearCheck();
                            mTag = "娱乐";
                        }
                        break;
                    case R.id.create_tag_outgoing:
                        if (mRBSport.isChecked()) {
                            mRGTag1.clearCheck();
                            mRGTag3.clearCheck();
                            mTag = "出行";
                        }
                        break;
                }
            }
        });
        mRGTag3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.create_tag_other && mRBOther.isChecked()) {
                    mRGTag1.clearCheck();
                    mRGTag2.clearCheck();
                    mTag = "其他";
                }
            }
        });
        //其他tag
        mETTagCustom = root.findViewById(R.id.create_edit_tag_more);

        //预计时间
        mETHour = root.findViewById(R.id.create_schedule_hour);
        mEDMin = root.findViewById(R.id.create_schedule_minute);

        mConfirm = root.findViewById(R.id.create_confirm);
        mConfirm.setOnClickListener(v -> {
            mContent = mETName.getText().toString();
            if (mContent.isEmpty()) {
                Toast.makeText(this.getContext(), "内容不得为空", Toast.LENGTH_SHORT).show();
                return;
            }
            if (mTag.equals("其他")) {
                mTag = mETTagCustom.getText().toString();
                if (mTag.isEmpty()) {
                    Toast.makeText(this.getContext(), "标签不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            Date date = new Date(System.currentTimeMillis());
            int hour = 0;
            if (!mETHour.getText().toString().isEmpty()) {
                hour = Integer.parseInt(mETHour.getText().toString());
                if (hour > 23) hour = 23;
            }

            int minute = 0;
            if (!mEDMin.getText().toString().isEmpty()) {
                minute = Integer.parseInt(mEDMin.getText().toString());
                if (minute > 60) minute = 60;
            }
            int schedule = hour * 60 + minute;
            Task task = new Task(mContent, mType, mTag, star, date, schedule, 0, 0, false, null);
            mListener.confirm(task);
            dismiss();
        });
    }

    public interface OnConfirmListener {
        void confirm(Task task);
    }

}
