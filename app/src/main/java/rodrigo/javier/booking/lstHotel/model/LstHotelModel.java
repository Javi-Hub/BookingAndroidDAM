package rodrigo.javier.booking.lstHotel.model;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rodrigo.javier.booking.BuildConfig;
import rodrigo.javier.booking.beans.Hotel;
import rodrigo.javier.booking.lstHotel.contract.LstHotelContract;
import rodrigo.javier.booking.retrofit.ApiClient;
import rodrigo.javier.booking.utils.Post;

public class LstHotelModel implements LstHotelContract.Model {


    @Override
    public void getHotelsService(Context context, OnLstHotelsListener onLstHotelsListener) {
        ApiClient apiClient = new ApiClient(context);
        final Call<List<Hotel>> request = apiClient.getAllHotels();

        request.enqueue(new Callback<List<Hotel>>() {
            @Override
            public void onResponse(Call<List<Hotel>> call, Response<List<Hotel>> response) {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (response != null && response.body() != null){
                    onLstHotelsListener.onResolve(new ArrayList<>(response.body()));
                }
            }

            @Override
            public void onFailure(Call<List<Hotel>> call, Throwable t) {
                t.printStackTrace();
                onLstHotelsListener.onReject("Error al cargar los datos");
            }
        });
    }

}
