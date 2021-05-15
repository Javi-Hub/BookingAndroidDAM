package rodrigo.javier.booking.register.model;

import android.content.Context;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rodrigo.javier.booking.beans.User;
import rodrigo.javier.booking.register.contract.RegisterContract;
import rodrigo.javier.booking.retrofit.ApiClient;

public class RegisterModel implements RegisterContract.Model {


    @Override
    public void doRegisterService(Context context, OnRegisterListener onRegisterListener, User user) {
        ApiClient apiClient = new ApiClient(context);
        final Call<User> request = apiClient.setUser(user.getName(), user.getSurename(), user.getEmail(), user.getPassword());

        request.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response != null && response.body() != null){
                    onRegisterListener.onResolve("Usuario registrado");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                t.printStackTrace();
                onRegisterListener.onReject("Usuario no registrado");
            }
        });
    }



    /*OnRegisterListener onRegisterListener;
    private ArrayList<User> list;

    @Override
    public void doRegisterService(OnRegisterListener onRegisterListener, User user) {
        this.onRegisterListener = onRegisterListener;
        HashMap<String, String> param = new HashMap<>();
        param.put("ACTION", "USER.INSERT");
        param.put("NAME", user.getName());
        param.put("SURENAME", user.getSurename());
        param.put("EMAIL", user.getEmail());
        param.put("PASSWORD", user.getPassword());
        BackgroundTask task = new BackgroundTask(param);
        task.execute(BuildConfig.SERVER_URL + "Controller");
        //task.execute("http://192.168.0.13:8090/Booking/Controller");
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
                list = User.getArrayListFromJSon(result);
            }catch (Exception e){
                Log.e("log_tag", "Error in http connection " + e.toString());
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean resp){
            try {
                if (resp){
                    onRegisterListener.onResolve("Usuario registrado correctamente");
                }
            } catch (Exception e){
                onRegisterListener.onReject("Error al registrar usuario");
            }
        }
    }*/

}
