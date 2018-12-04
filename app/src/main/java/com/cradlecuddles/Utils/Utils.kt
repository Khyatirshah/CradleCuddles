package com.cradlecuddles.Utils

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

import com.cradlecuddles.MainActivity
import com.cradlecuddles.R

import java.util.Locale

/**
 * Created by Khyati Shah on 11/19/2018.
 */
object Utils {

    private val BACK_STACK_ROOT_TAG = "root_fragment"

    fun addFragment(context: Context, fragment: Fragment) {
        val fragmentManager = (context as AppCompatActivity).supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.content_frame, fragment).commit()

    }

    fun addSubscreen(context: Context, fragment: Fragment) {
        val fragmentManager = (context as AppCompatActivity).supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.content_frame, fragment).addToBackStack(BACK_STACK_ROOT_TAG).commit()

    }

    fun replaceFragment(context: Context, fragment: Fragment) {
        val fragmentManager = (context as AppCompatActivity).supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.content_frame, fragment).commit()
    }

}
