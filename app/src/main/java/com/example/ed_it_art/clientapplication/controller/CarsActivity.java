package com.example.ed_it_art.clientapplication.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ed_it_art.clientapplication.R;
import com.example.ed_it_art.clientapplication.model.entities.Car;

import layout.CarListFragment;

public class CarsActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cars);
    }

   /* @Override
    public void onCarSelecteted(String choiceCar) {
        CarListFragment carListFragment = (CarListFragment)getSupportFragmentManager().findFragmentById(R.id.)
    }*/
}
