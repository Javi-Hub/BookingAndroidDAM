package rodrigo.javier.booking.fieldSearch.presenter;

import android.content.Context;

import java.util.ArrayList;

import rodrigo.javier.booking.beans.Hotel;
import rodrigo.javier.booking.fieldSearch.contract.FieldSearchContract;
import rodrigo.javier.booking.fieldSearch.model.FieldSearchModel;
import rodrigo.javier.booking.fieldSearch.view.FieldSearchActivity;

public class FieldSearchPresenter implements FieldSearchContract.Presenter {

    private FieldSearchActivity view;
    private FieldSearchModel model;

    public FieldSearchPresenter(FieldSearchActivity view) {
        this.view = view;
        this.model = new FieldSearchModel();
    }

    @Override
    public void getHotels(Context context, String city) {
        model.getHotelsService(context, new FieldSearchContract.Model.OnFieldSearchListener() {
            @Override
            public void onResolve(ArrayList<Hotel> hotels) {
                view.success(hotels);
            }

            @Override
            public void onReject(String error) {
                view.error("Problemas al mostrar la lista");
            }
        }, city);
    }
}
