package com.sky.simplegank;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.sky.simplegank.Welfare.view.WelfareFragment;
import com.sky.simplegank.about.AboutFragment;
import com.sky.simplegank.mvp.view.GankFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    /**
     * 标志变量，当处于同一个Fragment时再次点击它，不进行刷新，避免造成 资源紧张
     */
    private int navigationState = R.id.nav_welfare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        switch2Fragment(WelfareFragment.newInstance(getString(R.string.fragment_welfare)));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_welfare) {
            //当该Fragment处于前台时，再点击这个Item，不进行页面刷新，不做任何操作
            if (navigationState != R.id.nav_welfare) {
                switch2Fragment(WelfareFragment.newInstance(getString(R.string.fragment_welfare)));
                navigationState = R.id.nav_welfare;
            }
        } else if (id == R.id.nav_android) {
            if (navigationState != R.id.nav_android) {
                switch2Fragment(GankFragment.newInstance(getString(R.string.fragment_android)));
                navigationState = R.id.nav_android;
            }
        } else if (id == R.id.nav_ios) {
            if (navigationState != R.id.nav_ios) {
                switch2Fragment(GankFragment.newInstance(getString(R.string.fragment_ios)));
                navigationState = R.id.nav_ios;
            }
        } else if (id == R.id.nav_front) {
            if (navigationState != R.id.nav_front) {
                switch2Fragment(GankFragment.newInstance(getString(R.string.fragment_front_end)));
                navigationState = R.id.nav_front;
            }
        } else if (id == R.id.nav_expand) {
            if (navigationState != R.id.nav_expand) {
                switch2Fragment(GankFragment.newInstance(getString(R.string.fragment_expand)));
                navigationState = R.id.nav_expand;
            }
        } else if (id == R.id.nav_app) {
            if (navigationState != R.id.nav_app) {
                switch2Fragment(GankFragment.newInstance(getString(R.string.fragment_app)));
                navigationState = R.id.nav_app;
            }
        } else if (id == R.id.nav_about) {
            if (navigationState != R.id.nav_about) {
                switch2Fragment(AboutFragment.newInstance(getString(R.string.fragment_about)));
                navigationState = R.id.nav_about;
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void switch2Fragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container_view, fragment);
        transaction.commit();
    }
}
