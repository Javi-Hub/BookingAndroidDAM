package rodrigo.javier.booking.detailHotel.model;

import java.util.ArrayList;

import rodrigo.javier.booking.beans.Hotel;
import rodrigo.javier.booking.detailHotel.contract.DetailHotelContract;
import rodrigo.javier.booking.detailHotel.presenter.DetailHotelPresenter;

public class DetailHotelModel implements DetailHotelContract.Model {

    private DetailHotelPresenter presenter;
    private ArrayList<Hotel> list;
    private Hotel hotelSelected;

    public DetailHotelModel(DetailHotelPresenter presenter) {
        this.presenter = presenter;
    }

    //Obtener el hotel desde el método estático de la clase Hotel
    @Override
    public void getHotelFromList(Hotel hotel) {
        list = Hotel.getList();
        hotelSelected = new Hotel();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals(hotel.getName())){
               hotelSelected = list.get(i);
               if (hotelSelected != null){
                   presenter.setHotel(hotelSelected);
               }
            }
        }
    }
}
