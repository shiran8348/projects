package com.example.ed_it_art.clientapplication.controller;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ed_it_art.clientapplication.R;
import com.example.ed_it_art.clientapplication.model.BackEnd.DBManagerFactory;
import com.example.ed_it_art.clientapplication.model.BackEnd.DBmanager;
import com.example.ed_it_art.clientapplication.model.entities.Client;
import com.example.ed_it_art.clientapplication.model.entities.Order;

import java.util.List;

import static com.example.ed_it_art.clientapplication.model.utils.RentsConst.OrderToContentValues;

public class ClientCarActivity extends AppCompatActivity implements View.OnClickListener{
    DBmanager dBmanager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_car);
        dBmanager = DBManagerFactory.GetDB();
        findViews();
    }

    TextView tv_car;
    TextView tv_client;

    ScrollView scrollView;
    Button B_free;
    Button B_remove;

    EditText Et_startKM;
    EditText Et_endKM;
    EditText ET_fuel;

    String startKM;
    String endKM;
    String fuel;

    Client finalClient = new Client();
    Order finalOrder = new Order();
    public void findViews() {
        tv_car = (TextView) findViewById(R.id.tv_modelCar);
        tv_client = (TextView) findViewById(R.id.tv_Client);
        scrollView   = (ScrollView)findViewById(R.id.SV_freeCar);

        Et_endKM = (EditText)findViewById(R.id.ET_endKM);
        Et_startKM = (EditText)findViewById(R.id.ET_startKM);
        ET_fuel = (EditText)findViewById(R.id.ET_amountFuel);

        B_free = (Button)findViewById(R.id.remove_button);
        B_remove = (Button)findViewById(R.id.remove_theCAR);
        B_free.setOnClickListener(this);
        B_remove.setOnClickListener(this);
        setClient();
        setCar();
    }
    public void setViews(){
        startKM = Et_startKM.getText().toString();
        endKM = Et_endKM.getText().toString();
        fuel = ET_fuel.getText().toString();
    }
    @Override
    public void onClick(View v) {
        if(v == B_free){
            if(scrollView.getVisibility() == View.INVISIBLE) {
                scrollView.setVisibility(View.VISIBLE);
                B_free.setText("i regret");
                B_remove.setOnClickListener(this);
                B_remove.isClickable();

            }
            else {
                scrollView.setVisibility(View.INVISIBLE);
                B_free.setText("FREE THIS CAR");
            }

     //       Toast.makeText(ClientCarActivity.this, "this"+a,Toast.LENGTH_SHORT).show();
        }
        if (v == B_remove) {
            setViews();
            if (startKM.matches("")||
                    endKM.matches("")||
                    fuel.matches("")) {
            Toast.makeText(ClientCarActivity.this,"need do insert all paramters",Toast.LENGTH_SHORT).show();
            }
            else {
                setInOrder();
                Toast.makeText(ClientCarActivity.this, "update and removed succefully", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void setClient() {
        try {
            new AsyncTask<Void, Void, List<Client>>() {
                @Override
                protected void onPostExecute(List<Client> clients) {
                    for (Client client : clients) {
                        if (client.get_id() == dBmanager.getIdClient()) {
                            finalClient.setName(client.getName());
                            finalClient.setLast_name(client.getLast_name());
                            finalClient.setMail(client.getMail());
                            finalClient.set_id(client.get_id());
                            break;
                        }
                    }
                    tv_client.setText("last name: " + finalClient.getLast_name() + "\n"
                            + "first name: " + finalClient.getName() + "\n"
                            + "id : " + finalClient.get_id() + "\n"
                            + "email: " + finalClient.getMail() + "\n");
                }

                @Override
                protected List<Client> doInBackground(Void... params) {
                    return dBmanager.ListClient();
                }
            }.execute();

        } catch (Exception e) {

        }
    }

    public void setCar() {
        try {
            new AsyncTask<Void, Void, List<Order>>() {
                @Override
                protected void onPostExecute(List<Order> orders) {
       //             int size = orders.size();
       //            Toast.makeText(ClientCarActivity.this, "this" + size, Toast.LENGTH_SHORT).show();
                  for (Order order : orders) {
                      if (order.getId_client() == dBmanager.getIdClient())
                        if(order.isOrder_open()){
                            finalOrder.setNumber_car(order.getNumber_car());
                            finalOrder.setNumber_branch(order.getNumber_branch());
                            finalOrder.setNumber_order(order.getNumber_order());
                            finalOrder.setStart_km(order.getStart_km());
                           break;
                       }
                   }
                    tv_car.setText("carNUMBER : " + finalOrder.getNumber_car() + "\n"
                            + "branch number : "  + finalOrder.getNumber_branch() + "\n"
                            + "number order : " + finalOrder.getNumber_order() + "\n");
                    Toast.makeText(ClientCarActivity.this, "this" + finalOrder.getNumber_order(), Toast.LENGTH_SHORT).show();

                }

                @Override
                protected List<Order> doInBackground(Void... params) {
                    return dBmanager.ListOrder();
                }
            }.execute();
        } catch (Exception e) {
        }
    }

    public void setInOrder(){
        try{
            new AsyncTask<Void,Void, Void>() {

                @Override
                protected Void doInBackground(Void... params) {
                    dBmanager.updateOrder(OrderToContentValues(finalOrder),
                            Double.parseDouble(endKM),Double.parseDouble( fuel));
                    return null;
                }
            }.execute();
        }catch (Exception e){

        }
    }

}