package rodrigo.javier.booking.login.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import rodrigo.javier.booking.R;
import rodrigo.javier.booking.beans.User;
import rodrigo.javier.booking.detailRoom.view.DetailRoomActivity;
import rodrigo.javier.booking.login.contract.LoginContract;
import rodrigo.javier.booking.login.presenter.LoginPresenter;
import rodrigo.javier.booking.register.view.RegisterActivity;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    private LoginPresenter presenter;

    private EditText edtEmailLogin;
    private EditText edtPasswordLogin;
    private Button btLogin, btRegisterLogin;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;

    private String idRoom;
    private String nameHotel;
    private String city;
    private String capacity;
    private String prize;
    private String dateIn;
    private String dateOut;
    private String numPeople;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initComponents();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            idRoom = bundle.getString("id_room");
            nameHotel = bundle.getString("nameHotel");
            city = bundle.getString("city");
            capacity = bundle.getString("capacity");
            prize = bundle.getString("prize");
            dateIn = bundle.getString("dateIn");
            dateOut = bundle.getString("dateOut");
            numPeople = bundle.getString("numPeople");
        }

        presenter = new LoginPresenter(this);

        textInputLayoutEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textInputLayoutEmail.setError(null);
            }
        });

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmailLogin.getText().toString();
                String password = edtPasswordLogin.getText().toString();
                user = new User();
                user.setEmail(email);
                user.setPassword(password);

                if (email.isEmpty() || password.isEmpty()){
                    String message = "Campo obligatorio";
                    errorLogin(message);
                } else {
                    presenter.getUser(getBaseContext(), user);
                }
            }
        });

        btRegisterLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), RegisterActivity.class);
                intent.putExtra("id_room", idRoom);
                intent.putExtra("city", city);
                intent.putExtra("nameHotel", nameHotel);
                intent.putExtra("capacity", capacity);
                intent.putExtra("prize", prize);
                intent.putExtra("dateIn", dateIn);
                intent.putExtra("dateOut", dateOut);
                intent.putExtra("numPeople", numPeople);
                startActivity(intent);
            }
        });

}

    @Override
    public void success(User user) {
        Intent intent = new Intent(getBaseContext(), DetailRoomActivity.class);
        intent.putExtra("id_room", idRoom);
        intent.putExtra("city", city);
        intent.putExtra("nameHotel", nameHotel);
        intent.putExtra("idUser", user.getId());
        intent.putExtra("capacity", capacity);
        intent.putExtra("prize", prize);
        intent.putExtra("dateIn", dateIn);
        intent.putExtra("dateOut", dateOut);
        intent.putExtra("numPeople", numPeople);
        intent.putExtra("user", user);
        startActivity(intent);
        textInputLayoutEmail.setError(null);
 }

    @Override
    public void error(String error) {
        //Toast.makeText(this, error, Toast.LENGTH_LONG).show();
        textInputLayoutEmail.setError(error);
    }

    public void initComponents(){
        edtEmailLogin = findViewById(R.id.edtEmailLogin);
        edtPasswordLogin = findViewById(R.id.edtPasswordLogin);
        btLogin = findViewById(R.id.btLogin);
        btRegisterLogin = findViewById(R.id.btRegisterLogin);
        textInputLayoutEmail = findViewById(R.id.activity_login_textinput_layout_email);
        textInputLayoutPassword = findViewById(R.id.activity_login_textinput_layout_password);
    }

    public void errorLogin(String message){
       //Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        textInputLayoutEmail.setError(message);
        textInputLayoutPassword.setError(message
        );

    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}