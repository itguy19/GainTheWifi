package ch.zli.gainthewifi.service.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import ch.zli.gainthewifi.modal.NetworkItem;

@Dao
public interface NetworkDao {
    @Query("SELECT * FROM networkitem")
    List<NetworkItem> getAll();

    @Query("DELETE FROM networkitem")
    void clearDatabase();

    @Insert
    void insertAll(List<NetworkItem> networkItems);
}

