package com.andygeeks.musica.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andygeeks.musica.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyVideoListFragment extends Fragment {


    public MyVideoListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the songs_item_layout for this fragment
        return inflater.inflate(R.layout.fragment_my_video_list, container, false);
    }

}
