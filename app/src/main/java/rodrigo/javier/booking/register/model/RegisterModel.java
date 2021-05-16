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

}
