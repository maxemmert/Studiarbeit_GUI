package com.superbank;

import com.example.superbank.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CreateFragment extends Fragment {
 
    public CreateFragment() {
    }
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.drawer_layout, container, false);
 
        return rootView;
    }
 
}