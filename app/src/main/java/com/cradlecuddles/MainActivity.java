package com.cradlecuddles;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.cradlecuddles.Utils.Utils;
import com.cradlecuddles.adapters.NavigationAdapter;
import com.cradlecuddles.fragments.ChildCareFragment;
import com.cradlecuddles.fragments.ExtrasFragment;
import com.cradlecuddles.fragments.MotherCareFragment;
import com.cradlecuddles.fragments.SettingsFragment;
import com.cradlecuddles.interfaces.NavigationItemListener;
import com.cradlecuddles.models.NavigationItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationItemListener {

    private RelativeLayout rlContainar;
    RecyclerView mDrawerList;
    private NavigationAdapter navigationAdapter;
    ArrayList<NavigationItem> navigationItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerList = (RecyclerView) findViewById(R.id.left_drawer);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mDrawerList.setLayoutManager(mLayoutManager);
        mDrawerList.setItemAnimator(new DefaultItemAnimator());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        setNavigationDrawer();
       /* NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);*/

        Utils.addFragment(this, new ChildCareFragment());

    }

    private void setNavigationDrawer() {
        NavigationItem navigationItem1 = new NavigationItem();
        navigationItem1.setIconId(R.mipmap.ic_childcare);
        navigationItem1.setStrTitle("Child Care");
        navigationItems.add(navigationItem1);

        NavigationItem navigationItem2 = new NavigationItem();
        navigationItem2.setStrTitle("Mother Care");
        navigationItem2.setIconId(R.mipmap.ic_mothercare);
        navigationItems.add(navigationItem2);

        NavigationItem navigationItem3 = new NavigationItem();
        navigationItem3.setStrTitle("Extras");
        navigationItem3.setIconId(R.mipmap.ic_extras);
        navigationItems.add(navigationItem3);

        NavigationItem navigationItem4 = new NavigationItem();
        navigationItem4.setStrTitle("Settings");
        navigationItem4.setIconId(R.mipmap.ic_settings);
        navigationItems.add(navigationItem4);

        navigationAdapter = new NavigationAdapter(navigationItems, this);
        mDrawerList.setAdapter(navigationAdapter);
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


    @Override
    public void onNavigationItemClicked(int position) {
        switch (position) {
            case 0:
                Utils.replaceFragment(this, new ChildCareFragment());
                break;

            case 1:
                Utils.replaceFragment(this, new MotherCareFragment());
                break;

            case 2:
                Utils.replaceFragment(this, new ExtrasFragment());
                break;

            case 3:
                Utils.replaceFragment(this, new SettingsFragment());
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }
}
