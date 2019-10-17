package com.example.listhw;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class OnScreenFragment extends Fragment {
    private int mNumber;

    public static OnScreenFragment newInstance(int number) {
        OnScreenFragment onScreenFragment = new OnScreenFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("Number", number);
        onScreenFragment.setArguments(bundle);
        return onScreenFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNumber = getArguments().getInt("Number", -1);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.number_on_screen, container, false);
        TextView textNum = view.findViewById(R.id.fullscreen_number);
        textNum.setText(String.valueOf(mNumber));
        if (mNumber % 2 != 0) {
            textNum.setTextColor(getResources().getColor(R.color.blue));
        } else {
            textNum.setTextColor(getResources().getColor(R.color.red));
        }

        return view;
    }
}
