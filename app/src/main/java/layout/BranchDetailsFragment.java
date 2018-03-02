package layout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ed_it_art.clientapplication.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BranchDetailsFragment extends Fragment {


    public BranchDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_branch_details, container, false);
    }

}
