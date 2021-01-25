package rodrigo.javier.booking.detailHotel.contract;

import rodrigo.javier.booking.beans.Hotel;

public interface DetailHotelContract {
    interface View {
        void selectedHotel(Hotel hotel);
    }

    interface Presenter {
        void getHotel(Hotel hotel);
        void setHotel(Hotel hotel);
    }

    interface Model {
        void getHotelFromList(Hotel hotel);
    }
}
