package rodrigo.javier.booking.lstRoom.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

import rodrigo.javier.booking.R;
import rodrigo.javier.booking.beans.Hotel;
import rodrigo.javier.booking.beans.Room;
import rodrigo.javier.booking.lstRoom.adapter.LstRoomAdapter;
import rodrigo.javier.booking.lstRoom.contract.LstRoomContract;
import rodrigo.javier.booking.lstRoom.presenter.LstRoomPresenter;

public class LstRoomActivity extends AppCompatActivity implements LstRoomContract.View {

    private RecyclerView recycler;
    private DividerItemDecoration divider;
    private RecyclerView.LayoutManager lManager;
    private LstRoomPresenter presenter;

    private String nameHotel = "";
    private String city = "";
    private Hotel hotel;
    private String dateIn, dateOut, numPeople;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lst_room);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            nameHotel = bundle.getString("nameHotel");
            city = bundle.getString("city");
            dateIn = bundle.getString("dateIn");
            dateOut = bundle.getString("dateOut");
            numPeople = bundle.getString("numPeople");
        }
        System.out.println(dateIn + dateOut);

        hotel = new Hotel();
        hotel.setName(nameHotel);
        //Comunicar con la clase Presenter desde el View
        presenter = new LstRoomPresenter(this);
        presenter.getRooms(this, hotel);

        // Obtener el Recycler
        recycler = findViewById(R.id.recyclerLstRoom);
        recycler.setHasFixedSize(true);

        //Usar el administrador para LinearLayout
        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

    }

    @Override
    public void success(ArrayList<Room> lstRooms) {
        LstRoomAdapter adapter = new LstRoomAdapter(lstRooms, dateIn, dateOut, numPeople);
        divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(getResources().getDrawable(R.drawable.shape_text_view_rate));
        recycler.addItemDecoration(divider);
        recycler.setAdapter(adapter);
    }

    @Override
    public void error(String error) {
        Toast.makeText(this, "Fallo conexión con el servidor," +
                " al cargar el listado de hoteles", Toast.LENGTH_SHORT).show();

    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}