package ch.zli.gainthewifi.view;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ch.zli.gainthewifi.R;

public class ViewHolder extends RecyclerView.ViewHolder {

    ImageView wifiImageView;
    TextView name, signalStrength, macAddress, channelNumber, securityStandards;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        wifiImageView = itemView.findViewById(R.id.wifiImageView);
        name = itemView.findViewById(R.id.name);
        signalStrength = itemView.findViewById(R.id.signalStrength);
        macAddress = itemView.findViewById(R.id.macAddress);
        channelNumber = itemView.findViewById(R.id.channelNumber);
        securityStandards = itemView.findViewById(R.id.securityStandards);
    }
}
