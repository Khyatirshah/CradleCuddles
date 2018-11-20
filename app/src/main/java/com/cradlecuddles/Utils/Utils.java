package com.cradlecuddles.Utils;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.cradlecuddles.R;
import com.cradlecuddles.fragments.HomeFragment;

/**
 * Created by Khyati Shah on 11/19/2018.
 */
public class Utils {
    public static void addFragment(Context context, Fragment fragment) {
        FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.add(android.R.id.content, fragment).commit();

    }
}
