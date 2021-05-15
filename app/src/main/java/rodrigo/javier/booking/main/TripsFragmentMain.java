package rodrigo.javier.booking.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import rodrigo.javier.booking.R;

public class TripsFragmentMain extends Fragment {

    private View view;

    public TripsFragmentMain() {
        // Required empty public constructor
    }

     public static TripsFragmentMain newInstance() {
        TripsFragmentMain fragment = new TripsFragmentMain();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_trips_main, container, false);
        return view;
    }
}