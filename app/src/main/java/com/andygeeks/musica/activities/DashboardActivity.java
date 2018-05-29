package com.andygeeks.musica.activities;

import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;

import com.andygeeks.musica.R;
import com.andygeeks.musica.fragments.MyMusicListFragment;
import com.andygeeks.musica.fragments.MyVideoListFragment;

public class DashboardActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        /* Custom Toolbar*/
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);

        drawerLayout = findViewById(R.id.drawer_layout);

        /* Drawer toggle to toggle hamburger to back arrow */
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.open,
                R.string.close);
        drawerLayout.addDrawerListener(toggle);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                menuItem.setChecked(true);
                selectDrawerItem(menuItem);
                return true;
            }
        });


    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment = null;
        Class fragmentClass;
        switch(menuItem.getItemId()) {
            case R.id.nav_home:
                fragmentClass = MyMusicListFragment.class;
                break;
            case R.id.nav_my_music:
                fragmentClass = MyMusicListFragment.class;
                break;
            case R.id.nav_my_video:
                fragmentClass = MyVideoListFragment.class;
                break;
            case R.id.nav_settings:
                fragmentClass = MyMusicListFragment.class;
                break;
            default:
                fragmentClass = MyMusicListFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        drawerLayout.closeDrawers();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(Gravity.START);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
