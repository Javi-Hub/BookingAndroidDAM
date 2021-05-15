package rodrigo.javier.booking.login.presenter;

import android.content.Context;

import rodrigo.javier.booking.beans.User;
import rodrigo.javier.booking.login.contract.LoginContract;
import rodrigo.javier.booking.login.model.LoginModel;
import rodrigo.javier.booking.login.view.LoginActivity;

public class LoginPresenter implements LoginContract.Presenter {

    private LoginActivity view;
    private LoginModel model;

    public LoginPresenter(LoginActivity view) {
        this.view = view;
        this.model = new LoginModel();
    }

    @Override
    public void getUser(Context context, User user) {
        model.getUserService(context, new LoginContract.Model.OnLoginListener() {
            @Override
            public void onResolve(User user) {
                view.success(user);
            }

            @Override
            public void onReject(String error) {
                view.error(error);
            }
        }, user);
    }
}
