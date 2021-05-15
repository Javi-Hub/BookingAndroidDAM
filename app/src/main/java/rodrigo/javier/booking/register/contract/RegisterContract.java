package rodrigo.javier.booking.register.contract;

import android.content.Context;

import rodrigo.javier.booking.beans.User;

public interface RegisterContract {

    interface View {
        void success(String message);
        void error(String message);
    }

    interface Presenter{
        void doRegister(Context context, User user);
    }

    interface Model{
        void doRegisterService(Context context, OnRegisterListener onRegisterListener, User user);

        interface OnRegisterListener{
            void onResolve(String message);
            void onReject(String message);
        }
    }

}
