package com.cradlecuddles.models;

/**
 * Created by Khyati Shah on 12/3/2018.
 */
public class Vaccination {

    String VaccName;
    String Age;
    Integer Doses;
    String ContentTag;
    String ApproxPrice;

    public String getVaccName() {
        return VaccName;
    }

    public void setVaccName(String vaccName) {
        VaccName = vaccName;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public Integer getDoses() {
        return Doses;
    }

    public void setDoses(Integer doses) {
        Doses = doses;
    }

    public String getContentTag() {
        return ContentTag;
    }

    public void setContentTag(String contentTag) {
        ContentTag = contentTag;
    }

    public String getApproxPrice() {
        return ApproxPrice;
    }

    public void setApproxPrice(String approxPrice) {
        ApproxPrice = approxPrice;
    }
}
