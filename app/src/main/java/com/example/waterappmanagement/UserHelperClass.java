package com.example.waterappmanagement;

public class UserHelperClass {

 String fullName, username, idNo, email, phoneNo, address, password;

    public UserHelperClass() {

    }

    public UserHelperClass(String fullName, String username, String idNo, String email, String phoneNo, String address, String password) {
        this.fullName = fullName;
        this.username = username;
        this.idNo = idNo;
        this.email = email;
        this.phoneNo = phoneNo;
        this.address = address;
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
