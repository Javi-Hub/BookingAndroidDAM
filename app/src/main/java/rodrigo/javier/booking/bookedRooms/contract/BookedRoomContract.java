package rodrigo.javier.booking.bookedRooms.contract;

import android.content.Context;

import java.util.ArrayList;

import rodrigo.javier.booking.beans.Hotel;

public interface BookedRoomContract {
    interface View {
        void successMoreBooked(ArrayList<Hotel> hotels);
        void error (String message);
    }

    interface Presenter{
        void getHotelsBooked(Context context);
    }

    interface Model {
        void getHotelsMoreBooked(Context context, OnHotelsBookedListener onHotelsBookedListener);

        interface OnHotelsBookedListener{
            void onResolve(ArrayList<Hotel> hotels);
            void onReject(String error);
        }
    }
}
