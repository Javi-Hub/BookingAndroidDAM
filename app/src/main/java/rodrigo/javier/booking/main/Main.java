package rodrigo.javier.booking.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import rodrigo.javier.booking.R;
import rodrigo.javier.booking.bookedRooms.view.BookedRoomActivity;
import rodrigo.javier.booking.lstHotel.view.LstHotelActivity;

public class Main extends AppCompatActivity {

    private FrameLayout frameLayout;
    private BottomNavigationView nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setFragment(Search_fragment.newInstance());
        initComponents();
        initBottomNavigation();
    }

    private void initComponents(){
        frameLayout = findViewById(R.id.frame_container_activity_main);
        nav = findViewById(R.id.activity_main_nav);
    }

    public void setFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container_activity_main, fragment).commit();
    }

    public void initBottomNavigation(){
        nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.main_menu_nav_search:
                        setFragment(Search_fragment.newInstance());
                        break;
                    case R.id.main_menu_nav_trips:
                        setFragment(TripsFragmentMain.newInstance());
                        break;
                    case R.id.main_menu_nav_sign_in:
                        setFragment(LoginFragmentMain.newInstance());
                        break;
                }

                return false;
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.top_selection_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.top_selection_all){
            Intent intentAllHotels = new Intent(getBaseContext(), LstHotelActivity.class);
            startActivity(intentAllHotels);
        } else if (id == R.id.top_selection_top10){
            Intent intentTop = new Intent(getBaseContext(), BookedRoomActivity.class);
            startActivity(intentTop);
        }
        return super.onOptionsItemSelected(item);
    }
}