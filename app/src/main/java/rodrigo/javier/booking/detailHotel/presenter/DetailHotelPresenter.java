package rodrigo.javier.booking.detailHotel.presenter;

import rodrigo.javier.booking.beans.Hotel;
import rodrigo.javier.booking.detailHotel.contract.DetailHotelContract;
import rodrigo.javier.booking.detailHotel.model.DetailHotelModel;
import rodrigo.javier.booking.detailHotel.view.DetailHotelActivity;

public class DetailHotelPresenter implements DetailHotelContract.Presenter {

    private DetailHotelModel model;
    private DetailHotelActivity view;

    public DetailHotelPresenter(DetailHotelActivity view) {
        this.view = view;
        this.model = new DetailHotelModel(this);
    }

    //Comunicar con la clase Model para obtener el hotel seleccionado
    @Override
    public void getHotel(Hotel hotel) {
        if (view != null){
            model.getHotelFromList(hotel);
        }
    }

    //Recoger el hotel para mostrarlo en el view
    @Override
    public void setHotel(Hotel hotel) {
        if (view != null){
            view.selectedHotel(hotel);
        }
    }
}
