package com.example.ed_it_art.clientapplication.controller;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ed_it_art.clientapplication.R;
import com.example.ed_it_art.clientapplication.model.BackEnd.DBManagerFactory;
import com.example.ed_it_art.clientapplication.model.BackEnd.DBmanager;
import com.example.ed_it_art.clientapplication.model.entities.Branch;
import com.example.ed_it_art.clientapplication.model.entities.Car;
import com.example.ed_it_art.clientapplication.model.entities.ModelCar;
import com.example.ed_it_art.clientapplication.model.entities.Order;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import layout.CarListFragment;
import layout.CarViewFragment;

import static com.example.ed_it_art.clientapplication.model.utils.RentsConst.OrderToContentValues;

public class CarsActivity extends AppCompatActivity  implements CarListFragment.carSectionListener,
        CarViewFragment.carSectionListener,
        CarListFragment.carSelectedListner{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cars);
        dBmanager = DBManagerFactory.GetDB();
        findViews();
    }

    boolean isFlagCar= false;
    int orderNumberChangAble = 7;

    String carNumberString;
    String carModelString;
    int branchNumberString;

    DBmanager dBmanager;
    Button buttonAprove;
    boolean flagBranch;
    boolean flagCar;
    ModelCar modelCar = new ModelCar();
    Branch branch = new Branch();
    Car finalCar = new Car();
    Order finalOrder = new Order();
    protected ModelCar modelCarSelected = new ModelCar();
    protected Branch branchSelected = new Branch();
    //this gets code by the top fragment when the user clicks the button
    @Override
    public void onCarSelecteted(String choiceCar) {
        CarViewFragment carViewFragment = (CarViewFragment )
                getSupportFragmentManager().findFragmentById(R.id.fragment3);
        carViewFragment.setModelCarSelected(choiceCar);

        isFlagCar = true;
    }

    @Override
    public void onCarSelcted2(ModelCar car) {

        modelCarSelected.setChairs(car.getChairs());
        modelCarSelected.setGear_box(car.isGear_box());
        modelCarSelected.setModel_name(car.getModelName());
        modelCarSelected.setMotor_capacity(car.getMotor_capacity());
        modelCarSelected.setYear_car(car.getYear_car());
        carModelString = car.getModelName();
 //       Toast.makeText(CarsActivity.this,"1this -> " + carModelString, Toast.LENGTH_SHORT).show();
  //      Toast.makeText(CarsActivity.this,"2this -> " + carNumberString, Toast.LENGTH_SHORT).show();
        modelCar = car;
//        Toast.makeText(CarsActivity.this,"3this -> " + modelCar.getModelName(), Toast.LENGTH_SHORT).show();
        flagCar = true;

    }
    @Override
    public void onCarSelectetedView(Branch branch1) {

        branchSelected.setAddress_branch(branch1.getAddress_branch());
        branchSelected.setBranch_number(branch1.getBranch_number());
        branchSelected.setNumber_parking(branch1.getNumber_parking());
        branch = branch1;
        branchNumberString = (branch1.getBranch_number());
        flagBranch = true;
        getTheIdOfTheCar();
        setViews();
    }
    public void findViews() {
        buttonAprove = (Button) findViewById(R.id.cars_button);
    }
    public void setViews(){
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
    public void addOrder(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        try{
            finalOrder.setOrder_open(true);
            finalOrder.setNumber_branch(branchNumberString);
            finalOrder.setCar_rental_start(dateFormat.format(date));//dateFormat.format(date)
            finalOrder.setId_client(dBmanager.getIdClient());
            finalOrder.setNumber_car(carNumberString);
            finalOrder.setNumber_order(orderNumberChangAble);

            new AsyncTask<Object, Object, String>() {
                @Override
                protected void onPostExecute(String s) {
                    if (!s.toUpperCase().startsWith("ERROR")) {
                        Toast.makeText(getBaseContext(), "oooopppssseee", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    orderNumberChangAble+=2;
                }
                @Override
                protected String doInBackground(Object... params) {
                    return dBmanager.newOrder(OrderToContentValues(finalOrder));
                }
            }.execute();
        }catch (Exception e){

        }
    }
public void getTheIdOfTheCar(){
    try{
        new AsyncTask<Void, Void, List<Car>>() {
            @Override
            protected void onPostExecute(List<Car> cars) {
                for (Car car : cars) {
                    if (car.getModel_car().matches(carModelString)) {
                        if (car.getFix_branch() == branchNumberString) {
                            carNumberString = car.getCar_number();
                            Toast.makeText(CarsActivity.this, "this number your car ->" + carNumberString, Toast.LENGTH_SHORT).show();
                            finalCar.equals(car);
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

    }catch (Exception e){

    }
}



   /* @Override
    public void onCarSelecteted(String choiceCar) {
        CarListFragment carListFragment = (CarListFragment)getSupportFragmentManager().findFragmentById(R.id.)
    }*/
}
