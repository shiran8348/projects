package com.example.ed_it_art.clientapplication.controller;

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
import com.example.ed_it_art.clientapplication.model.entities.Car;
import com.example.ed_it_art.clientapplication.model.entities.Client;
import com.example.ed_it_art.clientapplication.model.entities.Order;

import java.util.List;

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
    EditText Et_startKM;
    EditText Et_endKM;
    EditText fuel;

    Client finalClient = new Client();
    Order finalOrder = new Order();

    public void findViews() {
        tv_car = (TextView) findViewById(R.id.tv_modelCar);
        tv_client = (TextView) findViewById(R.id.tv_Client);
        scrollView   = (ScrollView)findViewById(R.id.SV_freeCar);
        B_free = (Button)findViewById(R.id.remove_button);
        Et_endKM = (EditText)findViewById(R.id.ET_endKM);
        Et_startKM = (EditText)findViewById(R.id.ET_startKM);
        fuel = (EditText)findViewById(R.id.ET_amountFuel);
        B_free.setOnClickListener(this);
        setClient();
        setCar();
    }
    @Override
    public void onClick(View v) {
        if(v == B_free){
            scrollView.setVisibility(View.VISIBLE);
     //       Toast.makeText(ClientCarActivity.this, "this"+a,Toast.LENGTH_SHORT).show();
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
      //              int size = orders.size();
   //                 Toast.makeText(ClientCarActivity.this, "this" + size, Toast.LENGTH_SHORT).show();
/*                  for (Order order : orders) {
                      if (order.getId_client() == dBmanager.getIdClient()) {

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
 */               }

                @Override
                protected List<Order> doInBackground(Void... params) {
                    return dBmanager.ListOrder();
                }
            }.execute();
        } catch (Exception e) {
        }
    }


}