package rodrigo.javier.booking.lstRoom.model;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rodrigo.javier.booking.beans.Hotel;
import rodrigo.javier.booking.beans.Room;
import rodrigo.javier.booking.lstRoom.contract.LstRoomContract;
import rodrigo.javier.booking.retrofit.ApiClient;

public class LstRoomModel implements LstRoomContract.Model {

    public static String TAG = LstRoomModel.class.getSimpleName();

    @Override
    public void getRoomsService(Context context, OnLstRoomsListener onLstRoomsListener, Hotel hotel) {
        ApiClient apiClient = new ApiClient(context);
        final Call<List<Room>> request = apiClient.getRoomsHotel(hotel.getName());

        request.enqueue(new Callback<List<Room>>() {
            @Override
            public void onResponse(Call<List<Room>> call, Response<List<Room>> response) {
                if (response != null && response.body() != null){
                    onLstRoomsListener.onResolve(new ArrayList<>(response.body()));
                    Log.d(TAG, "[getListRooms Name -> ]" + response.body().get(0).getHotelName());
                }
            }

            @Override
            public void onFailure(Call<List<Room>> call, Throwable t) {
                t.printStackTrace();
                onLstRoomsListener.onReject(t.getLocalizedMessage());
            }
        });
    }

}
