package rodrigo.javier.booking.detailRoom.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.nio.DoubleBuffer;
import java.sql.Date;
import java.util.Calendar;
import java.util.IllegalFormatCodePointException;
import java.util.Random;

import rodrigo.javier.booking.R;
import rodrigo.javier.booking.SplashEndActivity;
import rodrigo.javier.booking.beans.BookRoom;
import rodrigo.javier.booking.beans.User;
import rodrigo.javier.booking.detailRoom.contract.DetailRoomContract;
import rodrigo.javier.booking.detailRoom.presenter.DetailRoomPresenter;

public class DetailRoomActivity extends AppCompatActivity implements DetailRoomContract.View {

    private DetailRoomPresenter presenter;

    private ImageView imgDetailRoom;
    private TextView txtDetailRoomHotel;
    private TextView txtDetailRoomCity;
    private TextView txtDetailRoomNumber;
    private EditText edtDetailRoomPeople;
    private TextView txtDetailRoomDays;
    private TextView txtDetailRoomCost;
    private TextView txtBookNumber;
    private TextView txtDetailRoomUser;
    private EditText edtDetailRoomDateIn;
    private EditText edtDetailRoomDateOut;
    private DatePickerDialog pickerDialog;
    private Button btEndBook;

    private int idRoom;
    private String city;
    private String nameHotel;
    private int idUser;
    private String capacity;
    private String prize;
    private String dateIn;
    private String dateOut;
    private String numPeople;
    private int days;
    private User user;
    private BookRoom bookRoom;

    public static String TAG = DetailRoomActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_room);
        initComponents();
        txtBookNumber.setText(String.valueOf(bookNumber()));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setDateIn();
        setDateOut();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            user = (User) getIntent().getSerializableExtra("user");
            Log.d(TAG, "[getUser] Name: " + user.getName());
            idRoom = Integer.parseInt(bundle.getString("id_room"));
            city = bundle.getString("city");
            nameHotel = bundle.getString("nameHotel");
            idUser = bundle.getInt("idUser");
            capacity = bundle.getString("capacity");
            prize = bundle.getString("prize");
            dateIn = bundle.getString("dateIn");
            dateOut = bundle.getString("dateOut");
            numPeople = bundle.getString("numPeople");
        }

        txtDetailRoomUser.setText(user.getName());

        presenter = new DetailRoomPresenter(this);

            txtDetailRoomCity.setText(city);
            txtDetailRoomHotel.setText(nameHotel);
            txtDetailRoomNumber.setText(String.valueOf(idRoom));
            txtDetailRoomCost.setText(prize);

            if (dateIn != null){
                edtDetailRoomDateIn.setText(dateIn);
            }
            if (dateOut != null){
                edtDetailRoomDateOut.setText(dateOut);
            }
            if (dateIn != null && dateOut != null){
                txtDetailRoomDays.setText(String.valueOf(calculateDate(dateIn, dateOut)));
            }
            if (numPeople != null){
                edtDetailRoomPeople.setText(numPeople);
            }

            if (dateIn != null && dateOut != null && numPeople != null){
                bookRoom = new BookRoom();
                bookRoom.setIdUser(idUser);
                bookRoom.setIdRoom(idRoom);
                bookRoom.setNumberDays(calculateDate(dateIn, dateOut));
                bookRoom.setNumberPeople(Integer.parseInt(numPeople));
                bookRoom.setDateIn(dateIn);
                bookRoom.setDateOut(dateOut);

                int days = Integer.parseInt(txtDetailRoomDays.getText().toString());
                double priceFinal = Double.parseDouble(prize);
                int numPeopleFinal = Integer.parseInt(numPeople);
                double totalPrize = calculatePrize(days, priceFinal, numPeopleFinal);
                txtDetailRoomCost.setText(String.valueOf(totalPrize));
                bookRoom.setCost(totalPrize);
            }
            
            btEndBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtDetailRoomDays.performClick();

               if (txtDetailRoomDays.getText().toString().isEmpty()){
                   String message = "Debe rellenar el campo días";
                   errorBook(message);
               } else {
                   presenter.doBook(getBaseContext(), bookRoom);

               }
            }
        });

        txtDetailRoomDays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtDetailRoomPeople.getText().toString().isEmpty()){
                    System.out.println("Introduce el número de personas");
                } else {
                    bookRoom = new BookRoom();
                    dateIn = edtDetailRoomDateIn.getText().toString();
                    dateOut = edtDetailRoomDateOut.getText().toString();
                    prize = txtDetailRoomCost.getText().toString();
                    days = calculateDate(String.valueOf(edtDetailRoomDateIn.getText()), String.valueOf(edtDetailRoomDateOut.getText()));
                    txtDetailRoomDays.setText(String.valueOf(days));
                    numPeople = edtDetailRoomPeople.getText().toString();
                    bookRoom.setIdUser(idUser);
                    bookRoom.setIdRoom(idRoom);
                    bookRoom.setNumberDays(days);
                    bookRoom.setNumberPeople(Integer.parseInt(numPeople));
                    bookRoom.setDateIn(dateIn);
                    bookRoom.setDateOut(dateOut);
                    bookRoom.setCost(Double.parseDouble(prize));
                }
            }
        });

 }

    public void initComponents(){
        //imgDetailRoom = findViewById(R.id.imgDetailRoom);
        txtDetailRoomHotel = findViewById(R.id.txtDetailRoomHotel);
        txtDetailRoomCity = findViewById(R.id.txtDetailRoomCity);
        txtDetailRoomNumber = findViewById(R.id.txtDetailRoomNumber);
        txtBookNumber = findViewById(R.id.txtBookNumber);
        edtDetailRoomPeople = findViewById(R.id.edtDetailRoomPeople);
        txtDetailRoomDays = findViewById(R.id.txtDetailRoomDays);
        edtDetailRoomDateIn = findViewById(R.id.edtDetailRoomDateIn);
        edtDetailRoomDateOut = findViewById(R.id.edtDetailRoomDateOut);
        txtDetailRoomCost = findViewById(R.id.txtDetailRoomCost);
        txtDetailRoomUser = findViewById(R.id.txtDetailRoomUser);
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

    public void setDateIn() {
        edtDetailRoomDateIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
                // DatePickerDialog
                pickerDialog = new DatePickerDialog(DetailRoomActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        if (dayOfMonth < 10 && monthOfYear < 9)
                            edtDetailRoomDateIn.setText(year + "-0" + (monthOfYear + 1) + "-0" + dayOfMonth);

                        else if (dayOfMonth < 10 && monthOfYear > 8)
                            edtDetailRoomDateIn.setText(year + "-" + (monthOfYear + 1) + "-0" + dayOfMonth);

                        else if (dayOfMonth > 9 && monthOfYear < 9)
                            edtDetailRoomDateIn.setText(year + "-0" + (monthOfYear + 1) + "-" + dayOfMonth);

                        else
                            edtDetailRoomDateIn.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);

                    }

                }, year, month, day);

                pickerDialog.show();

            }

        });
    }

    public void setDateOut() {
        edtDetailRoomDateOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
                // DatePickerDialog
                pickerDialog = new DatePickerDialog(DetailRoomActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        if (dayOfMonth < 10 && monthOfYear < 9)
                            edtDetailRoomDateOut.setText(year + "-0" + (monthOfYear + 1) + "-0" + dayOfMonth);

                        else if (dayOfMonth < 10 && monthOfYear > 8)
                            edtDetailRoomDateOut.setText(year + "-" + (monthOfYear + 1) + "-0" + dayOfMonth);

                        else if (dayOfMonth > 9 && monthOfYear < 9)
                            edtDetailRoomDateOut.setText(year + "-0" + (monthOfYear + 1) + "-" + dayOfMonth);

                        else
                            edtDetailRoomDateOut.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);

                        int days = calculateDate(edtDetailRoomDateIn.getText().toString(),
                                edtDetailRoomDateOut.getText().toString());
                        double prizeFinal = Double.parseDouble(prize);
                        int numPeople = Integer.parseInt(edtDetailRoomPeople.getText().toString());

                        txtDetailRoomDays.setText(String.valueOf(days));

                        double totalPrize = calculatePrize(days, prizeFinal, numPeople);
                        txtDetailRoomCost.setText(String.valueOf(totalPrize));

                    }

                }, year, month, day);

                pickerDialog.show();

            }

        });
    }

    public static int calculateDate(String date1, String date2){
        int milisecondsByDay = 86400000;
        Date checkIn = Date.valueOf(date1);
        Date checkOut = Date.valueOf(date2);
        int days = (int) ((checkOut.getTime() - checkIn.getTime()) / milisecondsByDay);
        return days;

    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public static double calculatePrize(int days, double prize, int people){
        return (double) days * prize * people;
    }

    public int bookNumber(){
        Random rd = new Random();
        int number = rd.nextInt(100000);
        return number;
    }

}