package com.example.ed_it_art.clientapplication.model.entities;

import java.util.Date;

/**
 * Created by USER on 03/12/2017.
 */

public class Order {
    private int id_client;
    private boolean order_open;
    private String number_car;
    private String car_rental_start;
    private String car_rental_end;
    private double start_km;
    private double end_km;
    private boolean had_fuel;
    private double amount_fuel;
    private double final_billing;
    private int number_order;
    private int number_branch;

    public String getCar_rental_start() {
        return car_rental_start;
    }

    public void setCar_rental_start(String car_rental_start) {
        this.car_rental_start = car_rental_start;
    }

    public String getCar_rental_end() {
        return car_rental_end;
    }

    public void setCar_rental_end(String car_rental_end) {
        this.car_rental_end = car_rental_end;
    }

    public int getNumber_branch() {
        return number_branch;
    }

    public void setNumber_branch(int number_branch) {
        this.number_branch = number_branch;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public boolean isOrder_open() {
        return order_open;
    }

    public void setOrder_open(boolean order_open) {
        this.order_open = order_open;
    }

    public String getNumber_car() {
        return number_car;
    }

    public void setNumber_car(String number_car) {
        this.number_car = number_car;
    }


    public double getStart_km() {
        return start_km;
    }

    public void setStart_km(double start_km) {
        this.start_km = start_km;
    }

    public double getEnd_km() {
        return end_km;
    }

    public void setEnd_km(double end_km) {
        this.end_km = end_km;
    }

    public boolean isHad_fuel() {
        return had_fuel;
    }

    public void setHad_fuel(boolean had_fuel) {
        this.had_fuel = had_fuel;
    }

    public double getAmount_fuel() {
        return amount_fuel;
    }

    public void setAmount_fuel(double amount_fuel) {
        this.amount_fuel = amount_fuel;
    }

    public double getFinal_billing() {
        return final_billing;
    }

    public void setFinal_billing(double final_billing) {
        this.final_billing = final_billing;
    }

    public int getNumber_order() {
        return number_order;
    }

    public void setNumber_order(int number_order) {
        this.number_order = number_order;
    }
    public Order(){

    }
}
