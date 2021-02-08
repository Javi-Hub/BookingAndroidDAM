package rodrigo.javier.booking.detailRoom.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import rodrigo.javier.booking.R;
import rodrigo.javier.booking.SearchActivity;
import rodrigo.javier.booking.SplashEndActivity;
import rodrigo.javier.booking.beans.BookRoom;
import rodrigo.javier.booking.beans.Hotel;
import rodrigo.javier.booking.beans.Room;
import rodrigo.javier.booking.bookedRooms.contract.BookedRoomContract;
import rodrigo.javier.booking.bookedRooms.presenter.BookedRoomPresenter;
import rodrigo.javier.booking.detailRoom.contract.DetailRoomContract;
import rodrigo.javier.booking.detailRoom.presenter.DetailRoomPresenter;

public class DetailRoomActivity extends AppCompatActivity implements DetailRoomContract.View {

    private DetailRoomPresenter presenter;

    private ImageView imgDetailRoom;
    private TextView txtDetailRoomHotel;
    private TextView txtDetailRoomCity;
    private TextView txtDetailRoomNumber;
    private TextView txtDetailRoomPeople;
    private EditText edtDetailRoomDays;
    private TextView txtDetailRoomCost;
    private Button btEndBook;

    private int idRoom;
    private String city;
    private String nameHotel;
    private int idUser;
    private String capacity;
    private String prize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_room);
        initComponents();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            idRoom = Integer.parseInt(bundle.getString("id_room")) ;
            city = bundle.getString("city");
            nameHotel = bundle.getString("nameHotel");
            idUser = bundle.getInt("idUser");
            capacity = bundle.getString("capacity");
            prize = bundle.getString("prize");
        }

        presenter = new DetailRoomPresenter(this);

        txtDetailRoomCity.setText(city);
        txtDetailRoomHotel.setText(nameHotel);
        txtDetailRoomNumber.setText("Habitación Nº: " + idRoom);
        txtDetailRoomPeople.setText("Nº Personas: " + capacity);
        txtDetailRoomCost.setText("Precio: " + prize);

        btEndBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               BookRoom bookRoom = new BookRoom();
               bookRoom.setIdUser(idUser);
               bookRoom.setIdRoom(idRoom);

               if (edtDetailRoomDays.getText().toString().isEmpty()){
                   String message = "Debe rellenar el campo días";
                   errorBook(message);
               } else {
                   presenter.doBook(bookRoom);
               }

            }
        });

    }

    public void initComponents(){
        imgDetailRoom = findViewById(R.id.imgDetailRoom);
        txtDetailRoomHotel = findViewById(R.id.txtDetailRoomHotel);
        txtDetailRoomCity = findViewById(R.id.txtDetailRoomCity);
        txtDetailRoomNumber = findViewById(R.id.txtDetailRoomNumber);
        txtDetailRoomPeople = findViewById(R.id.txtDetailRoomPeople);
        edtDetailRoomDays = findViewById(R.id.edtDetailRoomDays);
        txtDetailRoomCost = findViewById(R.id.txtDetailRoomCost);
        btEndBook = findViewById(R.id.btEndBook);
    }
    public void errorBook(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }


    @Override
    public void success(String message) {
        Intent intent = new Intent(getBaseContext(), SplashEndActivity.class);
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        startActivity(intent);
    }

    @Override
    public void error(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}