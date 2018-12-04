package com.cradlecuddles.fragments

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RadioGroup

import com.cradlecuddles.MainActivity
import com.cradlecuddles.R
import com.cradlecuddles.Utils.Constants
import com.cradlecuddles.Utils.LanguageHelper
import com.cradlecuddles.Utils.Utils
import com.cradlecuddles.foundation.BaseFragment

/**
 * Created by Khyati Shah on 11/27/2018.
 */
class SettingsFragment : BaseFragment() {

    private var llLanguage: LinearLayout? = null

    override fun onResume() {
        super.onResume()
        // Set title bar
        (activity as MainActivity)
                .setActionBarTitle(getString(R.string.Settings))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_settings, container, false)

        llLanguage = view.findViewById(R.id.llLanguage)

        llLanguage!!.setOnClickListener {
            // create a Dialog component
            val dialog = Dialog(activity)

            //tell the Dialog to use the dialog.xml as it's layout description
            dialog.setContentView(R.layout.dialog_language)
            val btnOk = dialog.findViewById<Button>(R.id.btnOk)
            val btnCancel = dialog.findViewById<Button>(R.id.btnCancel)
            val myRadioGroup = dialog.findViewById<RadioGroup>(R.id.myRadioGroup)

            btnOk.setOnClickListener {
                when (myRadioGroup.checkedRadioButtonId) {
                    R.id.english -> LanguageHelper.setLanguage(activity, Constants.ENG)
                    R.id.hindi -> LanguageHelper.setLanguage(activity, Constants.HINDI)
                }
                activity.finish()
                startActivity(Intent(context, MainActivity::class.java))
                dialog.dismiss()
            }
            btnCancel.setOnClickListener { dialog.dismiss() }
            dialog.show()
        }

        return view
    }
}

