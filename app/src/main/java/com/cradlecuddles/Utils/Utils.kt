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
import android.graphics.Bitmap.CompressFormat
import android.graphics.Bitmap
import java.io.ByteArrayOutputStream
import android.graphics.BitmapFactory
import android.util.Base64


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

    fun getBitmapAsByteArray(bitmap: Bitmap): String {
        /* val outputStream = ByteArrayOutputStream()
         bitmap.compress(CompressFormat.PNG, 0, outputStream)
         return outputStream.toByteArray()*/
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val b = baos.toByteArray()
        var encodedImageString = Base64.encodeToString(b, Base64.DEFAULT)
        return encodedImageString
    }


}
