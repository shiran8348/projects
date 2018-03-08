package layout;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
    public  int sizeOfTheList = 0;
    BranchSectionListener branchSectionListener;
    BranchSelectedListener branchSelectedListener;
    public List<Branch> branchList = new ArrayList<Branch>();
    public  List<Integer> branchListSendToInterface = new ArrayList<Integer>();

    public BranchListFragment() {
        // Required empty public constructor
    }
    public  interface  BranchSectionListener{
        public void onBranchSelecteted(String choiceBranches);
    }
    public interface BranchSelectedListener{
        public void onBranchSelected(Branch branch);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            branchSectionListener = (BranchSectionListener) context;
            branchSelectedListener = (BranchSelectedListener)context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString());
        }
    }
    public void buttonClicked(String carSelected, Branch branch){
        branchSectionListener.onBranchSelecteted(carSelected);
        branchSelectedListener.onBranchSelected(branch);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_branch_list, container, false);
        dBmanager = DBManagerFactory.GetDB();
        expandableListView = (ListView) view.findViewById(R.id.EXPBrancheList);
        getBranches();
        expandableListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for (int i = 0; i < sizeOfTheList; i++) {
                    if (position == i) {
                        buttonClicked(branchListSendToInterface.get(i).toString(), branchList.get(i));//carListSendToInterface.get(i)
                        break;
                    }
                }
            }
        });

        return view;
    }

    public void getBranches() {
        try {
            new AsyncTask<Void, Void, List<Branch>>() {
                @Override
                protected void onPostExecute(List<Branch> branches) {
                    branchList = branches;
                    List<String> branchList = new ArrayList<String>();
                    for (Branch branch : branches) {
                        branchList.add("address :  " + branch.getAddress_branch()
                                + " , number branch: " + branch.getBranch_number());
                        branchListSendToInterface.add(branch.getBranch_number());
                    }
                    ArrayAdapter<String> branchArrayAdapter = new ArrayAdapter<String>(
                            getActivity(),
                            android.R.layout.simple_list_item_1,
                            branchList) {
                    };
                    expandableListView.setAdapter(branchArrayAdapter);
                    sizeOfTheList = branchList.size();
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