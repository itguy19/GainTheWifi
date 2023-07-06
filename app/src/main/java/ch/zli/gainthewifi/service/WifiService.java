package ch.zli.gainthewifi.service;

import android.Manifest;
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

import java.util.ArrayList;
import java.util.List;

import ch.zli.gainthewifi.modal.NetworkItem;

public class WifiService extends Service {
    private final IBinder binder = new WifiBinder();
    private WifiManager wifiManager;
    public class WifiBinder extends Binder {
        WifiService getService() {
            return WifiService.this;
        }
    }

    public void startScan() {
        wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        context.registerReceiver(wifiScanReceiver, intentFilter);

        // Enable WIFI scan
        if (!wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        }

        boolean success = wifiManager.startScan();
        if (!success) {
            scanFailure();
        }
    }

    public void stopScanning() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public ArrayList<NetworkItem> getNetworkList() {
        return null;
    }

    private Context context;

    BroadcastReceiver wifiScanReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context c, Intent intent) {
            boolean success = intent.getBooleanExtra(
                    WifiManager.EXTRA_RESULTS_UPDATED, false);
            if (success) {
                scanSuccess();
            } else {
                scanFailure();
            }
        }
    };

    private void scanSuccess() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_WIFI_STATE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        List<ScanResult> results = wifiManager.getScanResults();
        for (ScanResult r: results) {
            System.out.println(r);
        }
    }

    private void scanFailure() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_WIFI_STATE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        List<ScanResult> results = wifiManager.getScanResults();
    }


}