package rodrigo.javier.booking.detailRoom.model;

import android.content.Context;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rodrigo.javier.booking.beans.BookRoom;
import rodrigo.javier.booking.detailRoom.contract.DetailRoomContract;
import rodrigo.javier.booking.retrofit.ApiClient;

public class DetailRoomModel implements DetailRoomContract.Model {

    public static String TAG = DetailRoomModel.class.getSimpleName();

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
                    Log.d(TAG, "[get bookRoom] Cost-> " + bookRoom.getCost());
                }
            }

            @Override
            public void onFailure(Call<BookRoom> call, Throwable t) {
                onBookRoomListener.onReject("Reserva no realizada correctamente");
            }
        });
    }

}
