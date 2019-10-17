package com.example.listhw;

import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimary));
        }
        initList();
    }

    public void initList(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction;

        if(fragmentManager.findFragmentById(R.id.main_content) == null){
            fragmentManager.beginTransaction()
                    .add(R.id.main_content,new ListFragment())
                    .commit();
        }
    }

    public void numberOnClick(int number) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment currentFragment = fragmentManager.findFragmentById(R.id.main_content);

        if (currentFragment instanceof ListFragment) {
            transaction.replace(R.id.main_content, OnScreenFragment.newInstance(number));
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }
}
