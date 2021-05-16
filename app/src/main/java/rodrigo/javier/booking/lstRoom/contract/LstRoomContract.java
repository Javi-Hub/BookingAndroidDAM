package rodrigo.javier.booking.lstRoom.contract;

import android.content.Context;

import java.util.ArrayList;

import rodrigo.javier.booking.beans.Hotel;
import rodrigo.javier.booking.beans.Room;

public interface LstRoomContract {

    interface View {
        void success(ArrayList<Room> lstRooms);
        void error (String error);
    }

    interface Presenter {
        void getRooms(Context context, Hotel hotel);
    }

    interface Model {
        void getRoomsService(Context context, OnLstRoomsListener onLstRoomsListener, Hotel hotel);

        interface OnLstRoomsListener{
            void onResolve(ArrayList<Room> lstRooms);
            void onReject(String error);
        }
    }
}
