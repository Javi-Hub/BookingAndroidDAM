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

    public static String TAG = LoginModel.class.getSimpleName();

    @Override
    public void getUserService(Context context, OnLoginListener onLoginListener, User user) {
        ApiClient apiClient = new ApiClient(context);
        final Call<List<User>> request = apiClient.getValidateUser(user.getEmail(), user.getPassword());

        request.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response != null && !response.body().isEmpty()){
                        onLoginListener.onResolve(response.body().get(0));
                    Log.d(TAG, "[getEmailUser -> ]" + user.getEmail());
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

}
