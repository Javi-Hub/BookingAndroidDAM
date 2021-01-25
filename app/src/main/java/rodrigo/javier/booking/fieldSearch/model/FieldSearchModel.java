package rodrigo.javier.booking.fieldSearch.model;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

import rodrigo.javier.booking.beans.Hotel;
import rodrigo.javier.booking.fieldSearch.contract.FieldSearchContract;
import rodrigo.javier.booking.lstHotel.contract.LstHotelContract;
import rodrigo.javier.booking.utils.Post;

public class FieldSearchModel implements FieldSearchContract.Model {

    private OnFieldSearchListener onFieldSearchListener;
    private ArrayList<Hotel> lstHotels;

    @Override
    public void getHotelsService(OnFieldSearchListener onFieldSearchListener, String city) {
        this.onFieldSearchListener = onFieldSearchListener;
        HashMap<String, String> param = new HashMap<>();
        param.put("ACTION", "HOTEL.FIND_FILTER");
        param.put("CITY", city);
        BackgroundTask task = new BackgroundTask(param);
        task.execute("http://192.168.0.12:8090/Booking/Controller");
    }

    class BackgroundTask extends AsyncTask<String, Integer, Boolean>{

        private HashMap<String, String> parameters = null;

        public BackgroundTask(HashMap<String, String> parameters) {
            super();
            this.parameters = parameters;
        }

        @Override
        protected Boolean doInBackground(String... params) {
            String url_select = params [0];
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
                        onFieldSearchListener.onResolve(lstHotels);
                    }
                }
            }catch (Exception e) {
                onFieldSearchListener.onReject("Fallo: Listar Hoteles");
            }
        }

    }

}