package com.example.ed_it_art.clientapplication.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.ed_it_art.clientapplication.R;
import com.example.ed_it_art.clientapplication.model.BackEnd.DBManagerFactory;
import com.example.ed_it_art.clientapplication.model.BackEnd.DBmanager;
import com.example.ed_it_art.clientapplication.model.entities.Branch;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shiran on 22/01/2018.
 */

public class BranchByCity extends BaseAdapter implements Filterable {
    private DBmanager dbm = DBManagerFactory.GetDB();
    private static List<Branch> branchesList;
    private static LayoutInflater mLayoutInflater;
    private static Filter cityFilter = null;

    public BranchByCity(Context context, List<Branch> branches) {
        if (branches == null)
            branchesList = new ArrayList<>();
        else branchesList = branches;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return branchesList.size();
    }

    @Override
    public Object getItem(int position) {
        return branchesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Branch branch = (Branch) getItem(position);
        View view = mLayoutInflater.inflate(R.layout.fragment_branch_view, parent, false);
//        TextView branchNum = (TextView) view.findViewById(R.id.branchNum);
//        FloatingActionButton goButton = (FloatingActionButton) view.findViewById(R.id.goButton);
//        goButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String str = "http://maps.google.com/maps?daddr=";
//                str += branch.getStreet().replace(" ", "+") + "+" +
//                        branch.getHouseNum() + "+" +
//                        branch.getCity().replace(" ", "+");
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(str));
//                context.startActivity(intent);
//            }
//        });
 //       branchNum.setText(String.valueOf(branch.getBranch_number()));
        return view;
    }

    @Override
    public Filter getFilter() {
        if (cityFilter == null)
            cityFilter = new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    FilterResults results = new FilterResults();
                    if (constraint == null || constraint.length() == 0) {
                        // No filter implemented we return all the list
                        results.values = branchesList;
                        results.count = branchesList.size();
                    } else {
                        // We perform filtering operation
                        List<Branch> nBranchList = new ArrayList<>();
                        for (Branch b : branchesList) {
                            if (b.getAddress_branch().toUpperCase().contains(constraint.toString().toUpperCase()))
                                nBranchList.add(b);
                        }
                        results.values = nBranchList;
                        results.count = nBranchList.size();
                    }
                    return results;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    // Now we have to inform the adapter about the new list filtered
                    if (results.count == 0)
                        notifyDataSetInvalidated();
                    else {
                        branchesList = (List<Branch>) results.values;
                        notifyDataSetChanged();
                    }
                }
            };
        return cityFilter;
    }
}

