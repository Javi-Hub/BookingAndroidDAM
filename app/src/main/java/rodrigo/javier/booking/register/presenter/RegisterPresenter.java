package rodrigo.javier.booking.register.presenter;

import android.content.Context;

import rodrigo.javier.booking.beans.User;
import rodrigo.javier.booking.register.contract.RegisterContract;
import rodrigo.javier.booking.register.model.RegisterModel;
import rodrigo.javier.booking.register.view.RegisterActivity;

public class RegisterPresenter implements RegisterContract.Presenter {

    private RegisterActivity view;
    private RegisterModel model;

    public RegisterPresenter(RegisterActivity view) {
        this.view = view;
        this.model = new RegisterModel();
    }

    @Override
    public void doRegister(Context context, User user) {
        model.doRegisterService(context, new RegisterContract.Model.OnRegisterListener() {
            @Override
            public void onResolve(String message) {
                view.success(message);
            }

            @Override
            public void onReject(String message) {
                view.error(message);
            }
        }, user);
    }
}
