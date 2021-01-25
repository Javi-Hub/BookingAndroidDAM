package rodrigo.javier.booking.lstHotel.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import rodrigo.javier.booking.R;
import rodrigo.javier.booking.beans.Hotel;
import rodrigo.javier.booking.filter.view.FilterHotelActivity;
import rodrigo.javier.booking.lstHotel.adapter.LstHotelAdapter;
import rodrigo.javier.booking.lstHotel.contract.LstHotelContract;
import rodrigo.javier.booking.lstHotel.presenter.LstHotelPresenter;

public class LstHotelActivity extends AppCompatActivity implements LstHotelContract.View {

    private RecyclerView recycler;
    private DividerItemDecoration divider;
    private RecyclerView.LayoutManager lManager;
    private LstHotelPresenter presenter;
    private Spinner spinFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lst_hotel);
        initComponents();

        //Adaptador para capturar la selección desde el Spinner
        ArrayAdapter<CharSequence> spAdapter = ArrayAdapter.createFromResource(this,
                R.array.spinFilter, R.layout.spinner_filter);
        spinFilter.setAdapter(spAdapter);

        //Capturar la selección pulsada en el spinner
        spinFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getSelectedItem().toString().equals("Por categoria")) {
                    Intent intent = new Intent(getBaseContext(), FilterHotelActivity.class);
                    intent.putExtra("position", spAdapter.getPosition("Por categoria"));
                    startActivity(intent);
                } else if (parent.getSelectedItem().toString().equals("Por valoracion")) {
                    Intent intent = new Intent(getBaseContext(), FilterHotelActivity.class);
                    intent.putExtra("position", spAdapter.getPosition("Por valoracion"));
                    startActivity(intent);
                } else if (parent.getSelectedItem().toString().equals("Precio Descendente")) {
                    Intent intent = new Intent(getBaseContext(), FilterHotelActivity.class);
                    intent.putExtra("position", spAdapter.getPosition("Precio Descendente"));
                    startActivity(intent);
                }else if (parent.getSelectedItem().toString().equals("Precio Ascendente")) {
                    Intent intent = new Intent(getBaseContext(), FilterHotelActivity.class);
                    intent.putExtra("position", spAdapter.getPosition("Precio Ascendente"));
                    startActivity(intent);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //Comunicar con la clase Presenter desde el View
        presenter = new LstHotelPresenter(this);
        presenter.getHotels();

        // Obtener el Recycler
        recycler = findViewById(R.id.recyclerLstHotels);
        recycler.setHasFixedSize(true);

        //Usar el administrador para LinearLayout
        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

    }

    public void initComponents(){
        spinFilter = findViewById(R.id.spinFilter);
    }

    @Override
    public void success(ArrayList<Hotel> hotels) {
        //Crear un nuevo adaptador
        LstHotelAdapter adapter = new LstHotelAdapter(hotels);
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