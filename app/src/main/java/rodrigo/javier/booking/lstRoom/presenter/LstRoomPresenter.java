package rodrigo.javier.booking.lstRoom.presenter;

import android.content.Context;

import java.util.ArrayList;

import rodrigo.javier.booking.beans.Hotel;
import rodrigo.javier.booking.beans.Room;
import rodrigo.javier.booking.lstRoom.contract.LstRoomContract;
import rodrigo.javier.booking.lstRoom.model.LstRoomModel;

public class LstRoomPresenter implements LstRoomContract.Presenter {

    private LstRoomContract.View view;
    private LstRoomModel model;

    public LstRoomPresenter(LstRoomContract.View view) {
        this.view = view;
        this.model = new LstRoomModel();
    }

    @Override
    public void getRooms(Context context, Hotel hotel) {
        model.getRoomsService(context, new LstRoomContract.Model.OnLstRoomsListener() {
            @Override
            public void onResolve(ArrayList<Room> lstRooms) {
                view.success(lstRooms);
            }

            @Override
            public void onReject(String error) {
                view.error("Problemas al mostrar la lista");
            }
        }, hotel);
    }
}
