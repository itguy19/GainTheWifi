package ch.zli.gainthewifi.service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import ch.zli.gainthewifi.view.MainActivity;

public class TimerWifiScan {
    private static MainActivity mainActivity;
    public TimerWifiScan(MainActivity mainActivity) {
        TimerWifiScan.mainActivity = mainActivity;

        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        Runnable task = TimerWifiScan::scanAgain;

        // Schedule the task to run every 5 seconds
        executor.scheduleAtFixedRate(task, 0, 5, TimeUnit.SECONDS);
    }

    public static void scanAgain() {
        if (MainActivity.scanModeOn) {
            TimerWifiScan.mainActivity.scanWifiList();
        }
    }
}





