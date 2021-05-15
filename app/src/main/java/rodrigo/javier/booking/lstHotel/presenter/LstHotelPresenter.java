package rodrigo.javier.booking.lstHotel.presenter;

import android.content.Context;

import java.net.ConnectException;
import java.util.ArrayList;

import rodrigo.javier.booking.beans.Hotel;
import rodrigo.javier.booking.lstHotel.contract.LstHotelContract;
import rodrigo.javier.booking.lstHotel.model.LstHotelModel;

public class LstHotelPresenter implements LstHotelContract.Presenter {

    private LstHotelContract.View view;
    private LstHotelModel model;

    public LstHotelPresenter(LstHotelContract.View view) {
        this.view = view;
        this.model = new LstHotelModel();
    }

    @Override
    public void getHotels(Context context) {
        model.getHotelsService(context, new LstHotelContract.Model.OnLstHotelsListener() {
            //Caso satisfactorio
            @Override
            public void onResolve(ArrayList<Hotel> hotels) {
                view.success(hotels);
            }

            //Caso de no obtener el listado
            @Override
            public void onReject(String error) {
                view.error("Problemas al mostrar la lista");
            }
        });
    }
}
