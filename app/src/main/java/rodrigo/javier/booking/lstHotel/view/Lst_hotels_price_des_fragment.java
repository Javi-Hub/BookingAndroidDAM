package rodrigo.javier.booking.lstHotel.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import rodrigo.javier.booking.R;
import rodrigo.javier.booking.beans.Hotel;
import rodrigo.javier.booking.lstHotel.adapter.LstHotelAdapter;

public class Lst_hotels_price_des_fragment extends Fragment {

    private View view;
    private ArrayList<Hotel> priceDesc;
    private RecyclerView recycler;
    private RecyclerView.LayoutManager lManager;

    private static final String PRICE_DESC = "param1";

    public Lst_hotels_price_des_fragment() {

    }

    public static Lst_hotels_price_des_fragment newInstance(ArrayList<Hotel> hotels) {
        Lst_hotels_price_des_fragment fragment = new Lst_hotels_price_des_fragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(PRICE_DESC, hotels);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            priceDesc = (ArrayList<Hotel>) getArguments().getSerializable(PRICE_DESC);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_lst_hotels_price_des, container, false);
        initComponents();
        loadingData(orderByPriceDesc(priceDesc));
        return view;
    }

    public void initComponents(){
        recycler = view.findViewById(R.id.recyclerLstHotelsPriceDesc);
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

    public static ArrayList<Hotel> orderByPriceDesc(ArrayList<Hotel> list) {
        Collections.sort(list, (hotel_1, hotel_2) -> hotel_2.getAveragePrize().compareTo(new Double(hotel_1.getAveragePrize())));
        return list;
    }
}