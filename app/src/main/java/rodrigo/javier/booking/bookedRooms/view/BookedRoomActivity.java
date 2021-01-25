package rodrigo.javier.booking.bookedRooms.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

import rodrigo.javier.booking.R;
import rodrigo.javier.booking.beans.Hotel;
import rodrigo.javier.booking.bookedRooms.adapter.BookedRoomAdapter;
import rodrigo.javier.booking.bookedRooms.contract.BookedRoomContract;
import rodrigo.javier.booking.bookedRooms.presenter.BookedRoomPresenter;

public class BookedRoomActivity extends AppCompatActivity implements BookedRoomContract.View {

    private RecyclerView recycler;
    private RecyclerView.LayoutManager lManager;
    private BookedRoomPresenter presenter;
    private DividerItemDecoration divider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booked_room);

        presenter = new BookedRoomPresenter(this);
        presenter.getHotelsBooked();

        // Obtener el Recycler
        recycler = findViewById(R.id.recyclerMoreBooked);
        recycler.setHasFixedSize(true);

        //Usar el administrador para LinearLayout
        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

    }

    @Override
    public void successMoreBooked(ArrayList<Hotel> hotels) {
        showListBooked(hotels);
    }

    @Override
    public void error(String message) {
        Toast.makeText(this, "Fallo conexión con el servidor," +
                " al cargar el listado de hoteles", Toast.LENGTH_SHORT).show();

    }

    public void showListBooked(ArrayList<Hotel> hotels){
        BookedRoomAdapter adapter = new BookedRoomAdapter(hotels);
        recycler = findViewById(R.id.recyclerMoreBooked);
        divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recycler.addItemDecoration(divider);
        recycler.setAdapter(adapter);
    }
}