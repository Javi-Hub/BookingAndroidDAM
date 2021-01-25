package rodrigo.javier.booking.detailRoom.contract;

import rodrigo.javier.booking.beans.BookRoom;

public interface DetailRoomContract {
    interface View{
        void success(String message);
        void error(String message);
    }

    interface Presenter{
        void doBook(BookRoom bookRoom);
    }

    interface Model{
        void doBookService(OnBookRoomListener onBookRoomListener, BookRoom bookRoom);

        interface OnBookRoomListener{
            void onResolve(String message);
            void onReject(String message);
        }
    }
}
