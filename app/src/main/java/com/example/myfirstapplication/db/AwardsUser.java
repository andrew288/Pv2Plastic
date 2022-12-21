package com.example.myfirstapplication.db;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "awards_user",
        foreignKeys = {@ForeignKey(
                entity = Award.class,
                parentColumns = "id",
                childColumns = "idAward"
        ), @ForeignKey(
                entity = User.class,
                parentColumns = "id",
                childColumns = "idUser"
        )})
public class AwardsUser {
    @PrimaryKey
    private int id;
    @NonNull
    private int idAward;
    @NonNull
    private int idUser;

    public AwardsUser(int idAward, int idUser) {
        this.idAward = idAward;
        this.idUser = idUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAward() {
        return idAward;
    }

    public void setIdAward(int idAward) {
        this.idAward = idAward;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
