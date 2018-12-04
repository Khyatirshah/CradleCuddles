package com.cradlecuddles.Utils;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.cradlecuddles.MainActivity;
import com.cradlecuddles.R;

import java.util.Locale;

/**
 * Created by Khyati Shah on 11/19/2018.
 */
public class Utils {

    private static final String BACK_STACK_ROOT_TAG = "root_fragment";

    public static void addFragment(Context context, Fragment fragment) {
        FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.content_frame, fragment).commit();

    }

    public static void addSubscreen(Context context, Fragment fragment) {
        FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, fragment).addToBackStack(BACK_STACK_ROOT_TAG).commit();

    }

    public static void replaceFragment(Context context, Fragment fragment) {
        FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, fragment).commit();
    }

}
