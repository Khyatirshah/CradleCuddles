package com.cradlecuddles.adapters

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

import com.cradlecuddles.R
import com.cradlecuddles.interfaces.NavigationItemListener
import com.cradlecuddles.models.NavigationItem

import java.util.ArrayList

/**
 * Created by Khyati Shah on 11/26/2018.
 */
class NavigationAdapter(internal var navigationItems: ArrayList<NavigationItem>, internal var navigationItemListener: NavigationItemListener) : RecyclerView.Adapter<NavigationAdapter.MyViewHolder>() {
    internal var selectedIndex = 0
    internal lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NavigationAdapter.MyViewHolder {
        context = parent.context
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.drawer_list_item, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NavigationAdapter.MyViewHolder, position: Int) {
        holder.txtTitle.text = navigationItems[position].strTitle
        holder.imgIcon.setImageResource(navigationItems[position].iconId)

        holder.llParent.setOnClickListener {
            navigationItemListener.onNavigationItemClicked(position)
            selectedIndex = position
            notifyDataSetChanged()
        }
        if (position == selectedIndex) {
            holder.llParent.setBackgroundColor(ContextCompat.getColor(context, R.color.colorBackgroundGray))
            holder.txtTitle.setTextColor(ContextCompat.getColor(context, R.color.colorWhite))
        } else {
            holder.llParent.setBackgroundColor(ContextCompat.getColor(context, R.color.colorWhite))
            holder.txtTitle.setTextColor(ContextCompat.getColor(context, R.color.colorText))
        }
    }

    override fun getItemCount(): Int {
        return navigationItems.size
    }


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var imgIcon: ImageView
        internal var txtTitle: TextView
        internal var llParent: LinearLayout

        init {
            imgIcon = itemView.findViewById(R.id.imgIcon)
            txtTitle = itemView.findViewById(R.id.txtTitle)
            llParent = itemView.findViewById(R.id.llParent)
        }
    }
}
