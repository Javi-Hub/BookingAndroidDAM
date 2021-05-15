package rodrigo.javier.booking.retrofit;

import android.content.Context;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rodrigo.javier.booking.BuildConfig;
import rodrigo.javier.booking.beans.BookRoom;
import rodrigo.javier.booking.beans.Hotel;
import rodrigo.javier.booking.beans.Room;
import rodrigo.javier.booking.beans.User;

public class ApiClient {

    private Retrofit retrofit;
    private Context context;
    private BookingApiClient service;

    public ApiClient(Context context) {
        this.context = context;

        retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Call<List<Hotel>> getAllHotels(){
        service = retrofit.create(BookingApiClient.class);
        return service.getAllHotels();
    }

    public Call<List<Hotel>> getTenBookedHotels(){
        service = retrofit.create(BookingApiClient.class);
        return service.getTenBookedHotels();
    }

    public Call<List<Hotel>> getHotelsByCity(String city){
        service = retrofit.create(BookingApiClient.class);
        return service.getHotelsByCity(city);
    }

    public Call<List<Room>> getRoomsHotel(String hotel){
        service = retrofit.create(BookingApiClient.class);
        return service.getRoomsHotel(hotel);
    }

    public Call<List<User>> getValidateUser(String email, String password){
        service = retrofit.create(BookingApiClient.class);
        return service.getValidateUser(email, password);
    }

    public Call<User> setUser(String name, String surename, String email, String password){
        service = retrofit.create(BookingApiClient.class);
        return service.setUser(name, surename, email, password);
    }

    public Call<BookRoom> setBookRoom(int idUser, int idRoom, String dateIn, String dateOut, int numDays, int numPeople, double prize){
        service = retrofit.create(BookingApiClient.class);
        return service.setBookRoom(idUser, idRoom, dateIn, dateOut, numDays, numPeople, prize);
    }

}
