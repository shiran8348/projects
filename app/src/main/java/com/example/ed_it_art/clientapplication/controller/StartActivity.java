package com.example.ed_it_art.clientapplication.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ed_it_art.clientapplication.R;
import com.example.ed_it_art.clientapplication.model.BackEnd.DBManagerFactory;
import com.example.ed_it_art.clientapplication.model.BackEnd.DBmanager;
import com.example.ed_it_art.clientapplication.model.entities.Client;

import java.util.List;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {
    DBmanager dBmanager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        dBmanager = DBManagerFactory.GetDB();
        //       init();
        findViews();
    }

    EditText UsernameEditText;
    EditText PasswordEditText;
    String userName;
    String password;
    CheckBox checkBox;
    Button b_clear;
    Button b_signIn;
    Button b_register;
    Button b_load;

    public void init() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (sharedPreferences.contains("user_name"))
            UsernameEditText.setText(sharedPreferences.getString("user_name", null));
        if (sharedPreferences.contains("password"))
            PasswordEditText.setText(sharedPreferences.getString("password", null));
        checkBox.setChecked(true);
    }

    private void clearSharedPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
        UsernameEditText.setText("");
        PasswordEditText.setText("");
        Toast.makeText(this, "clear Preferences", Toast.LENGTH_SHORT).show();
    }

    private void saveSharedPreferences() {
        try {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            String name = UsernameEditText.getText().toString();
            String pass = PasswordEditText.getText().toString();
            editor.putString("NAME", name);
            editor.putString("PASSWORD", pass);
            editor.commit();
            Toast.makeText(this, "save name and pass Preferences", Toast.LENGTH_SHORT).show();
        } catch (Exception ex) {
            Toast.makeText(this, "failed to save Preferences", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadSharedPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (sharedPreferences.contains("NAME")) {
            UsernameEditText.setText(sharedPreferences.getString("NAME", null));
        }
        if (sharedPreferences.contains("PASSWORD")) {
//            String password = sharedPreferences.getString("PASSWORD", null);
            PasswordEditText.setText(sharedPreferences.getString("PASSWORD", null));
        } else
            Toast.makeText(StartActivity.this, "there is no saved users", Toast.LENGTH_SHORT).show();

    }

    public void clearInfo() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
        UsernameEditText.setText("");
        PasswordEditText.setText("");
    }

    public void clear() {
        clearSharedPreferences();
        this.UsernameEditText.setText(null);
        this.PasswordEditText.setText(null);
    }

    public void findViews() {
        UsernameEditText = ((EditText) findViewById(R.id.ET_userName));
        userName = UsernameEditText.getText().toString();
        PasswordEditText = ((EditText) findViewById(R.id.Et_password));
        password = PasswordEditText.getText().toString();
        checkBox = (CheckBox) findViewById(R.id.CB_remember);
        b_clear = (Button) findViewById(R.id.B_clear);
        b_register = (Button) findViewById(R.id.B_register);
        b_signIn = (Button) findViewById(R.id.B_logIn);
        b_load = (Button) findViewById(R.id.B_load);
        b_load.setOnClickListener(this);
        b_clear.setOnClickListener(this);
        b_signIn.setOnClickListener(this);
        b_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        findViews();
        switch (v.getId()) {
            case R.id.B_register:
                Intent intent = new Intent(StartActivity.this, AddClientActivity.class);
                startActivity(intent);
                break;
            case R.id.B_clear:
                clear();
                break;
            case R.id.B_logIn:
                userName = ((EditText) findViewById(R.id.ET_userName)).getText().toString();
                password = ((EditText) findViewById(R.id.Et_password)).getText().toString();
                if (userName.matches("") || password.matches(""))
                    Toast.makeText(StartActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                else {
                    String user = userName;
                    String pass = password;
                    getClient(user, pass);
                }
                break;
            case R.id.B_load:
                loadSharedPreferences();
                break;
        }
    }

    private boolean getClient(final String user, final String pass) {
        final boolean[] f = {false};
        try {
            new AsyncTask<Void, Void, List<Client>>() {
                @Override
                protected void onPostExecute(List<Client> clients) {
                    for (Client c : clients) {
                        if ((c.getPassword().equals(pass)) &&
                                (c.getUser_name().equals(user))) {
                            if (checkBox.isChecked())
                                saveSharedPreferences();
                            Toast.makeText(StartActivity.this, "pleas go in", Toast.LENGTH_SHORT).show();
                            dBmanager.setIdClient((int)c.get_id());
                            Intent intent1 = new Intent(StartActivity.this, MainActivity.class);
                            startActivity(intent1);
                            f[0] = true;
                            break;
                        }
                    }
                    if (!f[0])
                        Toast.makeText(StartActivity.this, "not found in the server", Toast.LENGTH_SHORT).show();
                }
                @Override
                protected List<Client> doInBackground(Void... params) {
                    return dBmanager.ListClient();
                }
            }.execute();
        } catch (Exception e) {
        }
        return f[0];
    }
}
