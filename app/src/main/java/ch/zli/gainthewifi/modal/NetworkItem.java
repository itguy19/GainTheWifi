package ch.zli.gainthewifi.modal;

public class NetworkItem {
    private String name;
    private String macAddress;
    private int channel;
    private String security;
    private int signalStrength;
    private int wifiImage;

    public NetworkItem(String name, String macAddress, int channel, String security, int signalStrength, int wifiImage) {
        this.name = name;
        this.macAddress = macAddress;
        this.channel = channel;
        this.security = security;
        this.signalStrength = signalStrength;
        this.wifiImage = wifiImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

    public int getSignalStrength() {
        return signalStrength;
    }

    public void setSignalStrength(int signalStrength) {
        this.signalStrength = signalStrength;
    }

    public int getWifiImage() {
        return wifiImage;
    }

    public void setWifiImage(int wifiImage) {
        this.wifiImage = wifiImage;
    }
}
