package com.andygeeks.musica.fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andygeeks.musica.R;
import com.andygeeks.musica.adapters.MyMusicListAdapter;
import com.andygeeks.musica.pojo.AudioPojo;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyMusicListFragment extends Fragment {

    View rootView;
//    RecyclerView mRecyclerView;
//    RecyclerView.LayoutManager mLayoutManager;
//    RecyclerView.Adapter mAdapter;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    public MyMusicListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_my_music_list, container, false);

//        mRecyclerView = rootView.findViewById(R.id.myMusicList_recyclerView);
//
//        mLayoutManager = new LinearLayoutManager(getContext());
//        mRecyclerView.setLayoutManager(mLayoutManager);
//
//        mAdapter = new MyMusicListAdapter(getAudioPojos());
//        mRecyclerView.setAdapter(mAdapter);

        viewPager = rootView.findViewById(R.id.viewpager);
//        setupViewPager(viewPager);

        MyMusicListAdapter adapter = new MyMusicListAdapter(getActivity(), getActivity().getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        tabLayout = rootView.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        return rootView;
    }

    private ArrayList<AudioPojo> getAudioPojos() {


        return null;
    }

}
