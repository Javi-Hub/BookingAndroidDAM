package rodrigo.javier.booking.lstHotel.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import rodrigo.javier.booking.R;
import rodrigo.javier.booking.beans.Hotel;
import rodrigo.javier.booking.lstHotel.adapter.LstHotelAdapter;

public class Lst_hotels_fragment extends Fragment {

    private View view;
    private ArrayList<Hotel> allHotels;
    private RecyclerView recycler;
    private RecyclerView.LayoutManager lManager;

    private static final String HOTELS = "param1";

    public Lst_hotels_fragment() {

    }

    public static Lst_hotels_fragment newInstance(ArrayList<Hotel> hotels) {
        Lst_hotels_fragment fragment = new Lst_hotels_fragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(HOTELS, hotels);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            allHotels =(ArrayList<Hotel>) getArguments().getSerializable(HOTELS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_lst_hotels, container, false);
        initComponents();
        loadingData(allHotels);
        return view;
    }

    public void initComponents(){
        recycler = view.findViewById(R.id.recyclerLstHotels);
    }

    public void loadingData(ArrayList<Hotel> hotels){
        // Obtener el Recycler
        recycler.setHasFixedSize(true);
        //Usar el administrador para LinearLayout
        lManager = new LinearLayoutManager(view.getContext());
        recycler.setLayoutManager(lManager);

        LstHotelAdapter adapter = new LstHotelAdapter(hotels);
        adapter.notifyDataSetChanged();
        recycler.setAdapter(adapter);
    }



}