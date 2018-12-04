package com.cradlecuddles

import android.app.Application

import com.cradlecuddles.Utils.DataBaseHelper

/**
 * Created by Khyati Shah on 12/3/2018.
 */
class CradleCuddles : Application() {

    companion object {

        var dataBaseHelper: DataBaseHelper? = null
    }

    override fun onCreate() {
        super.onCreate()
        try {
            //Check & create DB
            dataBaseHelper = DataBaseHelper(myContext = applicationContext)
            dataBaseHelper!!.createDataBase()


        } catch (e: Exception) {
            e.printStackTrace()
        }

    }


}
