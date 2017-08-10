package com.example.fragement;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showSound(View view) {
        SoundFragment fragment = new SoundFragment();
        FragmentManager fm = getFragmentManager();
        android.app.FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fl, fragment);
        ft.commit();


    }

    public void showShow(View view) {
        ShowFragment fragment = new ShowFragment();
        FragmentManager fm = getFragmentManager();
        android.app.FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fl, fragment);
        ft.commit();


    }

    public void showStroge(View view) {
        StrogeFragment fragment = new StrogeFragment();
        FragmentManager fm = getFragmentManager();
        android.app.FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fl, fragment);
        ft.commit();


    }
}
