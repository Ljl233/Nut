package com.example.nut.ui.home.finish;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.nut.R;
import com.example.nut.database.Task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FinishFragment extends DialogFragment {

    private TextView mNutNum, mContent, mToday;
    private Button button;
    private ImageView imageView;

    public static Bundle getBundle(Task task,int nutNum){
        Bundle args = new Bundle();
        args.putString("content", task.getContent());
        args.putInt("nut", nutNum);

        return args;
    }

    public static FinishFragment newInstance(Task task, int nutNum) {
        Bundle args = new Bundle();
        args.putString("content", task.getContent());
        args.putInt("nut", nutNum);

        FinishFragment fragment = new FinishFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_finish, container, false);

        mNutNum = root.findViewById(R.id.finish_nut_num);
        mContent = root.findViewById(R.id.finish_tv_content);
        mToday = root.findViewById(R.id.finish_tv_today);
        button = root.findViewById(R.id.finish_close);
        imageView = root.findViewById(R.id.finish_nut_iv);

        if (getArguments() != null) {
            mContent.setText(getArguments().getString("content"));

            int num = getArguments().getInt("nut");
            mNutNum.setText("+" + num);
            addNut(num);
        }

        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("今日任务完成(yyyy-MM-dd)", Locale.CHINA);
        mToday.setText(simpleDateFormat.format(date));


        button.setOnClickListener(v -> dismiss());

        return root;
    }

    private void addNut(int nurNum) {

    }
}
