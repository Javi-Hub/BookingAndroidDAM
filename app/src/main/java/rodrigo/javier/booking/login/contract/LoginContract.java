package rodrigo.javier.booking.login.contract;

import rodrigo.javier.booking.beans.User;

public interface LoginContract {
      interface View {
          void success(User user);
          void error(String error);
      }

      interface Presenter{
          void getUser(User user);
      }

      interface Model{
          void getUserService(OnLoginListener onLoginListener, User user);

          interface OnLoginListener{
              void onResolve(User user);
              void onReject(String error);
          }
      }
}
