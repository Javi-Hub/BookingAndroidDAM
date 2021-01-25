package rodrigo.javier.booking.filter.contract;

import java.util.ArrayList;

import rodrigo.javier.booking.beans.Hotel;

public interface FilterHotelContract {
    interface View {
        void successCategory(ArrayList<Hotel> hotels);
        void successRate(ArrayList<Hotel> hotels);
        void successCostDesc(ArrayList<Hotel> hotels);
        void successCostAsc(ArrayList<Hotel> hotels);
        void error(String message);
    }

    interface Presenter{
        void getHotelsCategory();
        void setHotelsCategory(ArrayList<Hotel> hotelsCategory);
        void getHotelsRate();
        void setHotelsRate(ArrayList<Hotel> hotelsRate);
        void getHotelsCostDesc();
        void setHotelsCostDesc(ArrayList<Hotel> hotelsCostDesc);
        void getHotelsCostAsc();
        void setHotelsCostAsc(ArrayList<Hotel> hotelsCostAsc);
    }

    interface Model{
        void getFilterHotelsCategory();
        void getFilterHotelsRate();
        void getFilterHotelsCostDesc();
        void getFilterHotelsCostAsc();
    }
}
