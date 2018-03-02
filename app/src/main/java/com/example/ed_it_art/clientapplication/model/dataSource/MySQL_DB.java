package com.example.ed_it_art.clientapplication.model.dataSource;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;

import com.example.ed_it_art.clientapplication.model.BackEnd.DBmanager;
import com.example.ed_it_art.clientapplication.model.entities.Branch;
import com.example.ed_it_art.clientapplication.model.entities.Car;
import com.example.ed_it_art.clientapplication.model.entities.Client;
import com.example.ed_it_art.clientapplication.model.entities.ModelCar;
import com.example.ed_it_art.clientapplication.model.entities.Order;
import com.example.ed_it_art.clientapplication.model.utils.PHPTools;
import com.example.ed_it_art.clientapplication.model.utils.RentsConst;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static com.example.ed_it_art.clientapplication.model.utils.PHPTools.GET;
import static com.example.ed_it_art.clientapplication.model.utils.PHPTools.JsonToContentValues;
import static com.example.ed_it_art.clientapplication.model.utils.PHPTools.POST;
import static com.example.ed_it_art.clientapplication.model.utils.RentsConst.ClientToContentValues;
import static com.example.ed_it_art.clientapplication.model.utils.RentsConst.ContentValuesToCar;

/**
 * Created by Shiran on 22/01/2018.
 */
public class MySQL_DB  implements DBmanager{
    private final String WEB_URL = "http://boukris.vlab.jct.ac.il/phpRentCar/";

    @Override
    public boolean existClient(final ContentValues client) {
        final boolean[] flag = {false};
        try{
            new AsyncTask<Void, Void, List<Client>>() {
                @Override
                protected void onPostExecute(List<Client> clients) {
                    if (clients.contains(client)) {
                        flag[0] = true;
                    }
                }

                @Override
                protected List<Client> doInBackground(Void... params) {
                    return ListClient();
                }
            }.execute();

        }catch (Exception e){

        }
        return flag[0];
    }

    @Override
    public String addClient(ContentValues client) {
        try {
            return POST(WEB_URL + "addClient.php", client);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateCar(final ContentValues car, final double km) {
        try{
            new AsyncTask<Void, Void, List<Car>>() {
                @Override
                protected void onPostExecute(List<Car> cars) {
                    if(cars.contains(car))
                        car.put(RentsConst.CarConst.KILO_METERAJE, km);
                }

                @Override
                protected List<Car> doInBackground(Void... params) {
                    return ListCars();
                }
            }.execute();
        }catch (Exception e){

        }
    }

    @Override
    public List<Client> ListClient() {
        List<Client> result = new ArrayList<Client>();

        try {

            String str = GET(WEB_URL + "getClient.php");
            JSONArray array = new JSONObject(str).getJSONArray("clients");


            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);

                ContentValues contentValues = JsonToContentValues(jsonObject);
                Client client = RentsConst.ContentValuesToClient(contentValues);
                result.add(client);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Branch> ListBranch() {
        List<Branch> result = new ArrayList<Branch>();

        try {

            String str = GET(WEB_URL + "getBranch.php");
            JSONArray array = new JSONObject(str).getJSONArray("branches");


            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                ContentValues contentValues = JsonToContentValues(jsonObject);
                Branch branch = RentsConst.ContentValuesToBranch(contentValues);
                result.add(branch);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Car> ListCars() {
        List<Car> result = new ArrayList<Car>();

        try {

            String str = GET(WEB_URL + "getCar.php");
            JSONArray array = new JSONObject(str).getJSONArray("cars");


            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                ContentValues contentValues = JsonToContentValues(jsonObject);
                Car car =RentsConst.ContentValuesToCar(contentValues);
                result.add(car);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Car> ListFreeCar() {
        final List<Car> freeCar = null;
        try {
            new AsyncTask<Void, Void, List<Car>>() {
                @Override
                protected void onPostExecute(List<Car> cars) {
                    for (Car c : cars) {
                        if (c.isFree())
                            freeCar.add(c);
                    }
                }

                @Override
                protected List<Car> doInBackground(Void... params) {
                    return ListCars();
                }
            }.execute();
        } catch (Exception e) {
        }
        return freeCar;
    }

    @Override
    public List<Order> ListOrder() {
        List<Order> result = new ArrayList<Order>();

        try {

            String str = GET(WEB_URL + "getOrders.php");
            JSONArray array = new JSONObject(str).getJSONArray("orders");

            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);

                ContentValues contentValues = JsonToContentValues(jsonObject);
                Order order = RentsConst.ContentValuesToOrder(contentValues);
                result.add(order);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Car> ListFreeCarsForOneBranch(final Integer branch) {
        final List<Car> freeCar = null;
        try {
            new AsyncTask<Void, Void, List<Car>>() {
                @Override
                protected void onPostExecute(List<Car> cars) {
                    for (Car c : cars) {
                        if (c.getFix_branch() == branch)
                            freeCar.add(c);
                    }
                }

                @Override
                protected List<Car> doInBackground(Void... params) {
                    return ListFreeCar();
                }
            }.execute();
        } catch (Exception e) {
        }
        return freeCar;
    }

    @Override
    public List<Car> ListFreeCarsForRang(int rang) {
        return null;
    }

    @Override
    public List ListModels() {
        List<ModelCar> result = new ArrayList<ModelCar>();

        try {

            String str = GET(WEB_URL + "getModelCar.php");
            JSONArray array = new JSONObject(str).getJSONArray("models");

            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);

                ContentValues contentValues = JsonToContentValues(jsonObject);
                ModelCar modelCar = RentsConst.ContentValuesToModelCar(contentValues);
                result.add(modelCar);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Branch> ListBranchForOneCar(final Car car) {
        final List<Branch>  freeBranches= null;
        try{
            new AsyncTask<Void, Void, List<Branch>>() {
                @Override
                protected void onPostExecute(List<Branch> branches) {
                    for (Branch b:branches) {
                        if(b.getBranch_number() == car.getFix_branch())
                            freeBranches.add(b);
                    }
                }
                protected List<Branch> doInBackground(Void... params) {
                    return ListBranch();
                }
            }.execute();

        }catch (Exception e){
        }
        return freeBranches;
    }

    @Override
    public List<Order> ListOrderOpen() {
        final List<Order>orderOpen=null;
        try {
            new AsyncTask<Void, Void, List<Order>>() {

                @Override
                protected void onPostExecute(List<Order> orders) {
                    for (Order o:orders) {
                        if(o.isOrder_open())
                            orderOpen.add(o);
                    }
                }

                @Override
                protected List<Order> doInBackground(Void... params) {
                    return ListOrder();
                }
            }.execute();
        }catch (Exception e){

        }
        return null;
    }

    @Override
    public String newOrder(ContentValues order) {
        try {
            return POST(WEB_URL + "addOrder.php", order);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void closeOrder(Order order,double km,double fuel) {
        if(order.isOrder_open()) {
            order.setOrder_open(false);
            order.setEnd_km(km);}
        if(fuel == 0)
            order.setHad_fuel(false);
        else {
            order.setHad_fuel(true);
            order.setAmount_fuel(fuel);
        }
    }

    @Override
    public void setUpdate() {

    }

    @Override
    public boolean isUpdate() {
        return false;
    }

    @Override
    public void tenSecCheck() {

    }
}
