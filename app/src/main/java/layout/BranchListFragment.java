package layout;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ed_it_art.clientapplication.R;
import com.example.ed_it_art.clientapplication.model.BackEnd.DBManagerFactory;
import com.example.ed_it_art.clientapplication.model.BackEnd.DBmanager;
import com.example.ed_it_art.clientapplication.model.entities.Branch;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BranchListFragment extends Fragment {
    DBmanager dBmanager;
    public ListView expandableListView;

    public BranchListFragment() {
        // Required empty public constructor
    }
    public  interface  branchInterface{
        public void onBranchSelecteted(String choiceBranches);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_branch_list, container, false);
        dBmanager = DBManagerFactory.GetDB();
        expandableListView = (ListView) view.findViewById(R.id.EXPBrancheList);
        getBranches();

        return view;
    }

    public void getBranches() {
        try {
            new AsyncTask<Void, Void, List<Branch>>() {
                @Override
                protected void onPostExecute(List<Branch> branches) {
                    List<String> branchList = new ArrayList<String>();
                    for (Branch branch : branches) {
                        branchList.add("address :  " + branch.getAddress_branch()
                                + "   number branch: " + branch.getBranch_number());

                    }
                    ArrayAdapter<String> branchArrayAdapter = new ArrayAdapter<String>(
                            getActivity(),
                            android.R.layout.simple_list_item_1,
                            branchList) {
                    };
                    expandableListView.setAdapter(branchArrayAdapter);
                    //           this.setContentView(lv_carLIst);
                }

                @Override
                protected List<Branch> doInBackground(Void... params) {
                    return dBmanager.ListBranch();
                }
            }.execute();
        } catch (Exception e) {
            Toast.makeText(getContext(), "nooooo", Toast.LENGTH_LONG).show();
        }
    }
}