package rodrigo.javier.booking.lstHotel.view;

import android.os.Bundle;

import androidx.constraintlayout.utils.widget.MockView;
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

public class Lst_hotels_category_fragment extends Fragment {

    private View view;
    private ArrayList<Hotel> category;
    private RecyclerView recycler;
    private RecyclerView.LayoutManager lManager;

    private static final String CATEGORY = "param1";

    public static String TAG = Lst_hotels_category_fragment.class.getSimpleName();

    public Lst_hotels_category_fragment() {
    }

    public static Lst_hotels_category_fragment newInstance(ArrayList<Hotel> hotels) {
        Lst_hotels_category_fragment fragment = new Lst_hotels_category_fragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(CATEGORY, hotels);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Log.d(TAG, "[getListCategory Name -> ]" + category.get(0).getName());
            category = (ArrayList<Hotel>) getArguments().getSerializable(CATEGORY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_lst_hotels_category, container, false);
        initComponents();
        loadingData(orderByCategory(category));
        return view;

    }

    public void initComponents(){
        recycler = view.findViewById(R.id.recyclerLstHotelsCategory);
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

    //Método estatico que devuelve la lista de hoteles ordenados por categoría de mayor a menor
    public static ArrayList<Hotel> orderByCategory(ArrayList<Hotel> list) {
        Collections.sort(list, new Comparator<Hotel>() {
            @Override
            public int compare(Hotel hotel_1, Hotel hotel_2) {
                return new Integer(hotel_2.getCategory()).compareTo(new Integer(hotel_1.getCategory()));
            }
        });
        return list;
    }
}