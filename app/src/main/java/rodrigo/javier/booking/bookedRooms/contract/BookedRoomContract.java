package rodrigo.javier.booking.bookedRooms.contract;

import java.util.ArrayList;

import rodrigo.javier.booking.beans.Hotel;

public interface BookedRoomContract {
    interface View {
        void successMoreBooked(ArrayList<Hotel> hotels);
        void error (String message);
    }

    interface Presenter{
        void getHotelsBooked();
    }

    interface Model {
        void getHotelsMoreBooked(OnHotelsBookedListener onHotelsBookedListener);

        interface OnHotelsBookedListener{
            void onResolve(ArrayList<Hotel> hotels);
            void onReject(String error);
        }
    }
}
