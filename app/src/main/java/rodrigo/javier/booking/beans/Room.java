package rodrigo.javier.booking.beans;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Room {

    private static ArrayList<Room> list;
    private static final String ID = "id";
    private static final String CAPACITY = "capacity";
    private static final String ID_HOTEL = "idHotel";
    private static final String COST = "cost";
    private static final String AVAILABLE = "available";
    private static final String HOTEL_NAME  = "hotelName";
    private static final String URL_IMAGE = "urlImage";
    private static final String CITY = "city";

    public int id;
    public int capacity;
    public int idHotel;
    public double cost;
    public String available;
    public String hotelName;
    public String urlImage;
    public String city;

    public Room() {
    }
    public Room(int id, int capacity, int idHotel, double cost, String available, String hotelName,String urlImage, String city) {
        this.id = id;
        this.capacity = capacity;
        this.idHotel = idHotel;
        this.cost = cost;
        this.available = available;
        this.hotelName = hotelName;
        this.urlImage = urlImage;
        this.city = city;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    public int getIdHotel() {
        return idHotel;
    }
    public void setIdHotel(int idHotel) {
        this.idHotel = idHotel;
    }
    public double getCost() {
        return cost;
    }
    public void setCost(double cost) {
        this.cost = cost;
    }
    public String getAvailable() {
        return available;
    }
    public void setAvailable(String available) {
        this.available = available;
    }
    public String getHotelName() {
        return hotelName;
    }
    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
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

    /*Convertir el archivo JSON obtenidos desde la BBDDD en array de objetos de la clase Room
             y devolver la lista de objetos*/
    public static ArrayList<Room> getArrayListFromJSon (JSONArray data){
        list = null;
        try {
            if (data != null && data.length() > 0 ){
                list = new ArrayList<Room>();
            }
            for (int i = 0; i < data.length(); i++) {
                Room room = null;
                JSONObject json_data = data.getJSONObject(i);
                room = new Room();
                room.setId(json_data.getInt(ID));
                room.setCapacity(json_data.getInt(CAPACITY));
                room.setIdHotel(json_data.getInt(ID_HOTEL));
                room.setCost(json_data.getDouble(COST));
                room.setAvailable(json_data.getString(AVAILABLE));
                room.setHotelName(json_data.getString(HOTEL_NAME));
                room.setUrlImage(json_data.getString(URL_IMAGE));
                room.setCity(json_data.getString(CITY));

                list.add(room);
            }
        } catch (JSONException jse){
            jse.printStackTrace();
        }
        return list;
    }

}
