package com.cradlecuddles.models;

/**
 * Created by Khyati Shah on 11/26/2018.
 */
public class NavigationItem {
    int iconId;
    String strTitle;
    int bgColorId;

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public String getStrTitle() {
        return strTitle;
    }

    public void setStrTitle(String strTitle) {
        this.strTitle = strTitle;
    }

    public int getBgColorId() {
        return bgColorId;
    }

    public void setBgColorId(int bgColorId) {
        this.bgColorId = bgColorId;
    }
}
