package rodrigo.javier.booking.detailHotel.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import rodrigo.javier.booking.R;
import rodrigo.javier.booking.beans.Hotel;
import rodrigo.javier.booking.detailHotel.contract.DetailHotelContract;
import rodrigo.javier.booking.detailHotel.presenter.DetailHotelPresenter;
import rodrigo.javier.booking.lstRoom.view.LstRoomActivity;

public class DetailHotelActivity extends AppCompatActivity implements DetailHotelContract.View {

    private ImageView imgDetail;
    private TextView txtDetailHotel;
    private TextView txtDetailCategory;
    private TextView txtDetailRate;
    private TextView txtDetailCity;
    private TextView txtDetailDescription;
    private TextView txtDetailCost;
    private TextView txtDetailBooked;
    private Button btDetailButton;

    private DetailHotelPresenter presenter;

    private String nameHotel = "";
    private Hotel hotel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_hotel);

        initComponents();
        //Guardar el nombre del hotel al pulsar la etiqueta seleccionada
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            nameHotel = bundle.getString("nameHotel");
        }
        //Recoger el hotel seleccionado
        hotel = new Hotel();
        hotel.setName(nameHotel);
        //Comunicar con la clase Presenter desde el View
        presenter = new DetailHotelPresenter(this);
        presenter.getHotel(hotel);

        btDetailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), LstRoomActivity.class);
                intent.putExtra("nameHotel", hotel.getName());
                intent.putExtra("city", hotel.getCity());
                startActivity(intent);
            }
        });

    }

    public void initComponents(){
        imgDetail = findViewById(R.id.imgDetail);
        txtDetailHotel = findViewById(R.id.txtDetailHotel);
        txtDetailCategory = findViewById(R.id.txtDetailCategory);
        txtDetailRate = findViewById(R.id.txtDetailRate);
        txtDetailCity = findViewById(R.id.txtDetailCity);
        txtDetailDescription = findViewById(R.id.txtDetailDescription);
        txtDetailCost = findViewById(R.id.txtDetailCost);
        txtDetailBooked = findViewById(R.id.txtDetailBooked);
        btDetailButton = findViewById(R.id.btDetailButton);
    }

    //Mostrar los datos del hotel seleccionado
    @Override
    public void selectedHotel(Hotel hotel) {
        String urlImage ="http://192.168.0.12:8090/Booking/images/" +
                hotel.getUrlImage() + ".png";
        Picasso.get().load(urlImage).into(imgDetail);
        txtDetailHotel.setText(hotel.getName());
        txtDetailCategory.setText("Categoría: " + hotel.getCategory());
        txtDetailRate.setText("Valoración: " + hotel.getRate());
        txtDetailCity.setText(hotel.getCity());
        txtDetailDescription.setText(hotel.getDescription());
        txtDetailBooked.setText("Reservas: " + hotel.getBookedRooms());
        txtDetailCost.setText("Precio Medio: " + hotel.getAveragePrize());

    }
}