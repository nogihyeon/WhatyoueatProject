package com.pnu.whatyoueat;

public class UserClass {

    public String foodname;
    public String count;

    public UserClass() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public UserClass(String Foodname, String Count) {
        this.foodname = Foodname;
        this.count = Count;
    }
}