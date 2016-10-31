package com.jaemin.android.firebase_auth;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Jaemin on 2016. 10. 28..
 */

@IgnoreExtraProperties
public class User {

    public String username;
    public String email;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

}