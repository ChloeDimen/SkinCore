package com.jetwey.skincore.fragment;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jetwey.skincore.R;
import com.jetwey.skincore.adapter.GirlAdapter;


public class MusicFragment extends Fragment {
    private View mView;
    private RecyclerView mRelView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_music, container, false);
        mRelView = (RecyclerView) mView.findViewById(R.id.rel_view);
        //设置布局管理器
        mRelView.setLayoutManager(new LinearLayoutManager(getContext()));
        GirlAdapter girlAdapter = new GirlAdapter();
        mRelView.setAdapter(girlAdapter);
        return mView;
    }

    @Override
    public LayoutInflater onGetLayoutInflater(Bundle savedInstanceState) {
        return super.onGetLayoutInflater(savedInstanceState);
    }
}


