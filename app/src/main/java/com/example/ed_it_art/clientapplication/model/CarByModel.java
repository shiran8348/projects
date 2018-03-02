package com.example.ed_it_art.clientapplication.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import com.example.ed_it_art.clientapplication.model.BackEnd.DBManagerFactory;
import com.example.ed_it_art.clientapplication.model.BackEnd.DBmanager;
import com.example.ed_it_art.clientapplication.model.entities.Car;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shiran on 22/01/2018.
 */

public class CarByModel  extends BaseAdapter implements Filterable{
    private DBmanager dbm = DBManagerFactory.GetDB();
    private static List<Car> carsList;
    private static LayoutInflater mLayoutInflater;
    private static Filter modelFilter = null;

    public CarByModel(Context context, List<Car> cars) {
        if(cars==null)
            carsList = new ArrayList<>();
        else carsList = cars;
        mLayoutInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return carsList.size();
    }

    @Override
    public Object getItem(int position) {
        return carsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public Filter getFilter() {
        return null;
    }
}
