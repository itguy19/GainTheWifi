package ch.zli.gainthewifi.service;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.ACCESS_WIFI_STATE;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Binder;
import android.os.IBinder;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

import ch.zli.gainthewifi.modal.NetworkItem;
import ch.zli.gainthewifi.view.MainActivity;

public class WifiService extends Service {
    private final IBinder binder = new WifiBinder();
    private WifiManager wifiManager;

    public class WifiBinder extends Binder {
        public WifiService getService() {
            return WifiService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

}