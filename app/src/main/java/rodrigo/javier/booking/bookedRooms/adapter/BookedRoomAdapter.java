package rodrigo.javier.booking.bookedRooms.adapter;

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
import rodrigo.javier.booking.beans.Hotel;
import rodrigo.javier.booking.detailHotel.view.DetailHotelActivity;

public class BookedRoomAdapter extends RecyclerView.Adapter<BookedRoomAdapter.HotelViewHolder> {

    private ArrayList<Hotel> lstHotels;

    public BookedRoomAdapter(ArrayList<Hotel> lstHotels) {
        this.lstHotels = lstHotels;
    }

    public static class HotelViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        // Campos respectivos de un item
        public Context context;
        public LinearLayout rowHotelBooked;
        public ImageView image;
        public TextView name;
        public TextView category;
        public TextView city;
        public TextView rate;
        private TextView prize;
        private TextView bookedRooms;

        public HotelViewHolder(View view){
            super(view);
            context = view.getContext();
            rowHotelBooked = view.findViewById(R.id.rowHotelBooked);
            image = view.findViewById(R.id.imgPhoto);
            name = view.findViewById(R.id.txtName);
            category = view.findViewById(R.id.txtCategory);
            city = view.findViewById(R.id.txtCity);
            prize = view.findViewById(R.id.txtPrize);
            rate = view.findViewById(R.id.txtRate);
            bookedRooms = view.findViewById(R.id.txtBookedRooms);
        }

        void setOnClickListeners(){
            rowHotelBooked.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, DetailHotelActivity.class);
            intent.putExtra("nameHotel", name.getText());
            context.startActivity(intent);
        }
    }

    @NonNull
    @Override
    public BookedRoomAdapter.HotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_list_booked,
                        parent, false);
        return new HotelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookedRoomAdapter.HotelViewHolder holder, int position) {
        Hotel hotel = lstHotels.get(position);
        String urlImage ="http://192.168.0.12:8090/Booking/images/" +
                lstHotels.get(position).getUrlImage() + ".png";
        Picasso.get().load(urlImage).into(holder.image);
        holder.name.setText(hotel.getName());
        holder.category.setText(String.valueOf(hotel.getCategory()));
        holder.city.setText(hotel.getCity());
        holder.prize.setText(hotel.getAveragePrize() + "€");
        holder.bookedRooms.setText(String.valueOf(hotel.getBookedRooms()));
        holder.setOnClickListeners();
    }

    @Override
    public int getItemCount() {
        return lstHotels.size();
    }
}
