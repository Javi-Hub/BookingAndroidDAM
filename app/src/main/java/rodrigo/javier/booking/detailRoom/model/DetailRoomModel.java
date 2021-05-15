package rodrigo.javier.booking.detailRoom.model;

import android.content.Context;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rodrigo.javier.booking.beans.BookRoom;
import rodrigo.javier.booking.detailRoom.contract.DetailRoomContract;
import rodrigo.javier.booking.retrofit.ApiClient;

public class DetailRoomModel implements DetailRoomContract.Model {

    @Override
    public void doBookService(Context context, OnBookRoomListener onBookRoomListener, BookRoom bookRoom) {
        ApiClient apiClient = new ApiClient(context);
        final Call<BookRoom> request = apiClient.setBookRoom(bookRoom.getIdUser(), bookRoom.getIdRoom(), bookRoom.getDateIn(),
                                                                bookRoom.getDateOut(), bookRoom.getNumberDays(),
                                                                bookRoom.getNumberPeople(), bookRoom.getCost());

        request.enqueue(new Callback<BookRoom>() {
            @Override
            public void onResponse(Call<BookRoom> call, Response<BookRoom> response) {
                if (response != null && response.body() != null){
                    onBookRoomListener.onResolve("Reserva realizada con éxito");
                    System.out.println(bookRoom.getNumberDays());
                }
            }

            @Override
            public void onFailure(Call<BookRoom> call, Throwable t) {
                onBookRoomListener.onReject("Reserva no realizada correctamente");
            }
        });
    }



    /*OnBookRoomListener onBookRoomListener;
    private ArrayList<BookRoom> list;

    @Override
    public void doBookService(OnBookRoomListener onBookRoomListener, BookRoom bookRoom) {
        this.onBookRoomListener = onBookRoomListener;
        // Insert
        HashMap<String, String> param = new HashMap<>();
        param.put("ACTION", "BOOKROOM.INSERT");
        param.put("USER", String.valueOf(bookRoom.getIdUser()));
        param.put("ROOM", String.valueOf(bookRoom.getIdRoom()));
        param.put("DATE_IN", bookRoom.getDateIn());
        param.put("DATE_OUT", bookRoom.getDateOut());
        param.put("NUM_DAYS", String.valueOf(bookRoom.getNumberDays()));
        param.put("NUM_PEOPLE", String.valueOf(bookRoom.getNumberPeople()));

        //Update
        HashMap<String, String> param_2 = new HashMap<>();
        param_2.put("ACTION", "ROOM.UPDATE");
        param_2.put("ID_ROOM", String.valueOf(bookRoom.getIdRoom()));

        BackgroundTask task = new BackgroundTask(param, param_2);
        task.execute(BuildConfig.SERVER_URL + "Controller");
        //task.execute("http://192.168.0.13:8090/Booking/Controller");
    }

        class BackgroundTask extends AsyncTask<String, Integer, Boolean> {

            private HashMap<String, String> parameters = null;
            private HashMap<String, String> parameters_2 = null;

            public BackgroundTask(HashMap<String, String> parameters, HashMap<String, String> parameters_2) {
                super();
                this.parameters = parameters;
                this.parameters_2 = parameters_2;
            }

            @Override
            protected Boolean doInBackground(String... params) {

                String url_select = params[0];
                String url_select_2 = params[0];
                try {
                    Post post = new Post();
                    JSONArray result = post.getServerDataPost(parameters, url_select);
                    JSONArray result_2 = post.getServerDataPost(parameters_2, url_select_2);
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
        }*/


}
