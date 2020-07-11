package com.example.nut.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nut.R;
import com.example.nut.ui.home.data.TodoData;

public class HomeFragment extends Fragment {

    private Toolbar toolbar;
    private RecyclerView todoListView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        todoListView = root.findViewById(R.id.home_list);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        initAdapter();
    }

    private void initAdapter() {
        TodolistAdapter mAdapter = new TodolistAdapter(TodoData.getFakeData());
        todoListView.setAdapter(mAdapter);
    }

}
