package rodrigo.javier.booking.login.model;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

import rodrigo.javier.booking.beans.User;
import rodrigo.javier.booking.login.contract.LoginContract;
import rodrigo.javier.booking.utils.Post;

public class LoginModel implements LoginContract.Model {

    private OnLoginListener onLoginListener;
    private ArrayList<User> lstUser;

    @Override
    public void getUserService(OnLoginListener onLoginListener, User user) {
        this.onLoginListener = onLoginListener;
        HashMap<String, String> param = new HashMap<>();
        param.put("ACTION", "USER.VALIDATE");
        param.put("EMAIL", user.getEmail());
        param.put("PASSWORD", user.getPassword());
        BackgroundTask task = new BackgroundTask(param);
        task.execute("http://192.168.0.12:8090/Booking/Controller");
     }

    class BackgroundTask extends AsyncTask<String, Integer, Boolean>{

        private HashMap<String, String> parameters = null;

        public BackgroundTask(HashMap<String, String> parameters) {
            super();
            this.parameters = parameters;
        }

        @Override
        protected Boolean doInBackground(String... params) {

            String url_select = params[0];
            try {
                Post post = new Post();
                JSONArray result = post.getServerDataPost(parameters, url_select);
                lstUser = User.getArrayListFromJSon(result);
            } catch (Exception e) {
                Log.e("log_tag", "Error in http connection " + e.toString());
            }

            return true;
        }

        @Override
        protected void onPostExecute(Boolean resp) {
            try {
                if(resp){
                    onLoginListener.onResolve(lstUser.get(0));
                }
            }catch (Exception e) {
                onLoginListener.onReject("Fallo Usuario");
            }
        }
    }
}
