package rodrigo.javier.booking.login.model;

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
import rodrigo.javier.booking.beans.User;
import rodrigo.javier.booking.login.contract.LoginContract;
import rodrigo.javier.booking.retrofit.ApiClient;
import rodrigo.javier.booking.utils.Post;

public class LoginModel implements LoginContract.Model {

    @Override
    public void getUserService(Context context, OnLoginListener onLoginListener, User user) {
        ApiClient apiClient = new ApiClient(context);
        final Call<List<User>> request = apiClient.getValidateUser(user.getEmail(), user.getPassword());

        request.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response != null && !response.body().isEmpty()){
                        onLoginListener.onResolve(response.body().get(0));
                    System.out.println(response.body().get(0).getEmail());
                } else {
                    onLoginListener.onReject("Usuario no valido");
                    System.out.println("ERROR");
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                t.printStackTrace();
                onLoginListener.onReject("Usuario no valido");
                System.out.println("ERROR");
            }
        });
    }



    /*private OnLoginListener onLoginListener;
    private ArrayList<User> lstUser;

    @Override
    public void getUserService(OnLoginListener onLoginListener, User user) {
        this.onLoginListener = onLoginListener;
        HashMap<String, String> param = new HashMap<>();
        param.put("ACTION", "USER.VALIDATE");
        param.put("EMAIL", user.getEmail());
        param.put("PASSWORD", user.getPassword());
        BackgroundTask task = new BackgroundTask(param);
        task.execute(BuildConfig.SERVER_URL + "Controller");
        //task.execute("http://192.168.0.13:8090/Booking/Controller");
     }

    class BackgroundTask extends AsyncTask<String, Integer, Boolean> {

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
                onLoginListener.onReject("Usuario no valido");
            }
        }
    }*/
}
