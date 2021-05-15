package rodrigo.javier.booking.bookedRooms.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

import rodrigo.javier.booking.BuildConfig;
import rodrigo.javier.booking.R;
import rodrigo.javier.booking.beans.Hotel;
import rodrigo.javier.booking.detailHotel.view.DetailHotelActivity;

public class BookedRoomAdapter extends RecyclerView.Adapter<BookedRoomAdapter.HotelViewHolder> {

    private ArrayList<Hotel> lstHotels;

    public static String TAG = BookedRoomAdapter.class.getSimpleName();

    public BookedRoomAdapter(ArrayList<Hotel> lstHotels) {
        this.lstHotels = lstHotels;
    }

    public static class HotelViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        // Campos respectivos de un item
        public Context context;
        public CardView card_row_hotel_booked;
        public ImageView image;
        public TextView name;
        public TextView category;
        public ImageView icon;
        public TextView city;
        public TextView rate;
        private TextView prize;
        private TextView bookedRooms;
        private Hotel hotel;
        private RatingBar ratingBar;

        private boolean click = false;

        public HotelViewHolder(View view){
            super(view);
            context = view.getContext();
            card_row_hotel_booked = view.findViewById(R.id.card_row_hotel_booked);
            image = view.findViewById(R.id.imgPhoto);
            name = view.findViewById(R.id.txtName);
            icon = view.findViewById(R.id.card_favorite_icon);
            city = view.findViewById(R.id.txtCity);
            prize = view.findViewById(R.id.txtPrize);
            rate = view.findViewById(R.id.txtRate);
            bookedRooms = view.findViewById(R.id.txtBookedRooms);
            ratingBar = view.findViewById(R.id.card_rating_bar);

            icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    click = !click;
                    if (click){
                        icon.setImageResource(R.drawable.ic_fill_favorite);
                        icon.setColorFilter(Color.RED);
                    } else {
                        icon.setImageResource(R.drawable.ic_card_favorite);
                        icon.setColorFilter(Color.BLACK);
                    }
                }
            });
        }

        void setOnClickListeners(){
            card_row_hotel_booked.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, DetailHotelActivity.class);
            intent.putExtra("hotel", hotel);
            Log.i(TAG, "[To DetailHotelActivity - Hotel ] -> " + hotel.getName());
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
        String urlImage = BuildConfig.SERVER_URL + "images/" +
                lstHotels.get(position).getUrlImage() + ".png";
        Picasso.get().load(urlImage).into(holder.image);
        holder.name.setText(hotel.getName());
        holder.city.setText(hotel.getCity());
        holder.prize.setText(hotel.getAveragePrize() + "€");
        holder.bookedRooms.setText(String.valueOf(hotel.getBookedRooms()));
        holder.hotel = hotel;
        int stars = hotel.getCategory();
        switch (stars){
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                holder.ratingBar.setRating(stars);
        }
        holder.setOnClickListeners();
    }

    @Override
    public int getItemCount() {
        return lstHotels.size();
    }
}
