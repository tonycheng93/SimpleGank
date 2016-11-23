package com.sky.simplegank;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
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
import android.view.View;

import com.sky.simplegank.Android.view.AndroidFragment;
import com.sky.simplegank.CompleteApp.view.AppFragment;
import com.sky.simplegank.Expand.view.ExpandFragment;
import com.sky.simplegank.FrontEnd.view.FrontEndFragment;
import com.sky.simplegank.IOS.view.IOSFragment;
import com.sky.simplegank.Welfare.view.WelfareFragment;
import com.sky.simplegank.utils.Debugger;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        switch2Fragment(WelfareFragment.newInstance("WelfareFragment"));
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
            Debugger.d(WelfareFragment.mUserVisibleHint + "");
            //当该Fragment处于前台时，再点击这个Item，不进行页面刷新，不做任何操作
            if (!WelfareFragment.mUserVisibleHint) {
                switch2Fragment(WelfareFragment.newInstance("WelfareFragment"));
            }
        } else if (id == R.id.nav_android) {
            if (!AndroidFragment.mUserVisibleHint) {
                switch2Fragment(AndroidFragment.newInstance("AndroidFragment"));
            }
        } else if (id == R.id.nav_ios) {
            if (!IOSFragment.mUserVisibleHint) {
                switch2Fragment(IOSFragment.newInstance("IOSFragment"));
            }
        } else if (id == R.id.nav_front) {
            if (!FrontEndFragment.mUserVisibleHint){
                switch2Fragment(FrontEndFragment.newInstance("FrontEndFragment"));
            }
        } else if (id == R.id.nav_expand) {
            if (!ExpandFragment.mUserVisibleHint){
                switch2Fragment(ExpandFragment.newInstance("ExpandFragment"));
            }
        } else if (id == R.id.nav_app) {
            if (!AppFragment.mUserVisibleHint){
                switch2Fragment(AppFragment.newInstance("AppFragment"));
            }
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

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
