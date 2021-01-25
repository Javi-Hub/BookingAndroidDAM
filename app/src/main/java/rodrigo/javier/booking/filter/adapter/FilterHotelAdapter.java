package rodrigo.javier.booking.filter.adapter;

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

import java.util.ArrayList;

import rodrigo.javier.booking.R;
import rodrigo.javier.booking.beans.Hotel;
import rodrigo.javier.booking.detailHotel.view.DetailHotelActivity;

public class FilterHotelAdapter extends RecyclerView.Adapter<FilterHotelAdapter.HotelViewHolder> {

    private ArrayList<Hotel> lstHotels;

    public FilterHotelAdapter(ArrayList<Hotel> lstHotels) {
        this.lstHotels = lstHotels;
    }

    public static class HotelViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        // Campos respectivos de un item
        public Context context;
        public LinearLayout rowHotel;
        public ImageView image;
        public TextView name;
        public TextView category;
        public TextView city;
        public TextView rate;
        private TextView prize;

        public HotelViewHolder(View view){
            super(view);
            context = view.getContext();
            rowHotel = view.findViewById(R.id.rowHotel);
            image = view.findViewById(R.id.imgPhoto);
            name = view.findViewById(R.id.txtName);
            category = view.findViewById(R.id.txtCategory);
            city = view.findViewById(R.id.txtCity);
            prize = view.findViewById(R.id.txtPrize);
            rate = view.findViewById(R.id.txtRate);
        }

        void setOnClickListeners(){
            rowHotel.setOnClickListener(this);
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
    public FilterHotelAdapter.HotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_list_hotel,
                        parent, false);
        return new HotelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilterHotelAdapter.HotelViewHolder holder, int position) {
        Hotel hotel = lstHotels.get(position);
        String urlImage ="http://192.168.0.12:8090/Booking/images/" +
                lstHotels.get(position).getUrlImage() + ".png";
        Picasso.get().load(urlImage).into(holder.image);
        holder.name.setText(hotel.getName());
        holder.category.setText(hotel.getCategory() + " estrellas");
        holder.city.setText(hotel.getCity());
        holder.prize.setText("Precio medio: " + hotel.getAveragePrize() + "€");
        holder.rate.setText("Valoración: " + hotel.getRate());
        holder.setOnClickListeners();
    }

    @Override
    public int getItemCount() {
        return lstHotels.size();
    }
}
