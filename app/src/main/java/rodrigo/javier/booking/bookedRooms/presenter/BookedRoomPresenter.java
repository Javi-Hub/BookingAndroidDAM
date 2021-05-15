package rodrigo.javier.booking.bookedRooms.presenter;

import android.content.Context;
import android.text.style.IconMarginSpan;

import java.util.ArrayList;

import rodrigo.javier.booking.beans.Hotel;
import rodrigo.javier.booking.bookedRooms.contract.BookedRoomContract;
import rodrigo.javier.booking.bookedRooms.model.BookedRoomModel;
import rodrigo.javier.booking.bookedRooms.view.BookedRoomActivity;

public class BookedRoomPresenter implements BookedRoomContract.Presenter {

    private BookedRoomContract.View view;
    private BookedRoomModel model;

    public BookedRoomPresenter(BookedRoomContract.View view) {
        this.view = view;
        this.model = new BookedRoomModel();
    }

    @Override
    public void getHotelsBooked(Context context) {
        model.getHotelsMoreBooked(context, new BookedRoomContract.Model.OnHotelsBookedListener() {
            @Override
            public void onResolve(ArrayList<Hotel> hotels) {
                view.successMoreBooked(hotels);
            }

            @Override
            public void onReject(String error) {
                view.error("Problemas al mostrar la lista");
            }
        });
    }

}
