package layout;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ed_it_art.clientapplication.R;
import com.example.ed_it_art.clientapplication.model.BackEnd.DBManagerFactory;
import com.example.ed_it_art.clientapplication.model.BackEnd.DBmanager;
import com.example.ed_it_art.clientapplication.model.entities.Car;
import com.example.ed_it_art.clientapplication.model.entities.ModelCar;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CarListFragment extends Fragment {
    DBmanager dBmanager;
    public ListView lv_carLIst;
    public static int sizeOfTheList = 0;
    carSectionListener carInterface; //activityCommander
    public static List<String> carListSendToInterface = new ArrayList<String>();

    public CarListFragment() {
        // Required empty public constructor
    }
    //bucky :

    public interface carSectionListener {     //TopSelection
        public void onCarSelecteted(String choiceCar); //createMeme
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            carInterface = (carSectionListener)context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString());
        }
    }

    public  void buttonClicked(String carSelected){
        carInterface.onCarSelecteted(carSelected);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_car_list, container, false);
        dBmanager = DBManagerFactory.GetDB();
        lv_carLIst = (ListView) view.findViewById(R.id.EXPcarList);
        showCars();
        lv_carLIst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for (int i = 0; i < sizeOfTheList; i++) {
                    if (position == i) {
                        buttonClicked(carListSendToInterface.get(i));
      //                  Toast.makeText(getActivity(), "position number" + i, Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
            }
        });
        return view;
    }

    private void showCars() {
        try {
            new AsyncTask<Void, Void, List<ModelCar>>() {
                @Override
                protected void onPostExecute(List<ModelCar> cars) {
                    List<String> carList = new ArrayList<String>();
                    for (ModelCar car : cars) {
                        carList.add("model car :  " + car.getModelName()
                                + "   chairs: " + car.getChairs()
                                + "  year:  " + car.getYear_car());
                        carListSendToInterface.add(car.getModelName());
                    }
                    ArrayAdapter<String> carArrayAdapter = new ArrayAdapter<String>(
                            getActivity(),
                            android.R.layout.simple_list_item_1,
                            carList) {
                    };
                    lv_carLIst.setAdapter(carArrayAdapter);
                    //           this.setContentView(lv_carLIst);
                    sizeOfTheList = carList.size();
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