package com.example.ed_it_art.clientapplication.model.BackEnd;

import android.content.ContentValues;
import android.content.Context;

import com.example.ed_it_art.clientapplication.model.entities.Branch;
import com.example.ed_it_art.clientapplication.model.entities.Car;
import com.example.ed_it_art.clientapplication.model.entities.Client;
import com.example.ed_it_art.clientapplication.model.entities.ModelCar;
import com.example.ed_it_art.clientapplication.model.entities.Order;

import java.text.ParseException;
import java.util.List;

/**
 * Created by ed-it-art on 20/01/2018.
 */

public interface DBmanager {
    void setIdClient(Integer idClient);
    Integer getIdClient();
    boolean existClient(ContentValues client);
    String addClient(ContentValues client);
    void updateCar(ContentValues car, double km);
    void updateOrder(ContentValues car, double km,double aountFuel);
    List<Client> ListClient();
    List<Branch> ListBranch();
    List<Car> ListCars();
    List<Car> ListFreeCar();
    List<Order>ListOrder();
    List<Car> ListFreeCarsForOneBranch(Integer branch);
    List<Car> ListFreeCarsForRang(int rang);
    List ListModels();
    List<Branch> ListBranchForOneCar(Car car);
    List<Order> ListOrderOpen();
    String newOrder(ContentValues order);
    void closeOrder(Order order,double km,double fuel);
    void setUpdate();
    boolean isUpdate();
    void tenSecCheck();
/*

    public void saveLoginInformation(Context context, String userName, String password);
    public void  setCurrentClient(Client client);
    public Client getCurrentClient();
    public boolean clientExists(Client client);
    public boolean isUser(String username);
    public boolean isPassword(String username, String password);
    public String addUser(ContentValues contentValues);
    public long addClient(ContentValues contentValues);
    public long addOrder(ContentValues contentValues) throws ParseException;
    public Car findCar(long carNum);
    public Client findClient(long clientId);
    public Client findClient(String userName);
    public ModelCar findModel(long modelId);
    public Branch findBranch(long branchNum);
    public Order findOrder(long orderNum);
    public Order clientOrder(long clientId);
    public void updateCar(long carNum, long mileage);
    public List<Client> getClients();
    public List<Branch> getBranches();
    public List<Car> getAvailableCars();
    //    public List<Car> getAvailableCarsFromModel(String model);
    public List<Car> getAvailableCarsInBranch(long branchNum);
    public List<ModelCar> getModels();
    public List<Branch> getBranchesWithModel(long modelID);
    public List<Order> getOpenOrders();
    public long closeOrder(long orderNum, long mileage, int gasLiters);
    public List<Order> recentClosedOrders();
    public int charge(long orderNum, long mileageEnd, int gasLiters);
*/

}
