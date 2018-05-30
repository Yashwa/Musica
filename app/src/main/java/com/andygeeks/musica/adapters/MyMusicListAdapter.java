package com.andygeeks.musica.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.andygeeks.musica.R;
import com.andygeeks.musica.fragments.AlbumsFragment;
import com.andygeeks.musica.fragments.ArtistsFragment;
import com.andygeeks.musica.fragments.PlaylistsFragment;
import com.andygeeks.musica.fragments.SongsFragment;

public class MyMusicListAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public MyMusicListAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new PlaylistsFragment();
            case 1:
                return new ArtistsFragment();
            case 2:
                return new AlbumsFragment();
            case 3:
                return new SongsFragment();
            default:
                return new SongsFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        switch (position) {
            case 0:
                return mContext.getString(R.string.title_playLists);
            case 1:
                return mContext.getString(R.string.title_artists);
            case 2:
                return mContext.getString(R.string.title_albums);
            case 3:
                return mContext.getString(R.string.title_songs);
            default:
                return null;
        }
    }
}
