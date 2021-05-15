package rodrigo.javier.booking.lstRoom.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import rodrigo.javier.booking.BuildConfig;
import rodrigo.javier.booking.R;
import rodrigo.javier.booking.beans.Room;
import rodrigo.javier.booking.login.view.LoginActivity;

public class LstRoomAdapter extends RecyclerView.Adapter<LstRoomAdapter.RoomViewHolder> {

    private ArrayList<Room> lstRooms;
    private String dateIn, dateOut, numPeople;

    public LstRoomAdapter(ArrayList<Room> lstRooms, String dateIn, String dateOut, String numPeople) {
        this.lstRooms = lstRooms;
        this.dateIn = dateIn;
        this.dateOut = dateOut;
        this.numPeople = numPeople;
    }

    public static class RoomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        //Campos respectivos de un item
        public Context context;
        public CardView card_list_room;
        public ImageView image;
        public TextView nameHotel;
        public TextView available;
        public TextView capacity;
        public TextView number;
        public TextView prize;
        public TextView city;
        private String dateIn, dateOut, numPeople;

        public RoomViewHolder(View view){
            super(view);
            context = view.getContext();
            card_list_room = view.findViewById(R.id.card_room_hotel);
            image = view.findViewById(R.id.imgPhotoRoom);
            nameHotel = view.findViewById(R.id.txtHotelRoom);
            available = view.findViewById(R.id.txtAvailableRoom);
            capacity = view.findViewById(R.id.txtCapacityRoom);
            number = view.findViewById(R.id.txtNumberRoom);
            prize = view.findViewById(R.id.txtPrizeRoom);
            city = view.findViewById(R.id.txtCityRoom);
        }

        void setOnClickListeners() {
            card_list_room.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, LoginActivity.class);
            intent.putExtra("id_room", number.getText());
            intent.putExtra("nameHotel", nameHotel.getText());
            intent.putExtra("city", city.getText());
            intent.putExtra("capacity", capacity.getText());
            intent.putExtra("prize", prize.getText());
            intent.putExtra("dateIn", dateIn);
            intent.putExtra("dateOut", dateOut);
            intent.putExtra("numPeople", numPeople);
            context.startActivity(intent);
        }
    }


    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_list_room,
                        parent, false);
        return new RoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {
        Room room = lstRooms.get(position);
        /*String urlImage = "http://192.168.0.13:8090/Booking/images/" +
                lstRooms.get(position).getUrlImage() + ".png";*/
        String urlImage = BuildConfig.SERVER_URL + "images/" +
                lstRooms.get(position).getUrlImage() + ".png";
        Picasso.get().load(urlImage).into(holder.image);
        holder.nameHotel.setText(room.getHotelName());
        holder.city.setText(room.getCity());
        holder.available.setText(room.getAvailable());
        holder.capacity.setText(String.valueOf(room.getCapacity()));
        holder.number.setText(String.valueOf(room.getId()));
        holder.prize.setText(String.valueOf(room.getCost()));
        holder.setOnClickListeners();
        holder.dateIn = this.dateIn;
        holder.dateOut = this.dateOut;
        holder.numPeople = this.numPeople;
    }

    @Override
    public int getItemCount() {
        return lstRooms.size();
    }
}
