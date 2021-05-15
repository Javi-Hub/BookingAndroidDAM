package rodrigo.javier.booking.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rodrigo.javier.booking.beans.BookRoom;
import rodrigo.javier.booking.beans.Hotel;
import rodrigo.javier.booking.beans.Room;
import rodrigo.javier.booking.beans.User;

public interface BookingApiClient {

    @GET("Controller?ACTION=HOTEL.FIND_ALL")
    Call<List<Hotel>> getAllHotels();

    @GET("Controller?ACTION=HOTEL.TOP_TEN")
    Call<List<Hotel>> getTenBookedHotels();

    @GET("Controller?ACTION=HOTEL.FIND_FILTER")
    Call<List<Hotel>> getHotelsByCity(@Query("CITY") String city);

    @GET("Controller?ACTION=ROOM.FIND_FILTER")
    Call<List<Room>> getRoomsHotel(@Query("NAME_HOTEL") String hotel);

    @GET("Controller?ACTION=USER.VALIDATE")
    Call<List<User>> getValidateUser(@Query("EMAIL") String email, @Query("PASSWORD") String password);

    @POST("Controller?ACTION=USER.INSERT")
    Call<User> setUser(@Query("NAME") String name,
                       @Query("SURENAME") String surename,
                       @Query("EMAIL") String email,
                       @Query("PASSWORD") String password);

    @POST("Controller?ACTION=BOOKROOM.INSERT")
    Call<BookRoom> setBookRoom(@Query("USER") int idUser,
                               @Query("ROOM") int idRoom,
                               @Query("DATE_IN") String dateIn,
                               @Query("DATE_OUT") String dateOut,
                               @Query("NUM_DAYS") int numDays,
                               @Query("NUM_PEOPLE") int numPeople,
                               @Query("COST") double prize);
}
