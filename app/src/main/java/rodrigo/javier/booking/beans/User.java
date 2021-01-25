package rodrigo.javier.booking.beans;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class User {

    private static ArrayList<User> list;
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String SURENAME = "surename";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";

    public int id;
    public String name;
    public String surename;
    public String email;
    public String password;

    public User() {
    }
    public User(int id, String name, String surename, String email, String password) {
        this.id = id;
        this.name = name;
        this.surename = surename;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSurename() {
        return surename;
    }

    public void setSurename(String surename) {
        this.surename = surename;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

     /*Convertir el archivo JSON obtenidos desde la BBDDD en array de objetos de la clase User
     y devolver la lista de objetos*/
    public static ArrayList<User> getArrayListFromJSon(JSONArray data){
        list = null;
        try {
            if (data != null && data.length() > 0){
                list = new ArrayList<User>();
            }
            for (int i = 0; i < data.length(); i++) {
                User user = null;
                JSONObject json_data = data.getJSONObject(i);
                user = new User();
                user.setId(json_data.getInt(ID));
                user.setName(json_data.getString(NAME));
                user.setSurename(json_data.getString(SURENAME));
                user.setEmail(json_data.getString(EMAIL));
                user.setPassword(json_data.getString(PASSWORD));

                list.add(user);
            }
        } catch (JSONException jse){
            jse.printStackTrace();
        }
        return list;
    }

    public static ArrayList<User> getUsers(){
        return list;
    }


}
