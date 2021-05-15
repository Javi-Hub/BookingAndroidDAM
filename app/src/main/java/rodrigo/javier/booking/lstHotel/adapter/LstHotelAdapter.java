package rodrigo.javier.booking.lstHotel.adapter;

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
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import rodrigo.javier.booking.BuildConfig;
import rodrigo.javier.booking.R;
import rodrigo.javier.booking.beans.Hotel;
import rodrigo.javier.booking.detailHotel.view.DetailHotelActivity;

public class LstHotelAdapter extends RecyclerView.Adapter<LstHotelAdapter.HotelViewHolder> {

    private ArrayList<Hotel> lstHotels;

    public static String TAG = LstHotelAdapter.class.getSimpleName();

    public LstHotelAdapter(ArrayList<Hotel> lstHotels){
        this.lstHotels = lstHotels;
    }

    public static class HotelViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
       // Campos respectivos de un item
        public Context context;
        public CardView card_list_hotel;
        public ImageView image;
        public ImageView icon;
        public TextView name;
        public TextView category;
        public TextView city;
        public TextView rate;
        private TextView prize;
        private Hotel hotel;
        private RatingBar ratingBar;

        private boolean click = false;

       public HotelViewHolder(View view){
           super(view);
           context = view.getContext();
           card_list_hotel = view.findViewById(R.id.card_list_hotel);
           image = view.findViewById(R.id.imgPhoto);
           icon = view.findViewById(R.id.card_favorite_icon);
           name = view.findViewById(R.id.txtName);
           //category = view.findViewById(R.id.txtCategory);
           city = view.findViewById(R.id.txtCity);
           rate = view.findViewById(R.id.txtRate);
           prize = view.findViewById(R.id.txtPrize);
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
            card_list_hotel.setOnClickListener(this);
        }

        /*Al seleccionar el hotel se cambia de Activity para mostar
         * todos los datos de la selección */
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, DetailHotelActivity.class);
            intent.putExtra("hotel", hotel);
            Log.d(TAG, "[getHotel] Name " + hotel.getName());
            context.startActivity(intent);
        }
    }

    @NonNull
    @Override
    public HotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_list_hotel,
                        parent, false);
        return new HotelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelViewHolder holder, int position) {
        Hotel hotel = lstHotels.get(position);
        /*String urlImage = "http://192.168.0.13:8090/Booking/images/" +
                lstHotels.get(position).getUrlImage() + ".png";*/
        String urlImage = BuildConfig.SERVER_URL + "images/" +
                lstHotels.get(position).getUrlImage() + ".png";
        Picasso.get().load(urlImage).into(holder.image);
        holder.name.setText(hotel.getName());
        //holder.category.setText(String.valueOf(hotel.getCategory()));
        holder.city.setText(hotel.getCity());
        holder.prize.setText(hotel.getAveragePrize() + "€");
        holder.rate.setText(String.valueOf(hotel.getRate()));
        holder.setOnClickListeners();
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

    }

    @Override
    public int getItemCount() {
        return lstHotels.size();
    }
}
