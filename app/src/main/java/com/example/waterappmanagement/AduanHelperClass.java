package com.example.waterappmanagement;

public class AduanHelperClass {

 String username, aduan;

    public AduanHelperClass() {

    }

    public AduanHelperClass(String username, String aduan) {
        this.username = username;
        this.aduan = aduan;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAduan() {
        return aduan;
    }

    public void setAduan(String aduan) {
        this.aduan = aduan;
    }
}
