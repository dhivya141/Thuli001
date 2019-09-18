package com.example.thuli001;

public class UserProfile {
    public String username;
    public String userphno;
    public String useraddress;
    public String useremail;
    public float usercredit;

    public UserProfile(String username, String userphno, String useraddress, String useremail){
        this.username=username;
        this.userphno=userphno;
        this.useraddress=useraddress;
        this.useremail=useremail;
        this.usercredit=10;
    }
}
