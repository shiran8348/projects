package com.example.ed_it_art.clientapplication.model.entities;

import java.util.Date;

/**
 * Created by ed-it-art on 12/01/2018.
 */

public class RentRequest {
    private Long clientNumber;
    private boolean openRequestB;
    private Long carNumber;
    private Date dateStartRent;
    private Date dateEndRent;
    private Double kilometrajStart;
    private Double kilometrajEnd;
    private boolean fuelB;
    private double fuelAmount = 0;
    private double finalPayment;
    private int numberRent;

    public Long getClientNumber() {

        return clientNumber;
    }

    public void setClientNumber(Long clientNumber) {
        this.clientNumber = clientNumber;
    }

    public boolean isOpenRequesB() {
        return openRequestB;
    }

    public void setOpenRequesB(boolean openRequesB) {
        this.openRequestB = openRequesB;
    }

    public Long getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(Long carNumber) {
        this.carNumber = carNumber;
    }

    public Date getDateStartRent() {
        return dateStartRent;
    }

    public void setDateStartRent(Date dateStartRent) {
        this.dateStartRent = dateStartRent;
    }

    public Date getDateEndRent() {
        return dateEndRent;
    }

    public void setDateEndRent(Date dateEndRent) {
        this.dateEndRent = dateEndRent;
    }

    public Double getKilometrajStart() {
        return kilometrajStart;
    }

    public void setKilometrajStart(Double kilometrajStart) {
        this.kilometrajStart = kilometrajStart;
    }

    public Double getKilometrajEnd() {
        return kilometrajEnd;
    }

    public void setKilometrajEnd(Double kilometrajEnd) {
        this.kilometrajEnd = kilometrajEnd;
    }

    public boolean isFuelB() {
        return fuelB;
    }

    public void setFuelB(boolean fuelB) {
        this.fuelB = fuelB;
    }

    public double getFuelAmount() {
        return fuelAmount;
    }

    public void setFuelAmount(double fuelAmount) {
        this.fuelAmount = fuelAmount;
    }

    public double getFinalPayment() {
        return finalPayment;
    }

    public void setFinalPayment(double finalPayment) {
        this.finalPayment = finalPayment;
    }

    public int getNumberRent() {
        return numberRent;
    }

    public void setNumberRent(int numberRent) {
        this.numberRent = numberRent;
    }
    public RentRequest(){

    }

    public RentRequest(Long clientNumber, boolean openRequestB, Long carNumber,
                       Date dateStartRent, Date dateEndRent, Double kilometrajStart,
                       Double kilometrajEnd, boolean fuelB, double finalPayment,
                       int numberRent) {

        this.clientNumber = clientNumber;
        this.openRequestB = openRequestB;
        this.carNumber = carNumber;
        this.dateStartRent = dateStartRent;
        this.dateEndRent = dateEndRent;
        this.kilometrajStart = kilometrajStart;
        this.kilometrajEnd = kilometrajEnd;
        this.fuelB = fuelB;
        this.finalPayment = finalPayment;
        this.numberRent = numberRent;
    }
}
