package rodrigo.javier.booking.fieldSearch.contract;

import android.content.Context;

import java.util.ArrayList;

import rodrigo.javier.booking.beans.Hotel;
import rodrigo.javier.booking.lstHotel.contract.LstHotelContract;

public interface FieldSearchContract {

    interface View {
        void success(ArrayList<Hotel> hotels);
        void error(String message);
    }

    interface Presenter {
        void getHotels(Context context, String city);
    }

    interface Model {
        void getHotelsService(Context context, OnFieldSearchListener onFieldSearchListener, String city);

        interface OnFieldSearchListener {
            void onResolve(ArrayList<Hotel> lstHotels);
            void onReject(String error);
        }
    }
}
