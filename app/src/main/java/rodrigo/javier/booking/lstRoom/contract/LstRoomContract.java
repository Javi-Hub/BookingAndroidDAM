package rodrigo.javier.booking.lstRoom.contract;

import java.util.ArrayList;

import rodrigo.javier.booking.beans.Hotel;
import rodrigo.javier.booking.beans.Room;

public interface LstRoomContract {

    public interface View {
        void success(ArrayList<Room> lstRooms);
        void error (String error);
    }

    public interface Presenter {
        void getRooms(Hotel hotel);
    }

    public interface Model {
        void getRoomsService(OnLstRoomsListener onLstRoomsListener, Hotel hotel);

        interface OnLstRoomsListener{
            void onResolve(ArrayList<Room> lstRooms);
            void onReject(String error);
        }
    }
}
