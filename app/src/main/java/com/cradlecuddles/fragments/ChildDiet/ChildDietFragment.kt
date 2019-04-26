package com.cradlecuddles.fragments.ChildDiet

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cradlecuddles.MainActivity
import com.cradlecuddles.R
import com.cradlecuddles.adapters.childcare_diet.ChildCareDietAdapter
import com.cradlecuddles.foundation.BaseFragment
import java.util.*


class ChildDietFragment : BaseFragment() {

    private lateinit var rvAgeList: RecyclerView
    private lateinit var childCareDietAdapter: ChildCareDietAdapter

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
        val view = inflater!!.inflate(R.layout.fragment_child_diet, container, false)
        rvAgeList = view.findViewById(R.id.rvAgeList)
        rvAgeList.layoutManager = LinearLayoutManager(activity)
        val arrAgeList = ArrayList<String>(Arrays.asList(*resources.getStringArray(R.array.ageChildDiet)))
        childCareDietAdapter = ChildCareDietAdapter(arrAgeList)
        rvAgeList.adapter = childCareDietAdapter
        return view
    }
}