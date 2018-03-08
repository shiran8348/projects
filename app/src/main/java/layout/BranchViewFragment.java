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
import android.widget.ListView;
import android.widget.Toast;

import com.example.ed_it_art.clientapplication.R;
import com.example.ed_it_art.clientapplication.model.BackEnd.DBManagerFactory;
import com.example.ed_it_art.clientapplication.model.BackEnd.DBmanager;
import com.example.ed_it_art.clientapplication.model.entities.Branch;
import com.example.ed_it_art.clientapplication.model.entities.Car;
import com.example.ed_it_art.clientapplication.model.entities.ModelCar;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BranchViewFragment extends Fragment {

    DBmanager dBmanager;
    ListView lv_cars;

    boolean flag = false;
    public int sizeOftheList = 0;
    List<ModelCar> modelCarList = new ArrayList<ModelCar>();
    List<Car> carListFORmodelcar = new ArrayList<Car>();
    BranchSelectedListner branchSelectedListner;
    public  String brancheSelected = "";
    public  List<String> carsList = new ArrayList<String>();
    public List<Car> carListSelected = new ArrayList<Car>();

    public BranchViewFragment() {
        // Required empty public constructor
    }

    public interface BranchSelectedListner {
        public void onBranchSelectedView(ModelCar car);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            branchSelectedListner = (BranchSelectedListner) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString());
        }
    }

    public void buttonClicked(ModelCar car) {
        branchSelectedListner.onBranchSelectedView(car);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_branch_view, container, false);
        dBmanager = DBManagerFactory.GetDB();
        lv_cars = (ListView) view.findViewById(R.id.lv_carsInBranch);
        lv_cars.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for (int i = 0; i < sizeOftheList; i++) {
                    if (position == i) {
                        buttonClicked(modelCarList.get(i));//carListSendToInterface.get(i)
                        break;
                    }
                }
            }
        });
        return view;
    }

    public void setBranchesSelected(String branche) {
        carsList.clear();
        carListFORmodelcar.clear();
        modelCarList.clear();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,
                carsList) {
        };
        lv_cars.setAdapter(adapter);
        brancheSelected = branche;
        getCars();
    }



    public void getCars() {
        try {
            new AsyncTask<Void, Void, List<Car>>() {
                @Override
                protected void onPostExecute(List<Car> cars) {
                    carListSelected = cars;
                    for (Car car : cars) {
                        if (car.getFix_branch() == Integer.parseInt(brancheSelected)) {
                            carListFORmodelcar.add(car);
                            flag =true;
                        }
                    }
                    getModelCars();
                }

                @Override
                protected List<Car  > doInBackground(Void... params) {
                    return dBmanager.ListCars();
                }
            }.execute();
        } catch (Exception e) {
        }
    }
    public void getModelCars() {
        if (flag)
            try {
                new AsyncTask<Void, Void, List<ModelCar>>() {
                    @Override
                    protected void onPostExecute(List<ModelCar> modelCars) {
                        for (ModelCar model : modelCars)
                            for (Car car : carListFORmodelcar)
                                if (model.getModelName().matches(car.getModel_car())) {
                                    modelCarList.add(model);
                                    carsList.add(" model car: " + car.getModel_car()
                                            + " year: " + model.getYear_car()
                                            + " sits" + model.getChairs());
                                }
                        ArrayAdapter<String> carshArrayAdapter = new ArrayAdapter<String>(
                                getActivity(),
                                android.R.layout.simple_list_item_1,
                                carsList) {
                        };
                        lv_cars.setAdapter(carshArrayAdapter);
                        sizeOftheList = carsList.size();
                    }

                    @Override
                    protected List<ModelCar> doInBackground(Void... params) {
                        return dBmanager.ListModels();
                    }
                }.execute();
            } catch (Exception e) {

            }
    }
}
