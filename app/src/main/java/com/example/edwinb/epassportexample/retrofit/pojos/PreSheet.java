package com.example.edwinb.epassportexample.retrofit.pojos;

/**
 * Created by Edwin B on 10/26/2017.
 */

public class PreSheet {

    private String preTitle;
    private String preDosage;
    private String preStartDay;
    private String preTime;
    private String email;

    public PreSheet(String email, String preTitle, String preDosage, String preStartDay, String preTime) {
        this.email = email;
        this.preTitle = preTitle;
        this.preDosage = preDosage;
        this.preStartDay = preStartDay;
        this.preTime = preTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPreTitle() {
        return preTitle;
    }

    public void setPreTitle(String preTitle) {
        this.preTitle = preTitle;
    }

    public String getPreDosage() {
        return preDosage;
    }

    public void setPreDosage(String preDosage) {
        this.preDosage = preDosage;
    }

    public String getPreStartDay() {
        return preStartDay;
    }

    public void setPreStartDay(String preStartDay) {
        this.preStartDay = preStartDay;
    }

    public String getPreTime() {
        return preTime;
    }

    public void setPreTime(String preTime) {
        this.preTime = preTime;
    }
}
