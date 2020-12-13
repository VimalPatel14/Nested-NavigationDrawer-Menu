package com.vimal.nestednavigationdrawer.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.vimal.nestednavigationdrawer.R;


public class SecondFragment extends Fragment {

    TextView txt;

    public SecondFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(
                R.layout.fragment_first, container, false);

       txt = v.findViewById(R.id.txt);
        txt.setText("Second Fragment");
        return v;
    }




}