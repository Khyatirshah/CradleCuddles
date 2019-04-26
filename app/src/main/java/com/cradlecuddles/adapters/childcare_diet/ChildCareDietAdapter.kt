package com.cradlecuddles.adapters.childcare_diet

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.cradlecuddles.Utils.Utils
import com.cradlecuddles.fragments.ChildDiet.ChildDietDetailFragment
import java.util.ArrayList
import android.os.Bundle
import com.cradlecuddles.R


class ChildCareDietAdapter(val arrAge: ArrayList<String>) : RecyclerView.Adapter<ChildCareDietAdapter.ViewHolder>() {

    lateinit var context: Context

    override fun getItemCount(): Int {
        return arrAge.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        if (holder != null) {
            holder.txtAge.text = arrAge.get(position)
            holder.rlParent.setOnClickListener {
                val bundle = Bundle()
                bundle.putString("selectedAge", arrAge.get(position))
                val childDietDetailFragment = ChildDietDetailFragment()
                childDietDetailFragment.arguments = bundle
                Utils.replaceFragment(context, childDietDetailFragment)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ChildCareDietAdapter.ViewHolder {
        context = parent!!.context
        return ViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.item_age_diet, parent, false))

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtAge = view.findViewById<TextView>(R.id.txtAge)
        val rlParent = view.findViewById<RelativeLayout>(R.id.rlParent)
    }
}