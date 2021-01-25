package rodrigo.javier.booking.beans;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Location {

    private static ArrayList<Location> list;
    private static final String ID = "id";
    private static final String CITY = "city";

    public int id;
    public String city;

    public Location() {
    }

    public Location(int id, String city) {
        this.id = id;
        this.city = city;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    /*Convertir el archivo JSON obtenidos desde la BBDDD en array de objetos de la clase Location
     y devolver la lista de objetos*/
    public static ArrayList<Location> getArrayListFromJSon(JSONArray data){
        list = null;
        try {
            if (data != null  && data.length() > 0){
                list = new ArrayList<Location>();
            }
            for (int i = 0; i < data.length(); i++) {
                Location location = null;
                JSONObject json_data = data.getJSONObject(i);
                location = new Location();
                location.setId(json_data.getInt(ID));
                location.setCity(json_data.getString(CITY));

                list.add(location);
            }
        } catch (JSONException jse){
            jse.printStackTrace();
        }
        return list;
    }
}
