package ch.zli.gainthewifi.modal;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class NetworkItem {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public String macAddress;
    public int channel;
    public String security;
    public int signalStrength;
    public int wifiImage;
    
    public NetworkItem(String name, String macAddress, int channel, String security, int signalStrength, int wifiImage) {
        this.name = name;
        this.macAddress = macAddress;
        this.channel = channel;
        this.security = security;
        this.signalStrength = signalStrength;
        this.wifiImage = wifiImage;
    }
}
