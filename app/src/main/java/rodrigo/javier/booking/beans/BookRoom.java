package rodrigo.javier.booking.beans;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.util.ArrayList;

public class BookRoom {

    private static ArrayList<BookRoom> list;
    private static final String ID = "id";
    private static final String NUMBER_DAYS = "numberDays";
    private static final String NUMBER_PEOPLE = "numberPeople";
    private static final String ID_USER = "idUser";
    private static final String ID_ROOM = "idRoom";
    private static final String DATE_IN = "dateIn";
    private static final String DATE_OUT = "dateOut";
    private static final String COST = "cost";


    private int id;
    private int numberDays;
    private int numberPeople;
    private int idUser;
    private int idRoom;
    private String dateIn, dateOut;
    private double cost;

    public BookRoom() {
    }

    public BookRoom(int id, int numberDays, int numberPeople, int idUser, int idRoom, String dateIn, String dateOut, double cost) {
        this.id = id;
        this.numberDays = numberDays;
        this.numberPeople = numberPeople;
        this.idUser = idUser;
        this.idRoom = idRoom;
        this.dateIn = dateIn;
        this.dateOut = dateOut;
        this.cost = cost;
    }



    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getNumberDays() {
        return numberDays;
    }
    public void setNumberDays(int numberDays) {
        this.numberDays = numberDays;
    }
    public int getNumberPeople() {
        return numberPeople;
    }
    public void setNumberPeople(int numberPeople) {
        this.numberPeople = numberPeople;
    }
    public int getIdUser() {
        return idUser;
    }
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    public int getIdRoom() {
        return idRoom;
    }
    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }
    public String getDateIn() {
        return dateIn;
    }
    public void setDateIn(String dateIn) {
        this.dateIn = dateIn;
    }
    public String getDateOut() {
        return dateOut;
    }
    public void setDateOut(String dateOut) {
        this.dateOut = dateOut;
    }
    public double getCost() {
        return cost;
    }
    public void setCost(double cost) {
        this.cost = cost;
    }

    /*Convertir el archivo JSON obtenidos desde la BBDDD en array de objetos de la clase BookRoom
             y devolver la lista de objetos*/
    public static ArrayList<BookRoom> getArrayListFromJSon (JSONArray data){
        list = null;
        try {
            if (data != null && data.length() > 0 ){
                list = new ArrayList<BookRoom>();
            }
            for (int i = 0; i < data.length(); i++) {
                BookRoom bookRoom = null;
                JSONObject json_data = data.getJSONObject(i);
                bookRoom = new BookRoom();
                bookRoom.setId(json_data.getInt(ID));
                bookRoom.setNumberDays(json_data.getInt(NUMBER_DAYS));
                bookRoom.setNumberPeople(json_data.getInt(NUMBER_PEOPLE));
                bookRoom.setIdUser(json_data.getInt(ID_USER));
                bookRoom.setIdRoom(json_data.getInt(ID_ROOM));
                bookRoom.setDateIn(json_data.getString(DATE_IN));
                bookRoom.setDateOut(json_data.getString(DATE_OUT));
                bookRoom.setCost(json_data.getDouble(COST));

                list.add(bookRoom);
            }
        } catch (JSONException jse){
            jse.printStackTrace();
        }
        return list;
    }
}
