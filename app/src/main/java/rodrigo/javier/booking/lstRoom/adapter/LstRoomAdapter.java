package rodrigo.javier.booking.lstRoom.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

import rodrigo.javier.booking.R;
import rodrigo.javier.booking.SearchActivity;
import rodrigo.javier.booking.beans.Room;
import rodrigo.javier.booking.login.view.LoginActivity;

public class LstRoomAdapter extends RecyclerView.Adapter<LstRoomAdapter.RoomViewHolder> {

    private ArrayList<Room> lstRooms;

    public LstRoomAdapter(ArrayList<Room> lstRooms) {
        this.lstRooms = lstRooms;
    }

    public static class RoomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        //Campos respectivos de un item
        public Context context;
        public LinearLayout rowRoom;
        public ImageView image;
        public TextView nameHotel;
        public TextView available;
        public TextView capacity;
        public TextView number;
        public TextView prize;
        public TextView city;

        public RoomViewHolder(View view){
            super(view);
            context = view.getContext();
            rowRoom = view.findViewById(R.id.rowRoom);
            image = view.findViewById(R.id.imgPhotoRoom);
            nameHotel = view.findViewById(R.id.txtHotelRoom);
            available = view.findViewById(R.id.txtAvailableRoom);
            capacity = view.findViewById(R.id.txtCapacityRoom);
            number = view.findViewById(R.id.txtNumberRoom);
            prize = view.findViewById(R.id.txtPrizeRoom);
            city = view.findViewById(R.id.txtCityRoom);
        }

        void setOnClickListeners() {
            rowRoom.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, LoginActivity.class);
            intent.putExtra("id_room", number.getText());
            intent.putExtra("nameHotel", nameHotel.getText());
            intent.putExtra("city", city.getText());
            intent.putExtra("capacity", capacity.getText());
            intent.putExtra("prize", prize.getText());
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
        String urlImage = "http://192.168.0.12:8090/Booking/images/" +
                lstRooms.get(position).getUrlImage() + ".png";
        Picasso.get().load(urlImage).into(holder.image);
        holder.nameHotel.setText(room.getHotelName());
        holder.city.setText(room.getCity());
        holder.available.setText("Disponible: " + room.getAvailable());
        holder.capacity.setText(String.valueOf(room.getCapacity()));
        holder.number.setText(String.valueOf(room.getId()));
        holder.prize.setText(String.valueOf(room.getCost()));
        holder.setOnClickListeners();
    }

    @Override
    public int getItemCount() {
        return lstRooms.size();
    }
}
