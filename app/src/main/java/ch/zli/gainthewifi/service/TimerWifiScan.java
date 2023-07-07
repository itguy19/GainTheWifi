package ch.zli.gainthewifi.service;

import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import ch.zli.gainthewifi.view.MainActivity;

public class TimerWifiScan extends TimerTask {
    private MainActivity mainActivity;

    public TimerWifiScan(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void run() {
        mainActivity.scanWifiList();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}





