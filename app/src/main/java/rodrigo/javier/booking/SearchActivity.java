package rodrigo.javier.booking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import rodrigo.javier.booking.R;
import rodrigo.javier.booking.SplashScreenActivity;
import rodrigo.javier.booking.beans.Hotel;
import rodrigo.javier.booking.bookedRooms.view.BookedRoomActivity;
import rodrigo.javier.booking.fieldSearch.view.FieldSearchActivity;
import rodrigo.javier.booking.lstHotel.view.LstHotelActivity;

public class SearchActivity extends AppCompatActivity {

    private Spinner searchSpin;
    private Button btSearch;
    private EditText edtSearchCity;
    private EditText edtSearchNumberPeople;

    private Hotel hotel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initComponents();
        //Adaptador para capturar la selección desde el Spinner
        ArrayAdapter<CharSequence> spinAdapter = ArrayAdapter.createFromResource(this,
                R.array.searchSpin, R.layout.spinner_layout);
        searchSpin.setAdapter(spinAdapter);

        //Capturar la selección pulsada en el spinner
        searchSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getSelectedItem().toString().equals("Todos los hoteles")){
                    searchSpin.setSelection(position);
                    Intent intent = new Intent(getBaseContext(), LstHotelActivity.class);
                    startActivity(intent);

                } else if(parent.getSelectedItem().toString().equals("Los 10 hoteles mas reservados")){
                    searchSpin.setSelection(position);
                    Intent intent = new Intent(getBaseContext(), BookedRoomActivity.class);
                            startActivity(intent);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (edtSearchCity != null){
            btSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String city = edtSearchCity.getText().toString();
                    Intent intent = new Intent(getBaseContext(), FieldSearchActivity.class);
                    intent.putExtra("city", city);
                    startActivity(intent);
                }
            });
        }
    }

    public void initComponents(){
        searchSpin = findViewById(R.id.searchSpin);
        btSearch = findViewById(R.id.btSearch);
        edtSearchCity = findViewById(R.id.edtSearchCity);
        edtSearchNumberPeople = findViewById(R.id.edtSearchNumberPeople);
    }
}