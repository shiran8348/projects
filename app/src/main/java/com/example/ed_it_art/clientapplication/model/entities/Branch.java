package com.example.ed_it_art.clientapplication.model.entities;

/**
 * Created by USER on 03/12/2017.
 */

public class Branch {
    private String address_branch;
    private int number_parking;
    private int branch_number;


    public String getAddress_branch() {

        return address_branch;
    }

    public void setAddress_branch(String address_branch) {
        this.address_branch = address_branch;
    }

    public int getNumber_parking() {
        return number_parking;
    }

    public void setNumber_parking(int number_parking) {
        this.number_parking = number_parking;
    }

    public int getBranch_number() {
        return branch_number;
    }

    public void setBranch_number(int branch_number) {
        this.branch_number = branch_number;
    }
   public Branch(){

   }
    public Branch(String address, int numberParkingLast, int branchNumber) {
        address_branch = address;
        number_parking = numberParkingLast;
        branch_number = branchNumber;
    }

}
