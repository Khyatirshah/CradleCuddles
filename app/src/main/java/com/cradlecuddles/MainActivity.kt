package com.cradlecuddles

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.RelativeLayout

import com.cradlecuddles.Utils.LanguageHelper
import com.cradlecuddles.Utils.Utils
import com.cradlecuddles.adapters.NavigationAdapter
import com.cradlecuddles.fragments.ChildCareFragment
import com.cradlecuddles.fragments.ExtrasFragment
import com.cradlecuddles.fragments.MotherCareFragment
import com.cradlecuddles.fragments.SettingsFragment
import com.cradlecuddles.interfaces.NavigationItemListener
import com.cradlecuddles.models.NavigationItem

import java.util.ArrayList

class MainActivity : AppCompatActivity(), NavigationItemListener {

    private val rlContainar: RelativeLayout? = null
    private lateinit var mDrawerList: RecyclerView
    private var navigationAdapter: NavigationAdapter? = null
    internal var navigationItems = ArrayList<NavigationItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        mDrawerList = findViewById<RecyclerView>(R.id.left_drawer)
        val mLayoutManager = LinearLayoutManager(applicationContext)
        mDrawerList.layoutManager = mLayoutManager
        mDrawerList.itemAnimator = DefaultItemAnimator()

        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        setNavigationDrawer()
        /* NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);*/

        Utils.addFragment(this, ChildCareFragment())

    }

    private fun setNavigationDrawer() {
        val navigationItem1 = NavigationItem()
        navigationItem1.iconId = R.mipmap.ic_childcare
        navigationItem1.strTitle = getString(R.string.ChildCare)
        navigationItems.add(navigationItem1)

        val navigationItem2 = NavigationItem()
        navigationItem2.strTitle = getString(R.string.MotherCare)
        navigationItem2.iconId = R.mipmap.ic_mothercare
        navigationItems.add(navigationItem2)

        val navigationItem3 = NavigationItem()
        navigationItem3.strTitle = getString(R.string.Extras)
        navigationItem3.iconId = R.mipmap.ic_extras
        navigationItems.add(navigationItem3)

        val navigationItem4 = NavigationItem()
        navigationItem4.strTitle = getString(R.string.Settings)
        navigationItem4.iconId = R.mipmap.ic_settings
        navigationItems.add(navigationItem4)

        navigationAdapter = NavigationAdapter(navigationItems, this)
        mDrawerList.adapter = navigationAdapter
    }

    override fun onBackPressed() {
        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LanguageHelper.onAttach(base))
    }

    override fun onNavigationItemClicked(position: Int) {
        when (position) {
            0 -> Utils.replaceFragment(this, ChildCareFragment())

            1 -> Utils.replaceFragment(this, MotherCareFragment())

            2 -> Utils.replaceFragment(this, ExtrasFragment())

            3 -> Utils.replaceFragment(this, SettingsFragment())
        }
        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
    }

    fun setActionBarTitle(title: String) {
        supportActionBar!!.title = title
    }
}
