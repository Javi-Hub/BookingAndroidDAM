package rodrigo.javier.booking.lstHotel.contract;

import java.util.ArrayList;

import rodrigo.javier.booking.beans.Hotel;

public interface LstHotelContract {


     interface View {
        void success(ArrayList<Hotel> lstHotel);
        void error(String message);
    }

     interface Presenter {
        void getHotels();
    }

     interface Model {
        void getHotelsService(OnLstHotelsListener onLstHotelsListener);

        interface OnLstHotelsListener {
            void onResolve(ArrayList<Hotel> lstHotels);
            void onReject(String error);
        }
    }
}