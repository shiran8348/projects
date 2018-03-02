package com.example.ed_it_art.clientapplication.model.entities;

/**
 * Created by USER on 03/12/2017.
 */

public class ModelCar {

    private String model_name;
    private int year_car;
    private double motor_capacity;
    private boolean gear_box;
    private int chairs; //sport / open / jeep/


    public String getModelName() {
        return model_name;
    }

    public void setModel_name(String model_name) {
        this.model_name = model_name;
    }

    public double getMotor_capacity() {
        return motor_capacity;
    }

    public void setMotor_capacity(double motor_capacity) {
        this.motor_capacity = motor_capacity;
    }

    public boolean isGear_box() {
        return gear_box;
    }

    public void setGear_box(boolean gear_box) {
        this.gear_box = gear_box;
    }

    public int getChairs() {
        return chairs;
    }

    public void setChairs(int chairs) {
        this.chairs = chairs;
    }


    public int getYear_car() {
        return year_car;
    }

    public void setYear_car(int year_car) {
        this.year_car = year_car;
    }

    public ModelCar() {

    }

    public ModelCar(
            String modleName, int year, double motorCapacity,
            boolean gearBox, int chears) {


        model_name = modleName;
        year_car = year;
        gear_box = gearBox;
        motor_capacity = motorCapacity;
        chairs = chears;


    }
}
