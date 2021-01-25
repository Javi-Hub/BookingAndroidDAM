package rodrigo.javier.booking.filter.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import rodrigo.javier.booking.R;
import rodrigo.javier.booking.SearchActivity;
import rodrigo.javier.booking.beans.Hotel;
import rodrigo.javier.booking.filter.adapter.FilterHotelAdapter;
import rodrigo.javier.booking.filter.contract.FilterHotelContract;
import rodrigo.javier.booking.filter.presenter.FilterHotelPresenter;

public class FilterHotelActivity extends AppCompatActivity implements FilterHotelContract.View {

    private RecyclerView recycler;
    private RecyclerView.LayoutManager lManager;
    private FilterHotelPresenter presenter;
    private DividerItemDecoration divider;

    private Spinner spinFilter;
    private Button btReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_hotel);
        initComponents();

        presenter = new FilterHotelPresenter(this);

        //Obtener la posición de la selección del spinner que se ha pulsado
        int pos = 0;
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            pos = bundle.getInt("position");
        }

        ArrayAdapter<CharSequence> spAdapter = ArrayAdapter.createFromResource(this,
                R.array.spinFilter, R.layout.spinner_filter);
        spinFilter.setAdapter(spAdapter);

        /*Al cambiar la pantalla desde la página principal mostrar en el spinner
        la selección pulsada*/
        spinFilter.setSelection(pos);

        /*Cambio de pantalla según la selección pulsada en el spinner */
        spinFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getSelectedItem().toString().equals("Por categoria")){
                    presenter.getHotelsCategory();
                } else if (parent.getSelectedItem().toString().equals("Por valoracion")){
                    presenter.getHotelsRate();
                } else if (parent.getSelectedItem().toString().equals("Precio Descendente")){
                    presenter.getHotelsCostDesc();
                } else if (parent.getSelectedItem().toString().equals("Precio Ascendente")){
                    presenter.getHotelsCostAsc();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), SearchActivity.class);
                startActivity(intent);
            }
        });

        // Obtener el Recycler
        recycler = findViewById(R.id.recyclerFilterHotel);
        recycler.setHasFixedSize(true);

        //Usar el administrador para LinearLayout
        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);


    }

    @Override
    public void successCategory(ArrayList<Hotel> hotels) {
        showListFilter(hotels);
    }

    @Override
    public void successRate(ArrayList<Hotel> hotels) {
        showListFilter(hotels);
    }

    @Override
    public void successCostDesc(ArrayList<Hotel> hotels) {
        showListFilter(hotels);
    }

    @Override
    public void successCostAsc(ArrayList<Hotel> hotels) {
        showListFilter(hotels);
    }

    @Override
    public void error(String message) {
        Toast.makeText(this, "Fallo conexión con el servidor," +
                " al cargar el listado de hoteles", Toast.LENGTH_SHORT).show();

    }

    public void initComponents(){
        spinFilter = (Spinner) findViewById(R.id.spinFilter);
        btReturn = (Button) findViewById(R.id.btReturn);
    }

    public void showListFilter(ArrayList<Hotel> hotels){
        FilterHotelAdapter adapter = new FilterHotelAdapter(hotels);
        recycler = findViewById(R.id.recyclerFilterHotel);
        divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recycler.addItemDecoration(divider);
        recycler.setAdapter(adapter);
    }

}