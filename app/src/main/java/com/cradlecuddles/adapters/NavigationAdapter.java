package com.cradlecuddles.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cradlecuddles.R;
import com.cradlecuddles.interfaces.NavigationItemListener;
import com.cradlecuddles.models.NavigationItem;

import java.util.ArrayList;

/**
 * Created by Khyati Shah on 11/26/2018.
 */
public class NavigationAdapter extends RecyclerView.Adapter<NavigationAdapter.MyViewHolder> {

    ArrayList<NavigationItem> navigationItems;
    NavigationItemListener navigationItemListener;
    int selectedIndex = 0;
    Context context;

    public NavigationAdapter(ArrayList<NavigationItem> navigationItems, NavigationItemListener navigationItemListener) {
        this.navigationItems = navigationItems;
        this.navigationItemListener = navigationItemListener;
    }

    @Override
    public NavigationAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.drawer_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NavigationAdapter.MyViewHolder holder, final int position) {
        holder.txtTitle.setText(navigationItems.get(position).getStrTitle());
        holder.imgIcon.setImageResource(navigationItems.get(position).getIconId());

        holder.llParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigationItemListener.onNavigationItemClicked(position);
                selectedIndex = position;
                notifyDataSetChanged();
            }
        });
        if (position == selectedIndex) {
            holder.llParent.setBackgroundColor(ContextCompat.getColor(context, R.color.colorBackgroundGray));
            holder.txtTitle.setTextColor(ContextCompat.getColor(context, R.color.colorWhite));
        } else {
            holder.llParent.setBackgroundColor(ContextCompat.getColor(context, R.color.colorWhite));
            holder.txtTitle.setTextColor(ContextCompat.getColor(context, R.color.colorText));
        }
    }

    @Override
    public int getItemCount() {
        return navigationItems.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgIcon;
        TextView txtTitle;
        LinearLayout llParent;

        public MyViewHolder(View itemView) {
            super(itemView);
            imgIcon = itemView.findViewById(R.id.imgIcon);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            llParent = itemView.findViewById(R.id.llParent);
        }
    }
}
