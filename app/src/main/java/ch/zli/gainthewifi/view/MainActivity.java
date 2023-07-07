package ch.zli.gainthewifi.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.ACCESS_NETWORK_STATE;
import static android.Manifest.permission.ACCESS_WIFI_STATE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import ch.zli.gainthewifi.R;
import ch.zli.gainthewifi.modal.NetworkItem;
import ch.zli.gainthewifi.service.DbService;

public class MainActivity extends AppCompatActivity {

    private ImageButton wifiScanButton;
    private TextView statusTextView;
    private RecyclerView recyclerView;
    public static boolean scanModeOn = false;
    private WifiManager wifiManager;
    private List<NetworkItem> networkItems;
    private DbService dbService;
    private boolean isDbServceBound;


    @SuppressLint("StaticFieldLeak")
    public static MainActivity mainActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivity = this;

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        statusTextView = findViewById(R.id.statusTextView);
        setStatusMessage("stopped", ResourcesCompat.getColor(getResources(), R.color.red, null));

        wifiScanButton = (ImageButton) findViewById(R.id.imageButton);
        setupClickListener();

        networkItems = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        checkPermissions();

    }

    private void checkPermissions() {
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION}, 0);
        }
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{ACCESS_NETWORK_STATE}, 0);
        }
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), ACCESS_WIFI_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{ACCESS_WIFI_STATE}, 0);
        }
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{WRITE_EXTERNAL_STORAGE}, 0);
        }
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), ACCESS_WIFI_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{ACCESS_WIFI_STATE}, 0);
        }
    }

    /**************************************WIFI SCANNER**********************************************/

    public void scanWifiList() {
        wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        registerReceiver(wifiScanReceiver, intentFilter);

        // Enable WIFI scan
        if (!wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        }

        boolean success = wifiManager.startScan();
        if (!success) {
            scanResults();
        } else {
            scanResults();
        }
    }

    private void scanResults() {
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        List<ScanResult> results = wifiManager.getScanResults();
        networkItems.clear();
        for (ScanResult r: results) {
            String capabilities = r.capabilities;
            if (capabilities.length() >= 8) {
                capabilities = r.capabilities.substring(0, 8) + "..";
            }
            networkItems.add(new NetworkItem(r.SSID, r.BSSID, r.frequency, capabilities, r.level, R.drawable.wifi_on));
        }
        recyclerView.setAdapter(new ViewAdapter(getApplicationContext(), networkItems));
    }

    BroadcastReceiver wifiScanReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context c, Intent intent) {}
    };

    /**************************************WIFI SCANNER END****************************************/


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent bindDbServiceIntent = new Intent(this, DbService.class);
        bindService(bindDbServiceIntent, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(connection);
        isDbServceBound = false;
    }

    private final ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            DbService.LocalBinder binder = (DbService.LocalBinder)  iBinder;
            dbService = binder.getService();
            isDbServceBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isDbServceBound = false;
        }
    };
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String message = "Nothing clicked";
        if (item.getItemId() == R.id.saveRes) {
            message = "Data saved";
            dbService.saveData(networkItems);
        } else if (item.getItemId() == R.id.exportDb) {
            message = "Data exported";
            dbService.exportData();
        } else if (item.getItemId() == R.id.clearDB) {
            message = "Data cleared";
            dbService.clearData();
        }
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }

    private void setupClickListener() {
        wifiScanButton.setOnClickListener(v -> {
            if (scanModeOn) {
                wifiScanButton.setImageResource(R.drawable.wifi_off);
                setStatusMessage("stopped", ResourcesCompat.getColor(getResources(), R.color.red, null));
            } else {
                wifiScanButton.setImageResource(R.drawable.wifi_on);
                setStatusMessage("running", ResourcesCompat.getColor(getResources(), R.color.green, null));
                scanWifiList();
            }
            scanModeOn = !scanModeOn;
        });
    }

    @SuppressLint("SetTextI18n")
    private void setStatusMessage(String message, int color) {
        statusTextView.setText("Status:\n" + message);
        statusTextView.setTextColor(color);
    }
}