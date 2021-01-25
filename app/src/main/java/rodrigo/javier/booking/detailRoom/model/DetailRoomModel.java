package rodrigo.javier.booking.detailRoom.model;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

import rodrigo.javier.booking.beans.BookRoom;
import rodrigo.javier.booking.detailRoom.contract.DetailRoomContract;
import rodrigo.javier.booking.utils.Post;

public class DetailRoomModel implements DetailRoomContract.Model {

    OnBookRoomListener onBookRoomListener;
    private ArrayList<BookRoom> list;

    @Override
    public void doBookService(OnBookRoomListener onBookRoomListener, BookRoom bookRoom) {
        this.onBookRoomListener = onBookRoomListener;
        HashMap<String, String> param = new HashMap<>();
        param.put("ACTION", "BOOKROOM.INSERT");
        param.put("USER", String.valueOf(bookRoom.getIdUser()));
        param.put("ROOM", String.valueOf(bookRoom.getIdRoom()));
        BackgroundTask task = new BackgroundTask(param);
        task.execute("http://192.168.0.12:8090/Booking/Controller");
    }

        class BackgroundTask extends AsyncTask<String, Integer, Boolean> {

            private HashMap<String, String> parameters = null;

            public BackgroundTask(HashMap<String, String> parameters) {
                super();
                this.parameters = parameters;
            }

            @Override
            protected Boolean doInBackground(String... params) {

                String url_select = params[0];
                try {
                    Post post = new Post();
                    JSONArray result = post.getServerDataPost(parameters, url_select);
                    list = BookRoom.getArrayListFromJSon(result);
                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection " + e.toString());
                }

                return true;
            }

            @Override
            protected void onPostExecute(Boolean resp) {
                try {
                    if(resp){
                        onBookRoomListener.onResolve("Reseva finalizada con éxito");
                    }
                }catch (Exception e) {
                    onBookRoomListener.onReject("Fallo al realizar la reservar");
                }
            }
        }


}
