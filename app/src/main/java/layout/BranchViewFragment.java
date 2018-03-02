package layout;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
public class BranchViewFragment extends Fragment {

    DBmanager dBmanager;
    ListView lv_cars;
    public  static List<String> carsList = new ArrayList<String>();
    public BranchViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_branch_view, container, false);
        lv_cars = (ListView)view.findViewById(R.id.lv_carsInBranch);
        dBmanager = DBManagerFactory.GetDB();
        getCars();

        return view;
    }
    public  void getCars(){
        try {
            new AsyncTask<Void, Void, List<Car>>() {
                @Override
                protected void onPostExecute(List<Car> cars) {
                    for (Car car : cars) {
                        if(car.getFix_branch() == 2 ) {
                            carsList.add("model car: "+ car.getModel_car()
                            +"km: "+ car.getKilo_metrage());
                        }
                    }
                    ArrayAdapter<String> carshArrayAdapter = new ArrayAdapter<String>(
                            getActivity(),
                            android.R.layout.simple_list_item_1,
                            carsList) {
                    };
                    lv_cars.setAdapter(carshArrayAdapter);
                }
                @Override
                protected List<Car> doInBackground(Void... params) {
                    return dBmanager.ListCars();
                }
            }.execute();
        } catch (Exception e) {
        }
    }

}
