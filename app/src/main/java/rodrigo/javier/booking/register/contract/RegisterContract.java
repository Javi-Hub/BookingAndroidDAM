package rodrigo.javier.booking.register.contract;

import rodrigo.javier.booking.beans.User;

public interface RegisterContract {

    interface View {
        void success(String message);
        void error(String message);
    }

    interface Presenter{
        void doRegister(User user);
    }

    interface Model{
        void doRegisterService(OnRegisterListener onRegisterListener, User user);

        interface OnRegisterListener{
            void onResolve(String message);
            void onReject(String message);
        }
    }

}
