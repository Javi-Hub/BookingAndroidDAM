package rodrigo.javier.booking.bookedRooms.model;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

import rodrigo.javier.booking.beans.Hotel;
import rodrigo.javier.booking.bookedRooms.contract.BookedRoomContract;
import rodrigo.javier.booking.utils.Post;

public class BookedRoomModel implements BookedRoomContract.Model {

    private OnHotelsBookedListener onHotelsBookedListener;
    private ArrayList<Hotel> lstHotels;
    private ArrayList<Hotel> bookedHotels;

    @Override
    public void getHotelsMoreBooked(OnHotelsBookedListener onHotelsBookedListener) {
        this.onHotelsBookedListener = onHotelsBookedListener;
        HashMap<String, String> param = new HashMap<>();
        param.put("ACTION", "HOTEL.FIND_ALL");
        BackgroundTask task = new BackgroundTask(param);
        task.execute("http://192.168.0.12:8090/Booking/Controller");

    }

    class BackgroundTask extends AsyncTask<String, Integer, Boolean>    {
        private HashMap<String, String> parameters = null;

        public BackgroundTask(HashMap<String, String> parameters) {
            super();
            this.parameters = parameters;
        }

        public BackgroundTask() {
            super();
        }

        @Override
        protected Boolean doInBackground(String... params) {
            String url_select = params[0];
            try {
                Post post = new Post();
                JSONArray result = post.getServerDataPost(parameters, url_select);
                lstHotels = Hotel.getArrayListFromJSon(result);
            } catch (Exception e) {
                Log.e("log_tag", "Error in http connection " + e.toString());
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean resp) {
            try {
                if(resp){
                    if (lstHotels != null && lstHotels.size() > 0){
                        bookedHotels = Hotel.getListMoreBooked();
                        if (bookedHotels != null){
                            ArrayList<Hotel> tenBookedHotels = new ArrayList<>();
                            for (int i = 0; i < 10; i++) {
                                tenBookedHotels.add(bookedHotels.get(i));
                            }
                            onHotelsBookedListener.onResolve(tenBookedHotels);
                        }
                    }
                }
            }catch (Exception e) {
                onHotelsBookedListener.onReject("Fallo: Listar Hoteles");
            }
        }
    }
}
