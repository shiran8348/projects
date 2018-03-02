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

import com.example.ed_it_art.clientapplication.R;
import com.example.ed_it_art.clientapplication.model.BackEnd.DBManagerFactory;
import com.example.ed_it_art.clientapplication.model.BackEnd.DBmanager;
import com.example.ed_it_art.clientapplication.model.BranchByCity;
import com.example.ed_it_art.clientapplication.model.CarByModel;
import com.example.ed_it_art.clientapplication.model.entities.Branch;
import com.example.ed_it_art.clientapplication.model.entities.Car;

import java.util.List;

public class BranchesActivity extends AppCompatActivity //implements View.OnClickListener
         {
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branches);
    }
/*
    private DBmanager dbm;
    private SearchView searchView;
    private ListView branchList;
    private TextView carDetails;
    private ListView carList;
    private TextView branchDetails;
    private Button goButton;
    private Branch currentBranch;
    private BranchByCity cityAdapter;
    private View view;
    private Button yesButton;
    private Button okButton2;
    private Button okButton3;
    private View orderView;
    private AlertDialog orderDialog;
    private View carView;
    private AlertDialog carDialog;
    private View clientView;
    private AlertDialog clientDialog;
    private Car currentCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branches);
        dbm = DBManagerFactory.GetDB();
        findViews();
    }
    private void findViews(){
        searchView = (SearchView)findViewById(R.id.searchView);
//        branchList = (ListView)findViewById( R.id.branchList );
        carDetails = (TextView) findViewById( R.id.carDetailsTextView );
  //      carList = (ListView)findViewById( R.id.carList );
        branchDetails = (TextView) findViewById( R.id.branchDetailsTextView );
        goButton = (Button) findViewById(R.id.goButton);

        goButton.setOnClickListener(this);

    }
    private void init(){
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                cityAdapter.getFilter().filter(newText);
                cityAdapter.notifyDataSetChanged();
                return false;
            }
        }); //initiate search

        new AsyncTask<Object, Object, BranchByCity>() {
            @Override
            protected BranchByCity doInBackground(Object... params) {
                cityAdapter = new BranchByCity(getBaseContext(), dbm.ListBranch());
                return cityAdapter;
            }

            @Override
            protected void onPostExecute(BranchByCity branchByCity) {
                branchList.setAdapter(branchByCity);
            }
        }.execute(); //initiait brancg list
        branchList.setClickable(true);
        branchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                currentBranch = (Branch)branchList.getItemAtPosition(position);
                goButton.setEnabled(true);
                branchDetails.setText(currentBranch.toString().replace(", ", "\n"));
                new AsyncTask<Object, Object, List<Car>>(){

                    @Override
                    protected List<Car> doInBackground(Object... params) {
                        return dbm.ListFreeCarsForOneBranch(currentBranch.getBranch_number());
                    }

                    @Override
                    protected void onPostExecute(List<Car> cars) {
                        if(cars==null) {
                            carDetails.setVisibility(View.VISIBLE);
                            carDetails.setText("sorry, there are no cars in this branch");
                        }
                        else carDetails.setVisibility(View.GONE);
                        carList.setAdapter(new CarByModel(getBaseContext(), cars)); // initiate cars list
                    }
                }.execute();
            }
        });
    }
    @Override
    public void onClick(View v) {

    }*/
}
