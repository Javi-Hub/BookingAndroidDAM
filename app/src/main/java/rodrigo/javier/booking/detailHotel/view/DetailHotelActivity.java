package rodrigo.javier.booking.detailHotel.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import rodrigo.javier.booking.BuildConfig;
import rodrigo.javier.booking.R;
import rodrigo.javier.booking.beans.Hotel;
import rodrigo.javier.booking.lstRoom.view.LstRoomActivity;

public class DetailHotelActivity extends AppCompatActivity {

    private ImageView imgDetail;
    private TextView txtDetailHotel;
    private TextView txtDetailCategory;
    private TextView txtDetailRate;
    private TextView txtDetailCity;
    private TextView txtDetailDescription;
    private TextView txtDetailCost;
    private TextView txtDetailBooked;
    private Button btDetailButton;
    private RatingBar ratingBar;
    private Toolbar toolbar;

    private String nameHotel = "";
    private String title;
    private String dateIn, dateOut, numPeople;
    private Hotel hotel;

    public static String TAG = DetailHotelActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_hotel);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        initComponents();
        //Guardar el nombre del hotel al pulsar la etiqueta seleccionada
        Bundle bundle = getIntent().getExtras();
        hotel = (Hotel) getIntent().getSerializableExtra("hotel");
        Log.d(TAG, " [getHotel] Image: " + hotel.getUrlImage());
        Log.d(TAG, "[getHotel] Name: " + hotel.getName());

        if (bundle != null){
            nameHotel = bundle.getString("nameHotel");
            dateIn = bundle.getString("dateIn");
            dateOut = bundle.getString("dateOut");
            numPeople = bundle.getString("numPeople");
        }

        selectedHotel();

        //Recoger el hotel seleccionado
        /*hotel = new Hotel();
        hotel.setName(nameHotel);*/

        btDetailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), LstRoomActivity.class);
                intent.putExtra("nameHotel", hotel.getName());
                intent.putExtra("city", hotel.getCity());
                intent.putExtra("dateIn", dateIn);
                intent.putExtra("dateOut", dateOut);
                intent.putExtra("numPeople", numPeople);
                startActivity(intent);
            }
        });

    }

    public void initComponents(){
        imgDetail = findViewById(R.id.imgDetail);
        txtDetailHotel = findViewById(R.id.txtDetailHotel);
        txtDetailRate = findViewById(R.id.txtDetailRate);
        txtDetailCity = findViewById(R.id.txtDetailCity);
        txtDetailDescription = findViewById(R.id.txtDetailDescription);
        txtDetailCost = findViewById(R.id.txtDetailCost);
        txtDetailBooked = findViewById(R.id.txtDetailBooked);
        btDetailButton = findViewById(R.id.btDetailButton);
        ratingBar = findViewById(R.id.detail_rating_bar);
        toolbar = findViewById(R.id.toolbar);
    }

    //Mostrar los datos del hotel seleccionado
    public void selectedHotel() {
        AlphaAnimation alpha = new AlphaAnimation(0.3f, 1.0f);
        alpha.setDuration(1000);
        alpha.setStartOffset(1000);
        alpha.setFillAfter(true);
        String urlImage = BuildConfig.SERVER_URL + "images/" +
                hotel.getUrlImage() + ".png";
        Picasso.get().load(urlImage).into(imgDetail);
        imgDetail.startAnimation(alpha);
        txtDetailHotel.setText(hotel.getName());
        ratingBar.setRating(hotel.getCategory());
        txtDetailRate.setText(String.valueOf(hotel.getRate()));
        txtDetailCity.setText(hotel.getCity());
        txtDetailDescription.setText(hotel.getDescription());
        txtDetailBooked.setText(String.valueOf(hotel.getBookedRooms()));
        txtDetailCost.setText(String.valueOf(hotel.getAveragePrize()));
        title = hotel.getName();
        toolbar.setTitle(title);

    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}