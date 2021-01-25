package rodrigo.javier.booking.filter.model;

import rodrigo.javier.booking.beans.Hotel;
import rodrigo.javier.booking.filter.contract.FilterHotelContract;
import rodrigo.javier.booking.filter.presenter.FilterHotelPresenter;

public class FilterHotelModel implements FilterHotelContract.Model {

    private FilterHotelPresenter presenter;

    public FilterHotelModel(FilterHotelPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getFilterHotelsCategory() {
        if (Hotel.getListFilterCategory() != null){
            presenter.setHotelsCategory(Hotel.getListFilterCategory());
        }
    }

    @Override
    public void getFilterHotelsRate() {
        if (Hotel.getListFilterRate() != null){
            presenter.setHotelsRate(Hotel.getListFilterRate());
        }
    }

    @Override
    public void getFilterHotelsCostDesc() {
        if (Hotel.getListFilterPrizeDesc() != null){
            presenter.setHotelsCostDesc(Hotel.getListFilterPrizeDesc());
        }
    }

    @Override
    public void getFilterHotelsCostAsc() {
        if (Hotel.getListFilterPrizeAsc() != null){
            presenter.setHotelsCostAsc(Hotel.getListFilterPrizeAsc());
        }
    }
}
