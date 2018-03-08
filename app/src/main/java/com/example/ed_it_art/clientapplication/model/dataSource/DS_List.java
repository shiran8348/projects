package com.example.ed_it_art.clientapplication.model.dataSource;


import android.content.ContentValues;

import com.example.ed_it_art.clientapplication.model.BackEnd.DBmanager;
import com.example.ed_it_art.clientapplication.model.entities.Branch;
import com.example.ed_it_art.clientapplication.model.entities.Car;
import com.example.ed_it_art.clientapplication.model.entities.Client;
import com.example.ed_it_art.clientapplication.model.entities.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ed-it-art on 20/01/2018.
 */

public class DS_List implements DBmanager {

    static List<Client>clientList;
    static  List<Car>carList;
    static List<Branch>branchList;
    static List<Order> orderList;
    boolean thisUpdate = false;



    static {
        clientList = new ArrayList<>();
        carList = new ArrayList<>();
        branchList = new ArrayList<>();
    }


    @Override
    public void setIdClient(Integer idClient) {

    }

    @Override
    public Integer getIdClient() {
        return null;
    }

    @Override
    public boolean existClient(ContentValues client) {
        return false;
    }

    @Override
    public String addClient(ContentValues client) {
        return null;
    }

    @Override
    public void updateCar(ContentValues car, double km) {

    }

    @Override
    public List<Client> ListClient() {
        return clientList;
    }

    @Override
    public List<Branch> ListBranch() {
        return branchList;
    }

    @Override
    public List<Car> ListCars() {
        return null;
    }


    @Override
    public List<Car> ListFreeCar() {
        List<Car> freeList = null;
        for (Car c:carList) {
            if(c.isFree())
                freeList.add(c);
        }
        return freeList;
    }

    @Override
    public List<Order> ListOrder() {
        return null;
    }

    @Override
    public List<Car> ListFreeCarsForOneBranch(Integer branch) {
        return null;
    }


    @Override
    public List<Car> ListFreeCarsForRang(int rang) {
        List<Car> freeList = null;
        for (Car c:carList) {
            //// TODO: 21/01/2018  RANG
            if (  c.isFree()){
                freeList.add(c);
            }
        }
        return freeList;
    }

    @Override
    public List ListModels() {
        return null;
    }

    @Override
    public List<Branch> ListBranchForOneCar(Car car) {
        List<Branch> freeList = null;
        for (Branch b:branchList)
            if (b.equals(car.getFix_branch()) && car.isFree()){
                freeList.add(b);
        }
        return freeList;
    }

    @Override
    public List<Order> ListOrderOpen() {
        List<Order> freeOrder = null;
        for (Order order :orderList) {
            if (order.isOrder_open())
                freeOrder.add(order);
        }
        return freeOrder;
    }

    @Override
    public String newOrder(ContentValues order) {
        return null;
    }

    @Override
    public void closeOrder(Order order, double km, double fuel) {

    }


    @Override
    public void setUpdate() {
        thisUpdate = true;
    }

    @Override
    public boolean isUpdate() {
        if(thisUpdate){
            thisUpdate = false;
            return true;
        }
        return false;
    }

    @Override
    public void tenSecCheck() {

    }
}
