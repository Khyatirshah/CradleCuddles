package com.cradlecuddles.fragments.ChildDiet

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.cradlecuddles.CradleCuddles
import com.cradlecuddles.MainActivity
import com.cradlecuddles.R
import com.cradlecuddles.adapters.childcare_diet.ChildCareDietAdapter
import com.cradlecuddles.db.DataBaseHelper
import com.cradlecuddles.foundation.BaseFragment
import com.cradlecuddles.models.ChildDiet
import java.util.*

class ChildDietDetailFragment : BaseFragment() {

    lateinit var txtAgeDietTitile: TextView
    lateinit var valWhtToFeed: TextView
    lateinit var valHowMuch: TextView
    lateinit var valWhatNot: TextView
    lateinit var valDesc: TextView
    lateinit var txtHeaderFeed: TextView
    lateinit var txtHeaderHowMuch: TextView
    lateinit var txtHeaderNotFeed: TextView
    lateinit var descTop: TextView


    override fun onResume() {
        super.onResume()
        (activity as MainActivity)
                .setActionBarTitle(getString(R.string.ChildDiet))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_child_diet_detail, container, false)
        txtAgeDietTitile = view.findViewById(R.id.txtAgeDietTitile)
        valWhtToFeed = view.findViewById(R.id.valWhtToFeed)
        valHowMuch = view.findViewById(R.id.valHowMuch)
        valWhatNot = view.findViewById(R.id.valWhatNot)
        valDesc = view.findViewById(R.id.valDesc)
        txtHeaderFeed = view.findViewById(R.id.txtHeaderFeed)
        txtHeaderHowMuch = view.findViewById(R.id.txtHeaderHowMuch)
        txtHeaderNotFeed = view.findViewById(R.id.txtHeaderNotFeed)
        descTop = view.findViewById(R.id.descTop)

        var bundle = arguments
        if (bundle != null) {
            var strSelectedAge = bundle.getString("selectedAge")
            var childDiet = CradleCuddles.dataBaseHelper!!.getChildDiet(strSelectedAge)
            if (childDiet != null) {
                initViews(childDiet)
            }
        }
        return view
    }

    fun initViews(childDiet: ChildDiet) {

        if (TextUtils.isEmpty(childDiet.age)) {
            txtAgeDietTitile.visibility = View.GONE
        } else {
            txtAgeDietTitile.text = childDiet.age.replace("\\n", "\n")
        }

        if (TextUtils.isEmpty(childDiet.feed)) {
            txtHeaderFeed.visibility = View.GONE
            valWhtToFeed.visibility = View.GONE
        } else {
            valWhtToFeed.text = childDiet.feed.replace("\\n", "\n")
        }
        if (TextUtils.isEmpty(childDiet.howMuch)) {
            txtHeaderHowMuch.visibility = View.GONE
            valHowMuch.visibility = View.GONE
        } else {
            valHowMuch.text = childDiet.howMuch.replace("\\n", "\n")
        }
        if (TextUtils.isEmpty(childDiet.notToFeed)) {
            txtHeaderNotFeed.visibility = View.GONE
            valWhatNot.visibility = View.GONE
        } else {
            valWhatNot.text = childDiet.notToFeed.replace("\\n", "\n")
        }
        if (TextUtils.isEmpty(childDiet.descTop)) {
            descTop.visibility = View.GONE
        } else {
            descTop.text = childDiet.descTop.replace("\\n", "\n")
        }
        if (TextUtils.isEmpty(childDiet.descBottom)) {
            valDesc.visibility = View.GONE
        } else {
            valDesc.text = childDiet.descBottom.replace("\\n", "\n")
        }


    }
}


