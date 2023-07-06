package ch.zli.gainthewifi.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ch.zli.gainthewifi.R;
import ch.zli.gainthewifi.modal.NetworkItem;

public class MainActivity extends AppCompatActivity {
    private ImageButton wifiScanButton;
    private TextView statusTextView;
    private boolean scanModeOn = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        statusTextView = findViewById(R.id.statusTextView);
        setStatusMessage("stopped", ResourcesCompat.getColor(getResources(), R.color.red, null));

        wifiScanButton = (ImageButton) findViewById(R.id.imageButton);
        setupClickListener();



        List<NetworkItem> networkItems = new ArrayList<>();
        networkItems.add(new NetworkItem("DONG-WON UPC89893", "1d-b9-a5-57-c7-d9", 6, "[WPA-TSK-]", -46, R.drawable.wifi_on));
        networkItems.add(new NetworkItem("DONG-WON UPC89893", "1d-b9-a5-57-c7-d9", 6, "[WPA-TSK-]", -46, R.drawable.wifi_on));
        networkItems.add(new NetworkItem("DONG-WON UPC89893", "1d-b9-a5-57-c7-d9", 6, "[WPA-TSK-]", -46, R.drawable.wifi_on));
        networkItems.add(new NetworkItem("DONG-WON UPC89893", "1d-b9-a5-57-c7-d9", 6, "[WPA-TSK-]", -46, R.drawable.wifi_on));
        networkItems.add(new NetworkItem("DONG-WON UPC89893", "1d-b9-a5-57-c7-d9", 6, "[WPA-TSK-]", -46, R.drawable.wifi_on));
        networkItems.add(new NetworkItem("DONG-WON UPC89893", "1d-b9-a5-57-c7-d9", 6, "[WPA-TSK-]", -46, R.drawable.wifi_on));
        networkItems.add(new NetworkItem("DONG-WON UPC89893", "1d-b9-a5-57-c7-d9", 6, "[WPA-TSK-]", -46, R.drawable.wifi_on));
        networkItems.add(new NetworkItem("DONG-WON UPC89893", "1d-b9-a5-57-c7-d9", 6, "[WPA-TSK-]", -46, R.drawable.wifi_on));
        networkItems.add(new NetworkItem("DONG-WON UPC89893", "1d-b9-a5-57-c7-d9", 6, "[WPA-TSK-]", -46, R.drawable.wifi_on));
        networkItems.add(new NetworkItem("DONG-WON UPC89893", "1d-b9-a5-57-c7-d9", 6, "[WPA-TSK-]", -46, R.drawable.wifi_on));
        networkItems.add(new NetworkItem("DONG-WON UPC89893", "1d-b9-a5-57-c7-d9", 6, "[WPA-TSK-]", -46, R.drawable.wifi_on));
        networkItems.add(new NetworkItem("DONG-WON UPC89893", "1d-b9-a5-57-c7-d9", 6, "[WPA-TSK-]", -46, R.drawable.wifi_on));
        networkItems.add(new NetworkItem("DONG-WON UPC89893", "1d-b9-a5-57-c7-d9", 6, "[WPA-TSK-]", -46, R.drawable.wifi_on));
        networkItems.add(new NetworkItem("DONG-WON UPC89893", "1d-b9-a5-57-c7-d9", 6, "[WPA-TSK-]", -46, R.drawable.wifi_on));

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ViewAdapter(getApplicationContext(), networkItems));

    }

    private void setupClickListener() {
        wifiScanButton.setOnClickListener(v -> {
            if (scanModeOn) {
                wifiScanButton.setImageResource(R.drawable.wifi_off);
                setStatusMessage("stopped", ResourcesCompat.getColor(getResources(), R.color.red, null));
            } else {
                wifiScanButton.setImageResource(R.drawable.wifi_on);
                setStatusMessage("running", ResourcesCompat.getColor(getResources(), R.color.green, null));
            }
            scanModeOn = !scanModeOn;
        });
    }

    @SuppressLint("SetTextI18n")
    private void setStatusMessage(String message, int color) {
        statusTextView.setText("Status:\n" + message);
        statusTextView.setTextColor(color);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String message = "Nothing clicked";
        if (item.getItemId() == R.id.saveRes) {
            message = "Data saved";
        } else if (item.getItemId() == R.id.exportDb) {
            message = "Data exported";
        } else if (item.getItemId() == R.id.clearDB) {
            message = "Data cleared";
        }
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }
}