package com.pnu.whatyoueat;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class Fragment1 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment1, container, false);
        Button button = (Button)rootView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "버튼1", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), RandomActivity.class)); //추가해 줄 ProfileActivity
            }
        });

        Button button2 = (Button)rootView.findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "버튼2", Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }
}
