package com.example.ed_it_art.clientapplication.controller;

import android.app.AlertDialog;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ed_it_art.clientapplication.R;
import com.example.ed_it_art.clientapplication.model.BackEnd.DBManagerFactory;
import com.example.ed_it_art.clientapplication.model.BackEnd.DBmanager;
import com.example.ed_it_art.clientapplication.model.BranchByCity;
import com.example.ed_it_art.clientapplication.model.CarByModel;
import com.example.ed_it_art.clientapplication.model.entities.Branch;
import com.example.ed_it_art.clientapplication.model.entities.Car;
import com.example.ed_it_art.clientapplication.model.entities.ModelCar;
import com.example.ed_it_art.clientapplication.model.entities.Order;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import layout.BranchListFragment;
import layout.BranchViewFragment;

import static com.example.ed_it_art.clientapplication.model.utils.RentsConst.OrderToContentValues;

public class BranchesActivity extends AppCompatActivity implements BranchListFragment.BranchSectionListener,
BranchListFragment.BranchSelectedListener,
BranchViewFragment.BranchSelectedListner {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branches);
        dBmanager = DBManagerFactory.GetDB();
        findViews();
    }
    int orderNumberChangable = 6;
    String modelString = "";
    int branchNumString;
    String numberCarString;
    Button buttonAprove;
    DBmanager dBmanager;
    ModelCar modelCarFinal = new ModelCar();
    Order finalOrder = new Order();
    Car finalCar = new Car();

    boolean flagBranch = false;
    boolean flagCar = false;

    @Override
    public void onBranchSelecteted(String choiceBranches) {
        BranchViewFragment branchViewFragment = (BranchViewFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment4);
        branchViewFragment.setBranchesSelected(choiceBranches);
    }

    @Override
    public void onBranchSelected(Branch branch) {
        branchNumString = branch.getBranch_number();
 //       Toast.makeText(BranchesActivity.this, "branch this -> " + branchNumString, Toast.LENGTH_SHORT).show();
        flagBranch = true;
    }

    @Override
    public void onBranchSelectedView(ModelCar car) {
        modelString = (car.getModelName());
        modelCarFinal = car;
        flagCar = true;
  //      Toast.makeText(BranchesActivity.this, "car this -> " + modelString, Toast.LENGTH_SHORT).show();
        getTheIdOfTheCar();
        setViews();
    }

    public void findViews() {
        buttonAprove = (Button) findViewById(R.id.branch_button);
    }

    public void setViews() {
        if ((flagBranch) || (flagCar)) {
            buttonAprove.setEnabled(true);
            buttonAprove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v == buttonAprove) {

                        addOrder();
                        Toast.makeText(getBaseContext(), "congradulation you have got a new car!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public void addOrder() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        try {
            finalOrder.setOrder_open(true);
            finalOrder.setNumber_branch(branchNumString);
            finalOrder.setCar_rental_start(dateFormat.format(date));//dateFormat.format(date)
            finalOrder.setId_client(dBmanager.getIdClient());
            finalOrder.setNumber_car(numberCarString);
            finalOrder.setNumber_order(orderNumberChangable);
            new AsyncTask<Object, Object, String>() {
                @Override
                protected void onPostExecute(String s) {
                    if (!s.toUpperCase().startsWith("ERROR")) {
                        Toast.makeText(getBaseContext(), "oooopppssseee", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    orderNumberChangable+=2;
                }

                @Override
                protected String doInBackground(Object... params) {
                    return dBmanager.newOrder(OrderToContentValues(finalOrder));
                }
            }.execute();
        } catch (Exception e) {

        }
    }

    public void getTheIdOfTheCar() {
        try {
            new AsyncTask<Void, Void, List<Car>>() {
                @Override
                protected void onPostExecute(List<Car> cars) {
 //                   Toast.makeText(BranchesActivity.this, "this -> in post", Toast.LENGTH_SHORT).show();
                    for (Car car : cars) {
                        if (car.getModel_car().matches(modelString)) {
 //                           Toast.makeText(BranchesActivity.this, "this ->if1", Toast.LENGTH_SHORT).show();

                            if (car.getFix_branch() == branchNumString) {
                                finalCar.equals(car);
                                numberCarString = car.getCar_number();
                                //                            finalOrder.setNumber_car(car.getCar_number());
                                Toast.makeText(BranchesActivity.this, "this -> " + numberCarString, Toast.LENGTH_SHORT).show();
                                break;
                            }
                            break;
                        }
                    }
                }

                @Override
                protected List<Car> doInBackground(Void... params) {
                    return dBmanager.ListCars();
                }
            }.execute();

        } catch (Exception e) {

        }
    }
}
