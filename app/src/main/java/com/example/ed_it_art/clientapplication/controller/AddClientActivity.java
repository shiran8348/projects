package com.example.ed_it_art.clientapplication.controller;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ed_it_art.clientapplication.R;
import com.example.ed_it_art.clientapplication.model.BackEnd.DBManagerFactory;
import com.example.ed_it_art.clientapplication.model.BackEnd.DBmanager;
import com.example.ed_it_art.clientapplication.model.entities.Client;

import static com.example.ed_it_art.clientapplication.model.utils.RentsConst.ClientToContentValues;


public class AddClientActivity extends AppCompatActivity implements View.OnClickListener {

    DBmanager dBmanager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);
        dBmanager = DBManagerFactory.GetDB();
        findViews();

    }


    protected String firstName;
    protected String lastName;
    protected String ID;
    protected String mail;
    protected String creditCard;
    protected String userName;
    protected String password1;
    protected String password2;
    protected Button BaddClient;

    public void findViews() {

        firstName = ((EditText) findViewById(R.id.ET_name)).getText().toString();
        lastName = ((EditText) findViewById(R.id.ET_last_name)).getText().toString();
        ID = ((EditText) findViewById(R.id.ET_id)).getText().toString();
        mail = ((EditText) findViewById(R.id.ET_mail)).getText().toString();
        creditCard = ((EditText) findViewById(R.id.ET_credit_card)).getText().toString();
        userName = ((EditText) findViewById(R.id.ET_userName)).getText().toString();
        password1 = ((EditText) findViewById(R.id.Et_password1)).getText().toString();
        password2 = ((EditText) findViewById(R.id.Et_password2)).getText().toString();

        BaddClient = (Button) findViewById(R.id.BaddNewClient);
        BaddClient.setOnClickListener(this);

//        final Uri uri = RentsConst.ClientConst.CLIENT_URI;
//        updateItemList(this,uri);
    }

    @Override
    public void onClick(View v) {
        findViews();
        try {
            if (firstName.matches("") ||
                    lastName.matches("") ||
                    ID.matches("") ||
                    mail.matches("") ||
                    creditCard.matches("") ||
                    userName.matches("") ||
                    password1.matches("") ||
                    password2.matches(""))
                Toast.makeText(AddClientActivity.this, "miss inputs", Toast.LENGTH_SHORT).show();
           if(!password1.equals(password2)){
               Toast.makeText(AddClientActivity.this,"password not equal", Toast.LENGTH_SHORT).show();
           }
            else
                addClient();
        }catch (NumberFormatException n){
            Toast.makeText(AddClientActivity.this,"NUMBER WHERE THERE SHOULDN BE",Toast.LENGTH_SHORT).show();
        }

    }

    private void addClient() {
        final Client client;
        try {
            client = new Client();
            client.setName(firstName);
            client.setLast_name(lastName);
            client.set_id(Long.parseLong(ID));
            client.setMail(mail);
            client.setCredit_number(Long.parseLong(creditCard));
            client.setUser_name(userName);
            client.setPassword(password1);
            new AsyncTask<Object, Object, String>() {
                @Override
                protected void onPostExecute(String s) {
                    if (!s.toUpperCase().startsWith("ERROR")) {
                        Toast.makeText(getBaseContext(), "insert new client", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    super.onPostExecute(s);
                }

                @Override
                protected String doInBackground(Object... params) {

                    return dBmanager.addClient(ClientToContentValues(client));
                }
            }.execute();
        } catch (Exception e) {
        }
    }
}
