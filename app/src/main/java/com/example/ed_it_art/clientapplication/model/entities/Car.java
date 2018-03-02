package com.example.ed_it_art.clientapplication.model.entities;

/**
 * Created by USER on 03/12/2017.
 */

public class Car {
    private int fix_branch;
    private String model_car;
    private double kilo_metrage;
    private String car_number;
    private boolean free;

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public int getFix_branch() {
        return fix_branch;
    }

    public void setFix_branch(int fix_branch) {
        this.fix_branch = fix_branch;
    }

    public String getModel_car() {
        return model_car;
    }

    public void setModel_car(String model_car) {
        this.model_car = model_car;
    }

    public double getKilo_metrage() {
        return kilo_metrage;
    }

    public void setKilo_metrage(double kilo_metrage) {
        this.kilo_metrage = kilo_metrage;
    }

    public String getCar_number() {
        return car_number;
    }

    public void setCar_number(String car_number) {
        this.car_number = car_number;
    }

    public  Car(){
    }
    public Car(int fixBranch, String model,
               double kiloMetraje, String carNumber) {
        fix_branch = fixBranch;
        model_car = model;
        kilo_metrage = kiloMetraje;
        car_number = carNumber;
    }
}
