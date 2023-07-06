package ch.zli.gainthewifi.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ch.zli.gainthewifi.R;
import ch.zli.gainthewifi.modal.NetworkItem;

public class ViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    Context context;
    List<NetworkItem> networkItems;

    public ViewAdapter(Context context, List<NetworkItem> networkItems) {
        this.context = context;
        this.networkItems = networkItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(networkItems.get(position).getName());
        holder.signalStrength.setText(String.valueOf(networkItems.get(position).getSignalStrength()));
        holder.macAddress.setText(networkItems.get(position).getMacAddress());
        holder.channelNumber.setText(String.valueOf(networkItems.get(position).getChannel()));
        holder.securityStandards.setText(networkItems.get(position).getSecurity());
        holder.wifiImageView.setImageResource(networkItems.get(position).getWifiImage());
    }

    @Override
    public int getItemCount() {
        return networkItems.size();
    }
}
