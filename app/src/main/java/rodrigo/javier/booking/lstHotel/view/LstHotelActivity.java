package rodrigo.javier.booking.lstHotel.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import rodrigo.javier.booking.R;
import rodrigo.javier.booking.beans.Hotel;
import rodrigo.javier.booking.lstHotel.contract.LstHotelContract;
import rodrigo.javier.booking.lstHotel.presenter.LstHotelPresenter;

public class LstHotelActivity extends AppCompatActivity implements LstHotelContract.View {

    private LstHotelPresenter presenter;
    private FrameLayout frameLayout;
    private BottomNavigationView nav;
    private LinearLayout errorLayout;
    private ProgressBar progressBar;
    private Button retryButton;

    private ArrayList<Hotel> hotels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lst_hotel);
        initComponents();
        initBottomNavigation();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //Comunicar con la clase Presenter desde el View
        presenter = new LstHotelPresenter(this);
        presenter.getHotels(this);

        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                errorLayout.setVisibility(View.GONE);
                frameLayout.setVisibility(View.GONE);
                nav.setVisibility(View.GONE);
                presenter.getHotels(getBaseContext());
            }
        });

    }

    @Override
    public void success(ArrayList<Hotel> hotels) {
        frameLayout.setVisibility(View.VISIBLE);
        nav.setVisibility(View.VISIBLE);
        errorLayout.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        this.hotels = hotels;
        setFragment(Lst_hotels_fragment.newInstance(hotels));

    }

    @Override
    public void error(String message) {
        frameLayout.setVisibility(View.GONE);
        errorLayout.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        nav.setVisibility(View.GONE);
        /*Toast.makeText(this, "Fallo conexión con el servidor," +
                " al cargar el listado de hoteles", Toast.LENGTH_SHORT).show();*/
    }

    public void setFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_lst_hotels_fragment_container, fragment).commit();
    }

    public void initComponents(){
        frameLayout = findViewById(R.id.activity_lst_hotels_fragment_container);
        errorLayout = findViewById(R.id.activity_lst_hotels_error_loading);
        progressBar = findViewById(R.id.activity_lst_hotels_loading_progressBar);
        retryButton = findViewById(R.id.button_retry);
        nav = findViewById(R.id.activity_lst_hotel_nav);
    }

    public void initBottomNavigation(){
        nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_nav_category_hotels:
                        setFragment(Lst_hotels_category_fragment.newInstance(new ArrayList<>(hotels)));
                        break;
                    case R.id.menu_nav_rate_hotels:
                        setFragment(Lst_hotels_rate_fragment.newInstance(new ArrayList<>(hotels)));
                        break;
                    case R.id.menu_nav_price_desc_hotels:
                        setFragment(Lst_hotels_price_des_fragment.newInstance(new ArrayList<>(hotels)));
                        break;
                    case R.id.menu_nav_price_asc_hotels:
                        setFragment(Lst_hotels_price_asc_fragment.newInstance(new ArrayList<>(hotels)));
                }

                return false;
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}