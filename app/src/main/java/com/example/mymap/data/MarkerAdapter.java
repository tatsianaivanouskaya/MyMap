package com.example.mymap.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mymap.R;
import java.util.List;

public class MarkerAdapter extends RecyclerView.Adapter<MarkerAdapter.ViewHolder> {

    private List<MarkerInfo> markerItems;
    private Context mContext;
    private static ClickListener clickListener;

    public MarkerAdapter(List<MarkerInfo> markerItems, Context mContext) {
        this.markerItems = markerItems;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_marker, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final MarkerInfo itemList = markerItems.get(position);
        String txtLat = mContext.getString(R.string.lat) + itemList.getLatitude();
        String txtLon = mContext.getString(R.string.lon) + itemList.getLongitude();
        holder.txtLatitude.setText(txtLat);
        holder.txtLongitude.setText(txtLon);
        holder.txtCountry.setText(itemList.getCountry());
        holder.txtCity.setText(itemList.getAdministrativeArea());
    }

    @Override
    public int getItemCount() {
        return markerItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtLatitude;
        TextView txtLongitude;
        TextView txtCountry;
        TextView txtCity;

        ViewHolder(View itemView) {
            super(itemView);
            txtLatitude = itemView.findViewById(R.id.tv_hist_latitude);
            txtLongitude = itemView.findViewById(R.id.tv_hist_longitude);
            txtCountry = itemView.findViewById(R.id.tv_hist_country);
            txtCity = itemView.findViewById(R.id.tv_hist_city);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        MarkerAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
    }
}
