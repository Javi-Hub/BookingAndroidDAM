package rodrigo.javier.booking.filter.presenter;

import java.util.ArrayList;

import rodrigo.javier.booking.beans.Hotel;
import rodrigo.javier.booking.filter.contract.FilterHotelContract;
import rodrigo.javier.booking.filter.model.FilterHotelModel;
import rodrigo.javier.booking.filter.view.FilterHotelActivity;

public class FilterHotelPresenter implements FilterHotelContract.Presenter {

    private FilterHotelActivity view;
    private FilterHotelModel model;

    public FilterHotelPresenter(FilterHotelActivity view) {
        this.view = view;
        this.model = new FilterHotelModel(this);
    }

    @Override
    public void getHotelsCategory() {
        model.getFilterHotelsCategory();
    }

    @Override
    public void setHotelsCategory(ArrayList<Hotel> hotelsCategory) {
        view.successCategory(hotelsCategory);
    }

    @Override
    public void getHotelsRate() {
        model.getFilterHotelsRate();
    }

    @Override
    public void setHotelsRate(ArrayList<Hotel> hotelsRate) {
        view.successRate(hotelsRate);
    }

    @Override
    public void getHotelsCostDesc() {
        model.getFilterHotelsCostDesc();
    }

    @Override
    public void setHotelsCostDesc(ArrayList<Hotel> hotelsCostDesc) {
        view.successCostDesc(hotelsCostDesc);
    }

    @Override
    public void getHotelsCostAsc() {
        model.getFilterHotelsCostAsc();
    }

    @Override
    public void setHotelsCostAsc(ArrayList<Hotel> hotelsCostAsc) {
        view.successCostAsc(hotelsCostAsc);
    }
}
