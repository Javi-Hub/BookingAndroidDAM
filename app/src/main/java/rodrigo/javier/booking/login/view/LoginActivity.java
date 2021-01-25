package rodrigo.javier.booking.login.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import rodrigo.javier.booking.R;
import rodrigo.javier.booking.SearchActivity;
import rodrigo.javier.booking.beans.User;
import rodrigo.javier.booking.detailRoom.view.DetailRoomActivity;
import rodrigo.javier.booking.login.contract.LoginContract;
import rodrigo.javier.booking.login.presenter.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    private LoginPresenter presenter;

    private EditText edtEmailLogin;
    private EditText edtPasswordLogin;
    private Button btLogin;

    private String idRoom;
    private String nameHotel;
    private String city;
    private String capacity;
    private String prize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initComponents();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            idRoom = bundle.getString("id_room");
            nameHotel = bundle.getString("nameHotel");
            city = bundle.getString("city");
            capacity = bundle.getString("capacity");
            prize = bundle.getString("prize");
        }

        presenter = new LoginPresenter(this);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmailLogin.getText().toString();
                String password = edtPasswordLogin.getText().toString();
                User user = new User();
                user.setEmail(email);
                user.setPassword(password);

                if (email.isEmpty() || password.isEmpty()){
                    String message = "Rellenar todos los campos";
                    errorLogin(message);
                } else {
                    presenter.getUser(user);
                }
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
        startActivity(intent);
        Toast.makeText(this, "Bienvenido " + user.getName(), Toast.LENGTH_LONG).show();
 }

    @Override
    public void error(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    public void initComponents(){
        edtEmailLogin = findViewById(R.id.edtEmailLogin);
        edtPasswordLogin = findViewById(R.id.edtPasswordLogin);
        btLogin = findViewById(R.id.btLogin);
    }

    public void errorLogin(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

    }
}