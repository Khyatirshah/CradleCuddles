package com.cradlecuddles;

import android.app.Application;

import com.cradlecuddles.Utils.DataBaseHelper;

/**
 * Created by Khyati Shah on 12/3/2018.
 */
public class CradleCuddles extends Application {

    public static DataBaseHelper dataBaseHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            //Check & create DB
            dataBaseHelper = new DataBaseHelper(this);
            dataBaseHelper.createDataBase();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
