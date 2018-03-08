package layout;


import android.content.Context;
import android.content.Intent;
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
import com.example.ed_it_art.clientapplication.model.entities.Car;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CarViewFragment extends Fragment {

    DBmanager dBmanager;
    ListView lv_branches;
    boolean flag = false;
    boolean flag1 = false;

    carSectionListener carInterface;
    public List<Branch> branches_List = new ArrayList<Branch>();
    public List<String> branchListSendToInterface = new ArrayList<String>();
    //  String modelCarSelected = "";
    private int sizeOftheList = 0;
    private String modelCarSelected = "";
    public List<Integer> branchListNum = new ArrayList<Integer>();
    public List<String> branchList = new ArrayList<String>();

    public CarViewFragment() {
        // Required empty public constructor
    }


    public interface carSectionListener {     //TopSelection
        public void onCarSelectetedView(Branch branch); //createMeme
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            carInterface = (carSectionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString());
        }
    }

    public void buttonClicked(Branch branch) {
        carInterface.onCarSelectetedView(branch);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_car_view, container, false);
        dBmanager = DBManagerFactory.GetDB();
        lv_branches = (ListView) view.findViewById(R.id.lv_branchesForCar);
        lv_branches.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for (int i = 0; i < sizeOftheList; i++) {
                    if (position == i) {
                       buttonClicked(branches_List.get(i));//carListSendToInterface.get(i)
                       break;
                    }
                }
            }
        });
        return view;
    }

    public void setModelCarSelected(String modelCar) {
        branchList.clear();
        branchListNum.clear();
        branches_List.clear();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,
                branchList) {
        };
        lv_branches.setAdapter(adapter);
        modelCarSelected = modelCar;
        getBranchesNumbers();
    }

    public void getBranchesNumbers() {
        try {
            new AsyncTask<Void, Void, List<Car>>() {
                @Override
                protected void onPostExecute(List<Car> cars) {
                    for (Car car : cars) {
                        if (car.getModel_car().matches(modelCarSelected)) {//modelCarSelected
                            branchListNum.add(car.getFix_branch());
                            flag = true;
                        }
                    }
                    getBranches();

                }

                @Override
                protected List<Car> doInBackground(Void... params) {
                    return dBmanager.ListCars();
                }
            }.execute();
        } catch (Exception e) {
        }
    }

    public void getBranches() {
        if (flag) {
            try {
                new AsyncTask<Void, Void, List<Branch>>() {
                    @Override
                    protected void onPostExecute(List<Branch> branches) {
                        //       branches_List = branches;
                        //      branches.addAll(branches_List);
                        int i =0;
                        for (Branch branch : branches)
                            for (int item : branchListNum)
                                if (branch.getBranch_number() == item) {
                                    branchList.add(" address: " + branch.getAddress_branch()
                                            + ", branch: " + branch.getBranch_number());
                                    branches_List.add(branch);
                                }
                        ArrayAdapter<String> branchArrayAdapter = new ArrayAdapter<String>(
                                getActivity(),
                                android.R.layout.simple_list_item_1,
                                branchList) {
                        };
                        lv_branches.setAdapter(branchArrayAdapter);
                        sizeOftheList = branchList.size();
                    }

                    @Override
                    protected List<Branch> doInBackground(Void... params) {
                        return dBmanager.ListBranch();
                    }
                }.execute();
            } catch (Exception e) {
            }
        }
    }
}
