package com.example.ed_it_art.clientapplication.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ed_it_art.clientapplication.R;
import com.example.ed_it_art.clientapplication.model.entities.Car;

import layout.CarListFragment;
import layout.CarViewFragment;

public class CarsActivity extends AppCompatActivity  implements CarListFragment.carSectionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cars);
    }
    //this gets code by the top fragment when the user clicks the button
    @Override
    public void onCarSelecteted(String choiceCar) {
        CarViewFragment carViewFragment = (CarViewFragment )
                getSupportFragmentManager().findFragmentById(R.id.fragment3);
        carViewFragment.setModelCarSelected(choiceCar);
    }

   /* @Override
    public void onCarSelecteted(String choiceCar) {
        CarListFragment carListFragment = (CarListFragment)getSupportFragmentManager().findFragmentById(R.id.)
    }*/
}
