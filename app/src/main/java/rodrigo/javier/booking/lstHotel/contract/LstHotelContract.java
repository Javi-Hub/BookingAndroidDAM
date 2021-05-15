package rodrigo.javier.booking.lstHotel.contract;

import android.content.Context;

import java.util.ArrayList;

import rodrigo.javier.booking.beans.Hotel;

public interface LstHotelContract {


     interface View {
        void success(ArrayList<Hotel> lstHotel);
        void error(String message);
    }

     interface Presenter {
        void getHotels(Context context);
    }

     interface Model {
        void getHotelsService(Context context, OnLstHotelsListener onLstHotelsListener);

        interface OnLstHotelsListener {
            void onResolve(ArrayList<Hotel> lstHotels);
            void onReject(String error);
        }
    }
}