package rodrigo.javier.booking.fieldSearch.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

import rodrigo.javier.booking.R;
import rodrigo.javier.booking.beans.Hotel;
import rodrigo.javier.booking.fieldSearch.adapter.FieldSearchAdapter;
import rodrigo.javier.booking.fieldSearch.contract.FieldSearchContract;
import rodrigo.javier.booking.fieldSearch.presenter.FieldSearchPresenter;
import rodrigo.javier.booking.filter.contract.FilterHotelContract;
import rodrigo.javier.booking.lstHotel.presenter.LstHotelPresenter;

public class FieldSearchActivity extends AppCompatActivity implements FieldSearchContract.View {

    private RecyclerView recycler;
    private DividerItemDecoration divider;
    private RecyclerView.LayoutManager lManager;
    private FieldSearchPresenter presenter;

    private String city;
    private Hotel hotel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field_search);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            city = bundle.getString("city");
        }

        presenter = new FieldSearchPresenter(this);

        //Comunicar con la clase Presenter desde el View
        presenter = new FieldSearchPresenter(this);
        presenter.getHotels(city);

        // Obtener el Recycler
        recycler = findViewById(R.id.recyclerFieldSearch);
        recycler.setHasFixedSize(true);

        //Usar el administrador para LinearLayout
        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

    }

    @Override
    public void success(ArrayList<Hotel> hotels) {
        FieldSearchAdapter adapter = new FieldSearchAdapter(hotels);
        divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(getResources().getDrawable(R.drawable.recyclerview_divider));
        recycler.addItemDecoration(divider);
        recycler.setAdapter(adapter);
    }

    @Override
    public void error(String message) {
        Toast.makeText(this, "Fallo conexión con el servidor," +
                " al cargar el listado de hoteles", Toast.LENGTH_SHORT).show();

    }
}