package rodrigo.javier.booking.lstRoom.model;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

import rodrigo.javier.booking.beans.Hotel;
import rodrigo.javier.booking.beans.Room;
import rodrigo.javier.booking.lstRoom.contract.LstRoomContract;
import rodrigo.javier.booking.utils.Post;

public class LstRoomModel implements LstRoomContract.Model {

    private OnLstRoomsListener onLstRoomsListener;
    private ArrayList<Room> lstRooms;

    @Override
    public void getRoomsService(OnLstRoomsListener onLstRoomsListener, Hotel hotel) {
        this.onLstRoomsListener = onLstRoomsListener;
        HashMap<String, String> param = new HashMap<>();
        param.put("ACTION", "ROOM.FIND_FILTER");
        param.put("NAME_HOTEL", hotel.getName());
        BackgroundTask task = new BackgroundTask(param);
        task.execute("http://192.168.0.12:8090/Booking/Controller");

    }

    class BackgroundTask extends AsyncTask<String, Integer, Boolean>{
        private HashMap<String, String> parameters = null;

        public BackgroundTask(HashMap<String, String> parameters) {
            this.parameters = parameters;
        }

        @Override
        protected Boolean doInBackground(String... params) {
            String url_select = params[0];
            try {
                Post post = new Post();
                JSONArray result = post.getServerDataPost(parameters, url_select);
                lstRooms = Room.getArrayListFromJSon(result);

            } catch (Exception e) {
                Log.e("log_tag", "Error in http connection " + e.toString());
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean resp) {
            try {
                if(resp){
                if (lstRooms != null && lstRooms.size() > 0){
                        onLstRoomsListener.onResolve(lstRooms);
                    }
                }
            }catch (Exception e) {
                onLstRoomsListener.onReject("Fallo: Listar Hoteles");
            }
        }
    }
}
