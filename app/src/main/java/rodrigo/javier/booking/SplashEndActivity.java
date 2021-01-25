package rodrigo.javier.booking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

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
                                getBaseContext(), SearchActivity.class);
                        startActivity(intent);
                    }
                }, 4000);
    }
}