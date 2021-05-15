package rodrigo.javier.booking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import rodrigo.javier.booking.main.Main;

public class SplashEndActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_end);

        //SplashScreen
        final Handler handler = new Handler();
        handler.postDelayed(
                new Runnable() { // Interface
                    @Override
                    public void run() {
                        // Cargar la 2ª pantalla
                        Intent intent = new Intent(
                                getBaseContext(), Main.class);
                        startActivity(intent);
                    }
                }, 4000);
    }
}