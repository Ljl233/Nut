package com.example.nut.ui.hole;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nut.R;
import com.example.nut.ui.home.model.HomeRepository;

public class HoleFragment extends Fragment {
    private ImageView mArrow;
    private RecyclerView mRecycler;
    private HoleAdapter mAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_hole, container, false);

        mArrow = root.findViewById(R.id.hole_arrow);
        mRecycler = root.findViewById(R.id.hole_list);

        initAdapter();
        return root;
    }

    private void initAdapter() {
        mAdapter = new HoleAdapter();
        mRecycler.setLayoutManager(new LinearLayoutManager(this.getContext()));
        mRecycler.setAdapter(mAdapter);

    }
}
