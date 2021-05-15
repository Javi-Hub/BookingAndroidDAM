package rodrigo.javier.booking.detailRoom.presenter;

import android.content.Context;

import rodrigo.javier.booking.beans.BookRoom;
import rodrigo.javier.booking.detailRoom.contract.DetailRoomContract;
import rodrigo.javier.booking.detailRoom.model.DetailRoomModel;
import rodrigo.javier.booking.detailRoom.view.DetailRoomActivity;

public class DetailRoomPresenter implements DetailRoomContract.Presenter {

    DetailRoomActivity view;
    DetailRoomModel model;

    public DetailRoomPresenter(DetailRoomActivity view) {
        this.view = view;
        this.model = new DetailRoomModel();
    }

    @Override
    public void doBook(Context context, BookRoom bookRoom) {
        model.doBookService(context, new DetailRoomContract.Model.OnBookRoomListener() {
            @Override
            public void onResolve(String message) {
                view.success(message);
            }

            @Override
            public void onReject(String message) {
                view.error(message);
            }
        }, bookRoom);
    }
}
