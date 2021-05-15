package rodrigo.javier.booking.fieldSearch.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
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

import java.util.ArrayList;

import rodrigo.javier.booking.BuildConfig;
import rodrigo.javier.booking.R;
import rodrigo.javier.booking.beans.Hotel;
import rodrigo.javier.booking.detailHotel.view.DetailHotelActivity;
import rodrigo.javier.booking.fieldSearch.view.FieldSearchActivity;
import rodrigo.javier.booking.lstHotel.adapter.LstHotelAdapter;

public class FieldSearchAdapter extends RecyclerView.Adapter<FieldSearchAdapter.HotelViewHolder> {

    private ArrayList<Hotel> lstHotels;
    private String dateIn, dateOut, numPeople;

    public FieldSearchAdapter(ArrayList<Hotel> lstHotels, String dateIn, String dateOut, String numPeople) {
        this.lstHotels = lstHotels;
        this.dateIn = dateIn;
        this.dateOut = dateOut;
        this.numPeople = numPeople;
    }

    public static class HotelViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // Campos respectivos de un item
        private Context context;
        public CardView card_list_hotel;
        private ImageView image;
        private ImageView icon;
        private TextView name;
        private TextView category;
        private TextView city;
        private TextView rate;
        private TextView prize;
        private String dateIn, dateOut, numPeople;
        private Hotel hotel;
        private RatingBar ratingBar;

        private boolean click = false;

        public HotelViewHolder(View view){
            super(view);
            context = view.getContext();
            card_list_hotel = view.findViewById(R.id.card_list_hotel);
            image = view.findViewById(R.id.imgPhoto);
            icon = view.findViewById(R.id.card_favorite_icon);
            ratingBar = view.findViewById(R.id.card_rating_bar);
            name = view.findViewById(R.id.txtName);
            //category = view.findViewById(R.id.txtCategory);
            city = view.findViewById(R.id.txtCity);
            rate = view.findViewById(R.id.txtRate);
            prize = view.findViewById(R.id.txtPrize);

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
            intent.putExtra("nameHotel", name.getText());
            intent.putExtra("dateIn", dateIn);
            intent.putExtra("dateOut", dateOut);
            intent.putExtra("numPeople", numPeople);
            intent.putExtra("hotel", hotel);
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
        //holder.category.setText(hotel.getCategory() + " estrellas");
        holder.city.setText(hotel.getCity());
        holder.prize.setText(hotel.getAveragePrize() + "€");
        holder.rate.setText(String.valueOf(hotel.getRate()));
        holder.dateIn = this.dateIn;
        holder.dateOut = this.dateOut;
        holder.numPeople = this.numPeople;
        holder.hotel = hotel;
        holder.setOnClickListeners();
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
