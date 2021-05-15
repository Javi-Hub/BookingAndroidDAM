package rodrigo.javier.booking.beans;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Hotel implements Serializable {

    private static ArrayList<Hotel> list;
    private static final String ID = "id";
    private static final String ID_LOCATION = "idLocation";
    private static final String BOOKED_ROOMS = "bookedRooms";
    private static final String CATEGORY = "category";
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String URL_IMAGE = "urlImage";
    private static final String CITY = "city";
    private static final String RATE = "rate";
    private static final String AVERAGE_PRIZE = "averagePrize";

    public int id;
    public int idLocation;
    public int bookedRooms;
    public int category;
    public String name;
    public String description;
    public String urlImage;
    public String city;
    public Double rate;
    public Double averagePrize;

    public Hotel() {
    }
    public Hotel(int id, int idLocation, int bookedRooms, int category, String name, String description, String urlImage, String city, Double rate, Double averagePrize) {
        this.id = id;
        this.idLocation = idLocation;
        this.bookedRooms = bookedRooms;
        this.name = name;
        this.description = description;
        this.urlImage = urlImage;
        this.city = city;
        this.rate = rate;
        this.averagePrize = averagePrize;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getIdLocation() {
        return idLocation;
    }
    public void setIdLocation(int idLocation) {
        this.idLocation = idLocation;
    }
    public int getBookedRooms() {
        return bookedRooms;
    }
    public void setBookedRooms(int bookedRooms) {
        this.bookedRooms = bookedRooms;
    }
    public int getCategory(){
        return category;
    }
    public void setCategory(int category){
        this.category = category;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getUrlImage() {
        return urlImage;
    }
    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public Double getRate() {
        return rate;
    }
    public void setRate(Double rate) {
        this.rate = rate;
    }
    public Double getAveragePrize() {
        return averagePrize;
    }
    public void setAveragePrize(Double averagePrize) {
        this.averagePrize = averagePrize;
    }

    /*Convertir el archivo JSON obtenidos desde la BBDDD en array de objetos de la clase Hotel
     y devolver la lista de objetos*/
    public static ArrayList<Hotel> getArrayListFromJSon(JSONArray data){
        list = null;
        try {
            if (data != null && data.length() > 0){
                list = new ArrayList<Hotel>();
            }
            for (int i = 0; i < data.length(); i++) {
                Hotel hotel = null;
                JSONObject json_data = data.getJSONObject(i);
                hotel = new Hotel();
                hotel.setId(json_data.getInt(ID));
                hotel.setIdLocation(json_data.getInt(ID_LOCATION));
                hotel.setBookedRooms(json_data.getInt(BOOKED_ROOMS));
                hotel.setCategory(json_data.getInt(CATEGORY));
                hotel.setName(json_data.getString(NAME));
                hotel.setDescription(json_data.getString(DESCRIPTION));
                hotel.setUrlImage(json_data.getString(URL_IMAGE));
                hotel.setCity(json_data.getString(CITY));
                hotel.setRate(json_data.getDouble(RATE));
                hotel.setAveragePrize(json_data.getDouble(AVERAGE_PRIZE));

                list.add(hotel);
            }
        } catch (JSONException jse) {
            jse.printStackTrace();
        }
         return list;
    }

    //Método que devuelve la lista completa de hoteles sin filtrar
    public static ArrayList<Hotel> getList() {
        return list;
    }

    /*//Método estatico que devuelve la lista de hoteles ordenados por categoría de mayor a menor
    public static ArrayList<Hotel> getListFilterCategory() {
        Collections.sort(list, new Comparator<Hotel>() {
            @Override
            public int compare(Hotel hotel_1, Hotel hotel_2) {
                return new Integer(hotel_2.getCategory()).compareTo(new Integer(hotel_1.getCategory()));
            }
        });
        return list;
    }

    //Método estatico que devuelve la lista de hoteles ordenados por valoración de mayor a menor
    public static ArrayList<Hotel> getListFilterRate() {
        Collections.sort(list, new Comparator<Hotel>() {
            @Override
            public int compare(Hotel hotel_1, Hotel hotel_2) {
                return new Double(hotel_2.getRate()).compareTo(new Double(hotel_1.getRate()));
            }
        });
        return list;
    }

    //Método estatico que devuelve la lista de hoteles ordenados por precio de más caro a más barato
    public static ArrayList<Hotel> getListFilterPrizeDesc() {
        Collections.sort(list, new Comparator<Hotel>() {
            @Override
            public int compare(Hotel hotel_1, Hotel hotel_2) {
                return new Double(hotel_2.getAveragePrize()).compareTo(new Double(hotel_1.getAveragePrize()));
            }
        });
        return list;
    }

    //Método estatico que devuelve la lista de hoteles ordenados por precio de más barato a más caro
    public static ArrayList<Hotel> getListFilterPrizeAsc() {
        Collections.sort(list, new Comparator<Hotel>() {
            @Override
            public int compare(Hotel hotel_1, Hotel hotel_2) {
                return new Double(hotel_1.getAveragePrize()).compareTo(new Double(hotel_2.getAveragePrize()));
            }
        });
        return list;
    }*/

    //Método estatico que devuelve la lista de hoteles ordenados con más reservas a menos
    public static ArrayList<Hotel> getListMoreBooked() {
        Collections.sort(list, new Comparator<Hotel>() {
            @Override
            public int compare(Hotel hotel_1, Hotel hotel_2) {
                return new Integer(hotel_2.getBookedRooms()).compareTo(new Integer(hotel_1.getBookedRooms()));
            }
        });
        return list;
    }

    public static ArrayList<Hotel> getListFilterCategory(ArrayList<Hotel> list) {
        Collections.sort(list, new Comparator<Hotel>() {
            @Override
            public int compare(Hotel hotel_1, Hotel hotel_2) {
                return new Integer(hotel_2.getCategory()).compareTo(new Integer(hotel_1.getCategory()));
            }
        });
        return list;
    }
}
