package rodrigo.javier.booking.lstHotel.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import rodrigo.javier.booking.R;
import rodrigo.javier.booking.beans.Hotel;
import rodrigo.javier.booking.lstHotel.adapter.LstHotelAdapter;

public class Lst_hotels_rate_fragment extends Fragment {

    private View view;
    private ArrayList<Hotel> rate;
    private RecyclerView recycler;
    private RecyclerView.LayoutManager lManager;

    private static final String RATE = "param1";

    public static String TAG = Lst_hotels_rate_fragment.class.getSimpleName();

    public Lst_hotels_rate_fragment() {
    }

    public static Lst_hotels_rate_fragment newInstance(ArrayList<Hotel> hotels) {
        Lst_hotels_rate_fragment fragment = new Lst_hotels_rate_fragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(RATE, hotels);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            rate = (ArrayList<Hotel>) getArguments().getSerializable(RATE);
            Log.d(TAG, "[getRate list Name -> ]" + rate.get(0).getName());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_lst_hotels_rate, container, false);
        initComponents();
        loadingData(orderByRate(rate));
        return view;
    }

    public void initComponents(){
        recycler = view.findViewById(R.id.recyclerLstHotelsRate);
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

    //Método estatico que devuelve la lista de hoteles ordenados por valoración de mayor a menor
    public ArrayList<Hotel> orderByRate(ArrayList<Hotel> list) {
        Collections.sort(list, (hotel_1, hotel_2) -> hotel_2.getRate().compareTo(hotel_1.getRate()));
        return list;
    }
}