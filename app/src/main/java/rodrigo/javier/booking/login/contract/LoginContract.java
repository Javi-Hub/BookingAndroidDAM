package rodrigo.javier.booking.login.contract;

import android.content.Context;

import rodrigo.javier.booking.beans.User;

public interface LoginContract {
      interface View {
          void success(User user);
          void error(String error);
      }

      interface Presenter{
          void getUser(Context context, User user);
      }

      interface Model{
          void getUserService(Context context, OnLoginListener onLoginListener, User user);

          interface OnLoginListener{
              void onResolve(User user);
              void onReject(String error);
          }
      }
}
