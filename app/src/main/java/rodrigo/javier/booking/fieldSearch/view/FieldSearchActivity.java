package rodrigo.javier.booking.fieldSearch.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import rodrigo.javier.booking.R;
import rodrigo.javier.booking.beans.Hotel;
import rodrigo.javier.booking.fieldSearch.adapter.FieldSearchAdapter;
import rodrigo.javier.booking.fieldSearch.contract.FieldSearchContract;
import rodrigo.javier.booking.fieldSearch.presenter.FieldSearchPresenter;

public class FieldSearchActivity extends AppCompatActivity implements FieldSearchContract.View {

    private RecyclerView recycler;
    private DividerItemDecoration divider;
    private RecyclerView.LayoutManager lManager;
    private FieldSearchPresenter presenter;

    private LinearLayout errorLayout;
    private ProgressBar progressBar;
    private Button retryButton;
    private String city, dateIn, dateOut, numPeople;

    public static String TAG = FieldSearchActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field_search);
        initComponents();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            Log.d(TAG, "[getBundle DateIn -> ]" + city);
            city = bundle.getString("city");
            dateIn = bundle.getString("dateIn");
            dateOut = bundle.getString("dateOut");
            numPeople = bundle.getString("numPeople");
        }

        presenter = new FieldSearchPresenter(this);

        //Comunicar con la clase Presenter desde el View
        presenter = new FieldSearchPresenter(this);
        presenter.getHotels(this, city);

        // Obtener el Recycler
        recycler = findViewById(R.id.recyclerFieldSearch);
        recycler.setHasFixedSize(true);

        //Usar el administrador para LinearLayout
        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                errorLayout.setVisibility(View.GONE);
                recycler.setVisibility(View.GONE);
                presenter.getHotels(getBaseContext(), city);
            }
        });

    }

    public void initComponents(){
        errorLayout = findViewById(R.id.activity_field_search_error_loading);
        progressBar = findViewById(R.id.activity_field_search_loading_progressBar);
        retryButton = findViewById(R.id.activity_field_search_button_retry);
    }

    @Override
    public void success(ArrayList<Hotel> hotels) {
        FieldSearchAdapter adapter = new FieldSearchAdapter(hotels, dateIn, dateOut, numPeople);
        divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(getResources().getDrawable(R.drawable.shape_text_view_rate));
        recycler.addItemDecoration(divider);
        recycler.setAdapter(adapter);
        recycler.setVisibility(View.VISIBLE);
        errorLayout.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void error(String message) {
        recycler.setVisibility(View.GONE);
        errorLayout.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this, "Fallo conexión con el servidor," +
                " al cargar el listado de hoteles", Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}