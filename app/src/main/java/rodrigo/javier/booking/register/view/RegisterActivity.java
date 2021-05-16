package rodrigo.javier.booking.register.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import rodrigo.javier.booking.R;
import rodrigo.javier.booking.SplashEndActivity;
import rodrigo.javier.booking.beans.User;
import rodrigo.javier.booking.login.view.LoginActivity;
import rodrigo.javier.booking.register.contract.RegisterContract;
import rodrigo.javier.booking.register.presenter.RegisterPresenter;

public class RegisterActivity extends AppCompatActivity implements RegisterContract.View {

    private RegisterPresenter presenter;

    private EditText edtNameRegister;
    private EditText edtSurenameRegister;
    private EditText edtEmailRegister;
    private EditText edtPasswordRegister;
    private TextInputLayout txtInputLayoutRegisterName, txtInputLayoutRegisterSurename,
            txtInputLayoutRegisterEmail, txtInputLayoutRegisterPassword;
    private Button btRegister;

    private String idRoom;
    private String nameHotel;
    private String city;
    private String capacity;
    private String prize;

    public static String TAG = RegisterActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initComponents();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            idRoom = bundle.getString("id_room");
            nameHotel = bundle.getString("nameHotel");
            city = bundle.getString("city");
            capacity = bundle.getString("capacity");
            prize = bundle.getString("prize");
            Log.d(TAG, "[getBundle nameHotel -> ]" + nameHotel);
        }

        presenter = new RegisterPresenter(this);

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = String.valueOf(edtNameRegister.getText());
                String surename = String.valueOf(edtSurenameRegister.getText());
                String email = String.valueOf(edtEmailRegister.getText());
                String password = String.valueOf(edtPasswordRegister.getText());
                User user = new User();
                user.setName(name);
                user.setSurename(surename);
                user.setEmail(email);
                user.setPassword(password);
                if (name.isEmpty() || surename.isEmpty() || email.isEmpty() || password.isEmpty()){
                    String message = "Debe rellenar todos los campos";
                    errorRegister(message);
                } else {
                    presenter.doRegister(getBaseContext(), user);
                }
            }
        });

    }



    public void initComponents(){
        edtNameRegister = findViewById(R.id.edtNameRegister);
        edtSurenameRegister = findViewById(R.id.edtSurenameRegister);
        edtEmailRegister = findViewById(R.id.edtEmailRegister);
        edtPasswordRegister = findViewById(R.id.edtPasswordRegister);
        txtInputLayoutRegisterName = findViewById(R.id.txtInputLayoutRegisterName);
        txtInputLayoutRegisterSurename = findViewById(R.id.txtInputLayoutRegisterSurename);
        txtInputLayoutRegisterEmail = findViewById(R.id.txtInputLayoutRegisterEmail);
        txtInputLayoutRegisterPassword = findViewById(R.id.txtInputLayoutRegisterPassword);
        btRegister = findViewById(R.id.btRegister);
    }

    public void errorRegister(String message) {
        txtInputLayoutRegisterName.setError(message);
        txtInputLayoutRegisterSurename.setError(message);
        txtInputLayoutRegisterEmail.setError(message);
        txtInputLayoutRegisterPassword.setError(message);
    }

    @Override
    public void success(String message) {
        Intent intent = new Intent(getBaseContext(), LoginActivity.class);
        intent.putExtra("id_room", idRoom);
        intent.putExtra("city", city);
        intent.putExtra("nameHotel", nameHotel);
        intent.putExtra("capacity", capacity);
        intent.putExtra("prize", prize);
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        startActivity(intent);
    }

    @Override
    public void error(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}