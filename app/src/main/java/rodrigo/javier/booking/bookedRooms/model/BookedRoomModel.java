package rodrigo.javier.booking.bookedRooms.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rodrigo.javier.booking.beans.Hotel;
import rodrigo.javier.booking.bookedRooms.contract.BookedRoomContract;
import rodrigo.javier.booking.retrofit.ApiClient;

public class BookedRoomModel implements BookedRoomContract.Model {


    @Override
    public void getHotelsMoreBooked(Context context, OnHotelsBookedListener onHotelsBookedListener) {
        ApiClient apiClient = new ApiClient(context);
        final Call<List<Hotel>> request = apiClient.getTenBookedHotels();

        request.enqueue(new Callback<List<Hotel>>() {
            @Override
            public void onResponse(Call<List<Hotel>> call, Response<List<Hotel>> response) {
                if (response != null && response.body() != null){
                    onHotelsBookedListener.onResolve(new ArrayList<>(response.body()));
                }
            }

            @Override
            public void onFailure(Call<List<Hotel>> call, Throwable t) {
                t.printStackTrace();
                onHotelsBookedListener.onReject(t.getLocalizedMessage());
            }
        });

    }
}
