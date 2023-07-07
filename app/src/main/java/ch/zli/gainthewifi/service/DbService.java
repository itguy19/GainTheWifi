package ch.zli.gainthewifi.service;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;

import com.google.gson.Gson;

import org.json.JSONArray;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ch.zli.gainthewifi.modal.NetworkItem;
import ch.zli.gainthewifi.service.database.AppDatabase;

public class DbService extends Service {
    private final IBinder binder = new LocalBinder();
    private AppDatabase appDatabase;

    public class LocalBinder extends Binder {
        public DbService getService() {

            return DbService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public void saveData(List<NetworkItem> networkItems) {
        AsyncTask.execute(() -> {
            AppDatabase.getInstance(this).networkDao().insertAll(networkItems);
        });
    }

    public void exportData() {
        AsyncTask.execute(() -> {
            List<NetworkItem> networkItemList = AppDatabase.getInstance(this).networkDao().getAll();
            String json = new Gson().toJson(networkItemList);
            System.out.println(json);

            String filename = "data.json";
            try {
                FileWriter fileWriter = new FileWriter(filename);
                fileWriter.write(json);
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void clearData() {
        AsyncTask.execute(() -> {
            AppDatabase.getInstance(this).networkDao().clearDatabase();
        });
    }
}