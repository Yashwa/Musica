package com.andygeeks.musica.fragments;


import android.Manifest;
import android.content.ContentUris;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andygeeks.musica.R;
import com.andygeeks.musica.adapters.SongsAdapter;
import com.andygeeks.musica.pojo.SongsPojo;
import com.andygeeks.musica.utils.TimeUtil;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SongsFragment extends Fragment {

    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 0;
    View rootView;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;

    public SongsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the songs_item_layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_songs, container, false);

        mRecyclerView = rootView.findViewById(R.id.songs_recyclerView);
//
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        checkPermission();

        return rootView;
    }

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            mAdapter = new SongsAdapter(getSongs());
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    private ArrayList<SongsPojo> getSongs() {
        Cursor cursor = null;
        ArrayList<SongsPojo> songs = new ArrayList<>();

        try {
            String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";

            String[] projection = {
                    MediaStore.Audio.Media._ID,
                    MediaStore.Audio.Media.ARTIST,
                    MediaStore.Audio.Media.TITLE,
                    MediaStore.Audio.Media.DATA,
                    MediaStore.Audio.Media.DISPLAY_NAME,
                    MediaStore.Audio.Media.DURATION,
            };

            cursor = getActivity().getContentResolver().query(
                    MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                    projection,
                    selection,
                    null,
                    null);

            while (cursor.moveToNext()) {
                SongsPojo song = new SongsPojo();
                song.setId(cursor.getLong(0));
                song.setArtist(cursor.getString(1));
                song.setTitle(cursor.getString(2));
                song.setData(cursor.getString(3));
                song.setDisplayName(cursor.getString(4));
                song.setDuration(TimeUtil.secondsToTime(cursor.getString(5)));
                song.setAlbumCover(getAlbumCover(cursor.getLong(0)));
                Log.i("SOngList", song.getId() + "||" + song.getArtist() + "||" + song.getTitle() + "||" + song.getData() +
                        "||" + song.getDisplayName() + "||" + cursor.getString(5));
                songs.add(song);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return songs;
    }

    private Bitmap getAlbumCover(Long albumId) {

            Cursor albumCursor = getActivity().getContentResolver().query(
                    MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI,
                    new String[]{MediaStore.Audio.Albums.ALBUM_ART},
                    MediaStore.Audio.Albums._ID + " = ?",
                    new String[]{Long.toString(albumId)},
                    null
            );
            boolean queryResult = albumCursor.moveToFirst();
            String result = null;
            if (queryResult) {
                result = albumCursor.getString(0);
            }
            albumCursor.close();
            return BitmapFactory.decodeFile(result);
        }



//        Uri sArtworkUri = Uri.parse("/storage/emulated/0/Samsung/Music/");
//        Uri uri = ContentUris.withAppendedId(sArtworkUri, album_id);
//        ContentResolver res = getActivity().getContentResolver();
//        InputStream in = null;
//        try {
//            in = res.openInputStream(uri);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        return BitmapFactory.decodeStream(in);

}
