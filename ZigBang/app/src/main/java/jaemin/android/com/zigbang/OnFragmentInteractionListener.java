package jaemin.android.com.zigbang;

import android.net.Uri;

import com.google.firebase.database.DatabaseReference;

public interface OnFragmentInteractionListener {
    void onFragmentInteraction(Uri uri);
    DatabaseReference getRoomReference();
}