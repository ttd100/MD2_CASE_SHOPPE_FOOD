package rikkei.academy.model;

import java.io.Serializable;

public class Like implements Serializable {
    private int id;
    private int idFood;
    private int idUser;

    public Like() {
    }

    public Like(int id, int idFood, int idUser) {
        this.id = id;
        this.idFood = idFood;
        this.idUser = idUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdFood() {
        return idFood;
    }

    public void setIdFood(int idFood) {
        this.idFood = idFood;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "Like{" +
                "id=" + id +
                ", idFood=" + idFood +
                ", idUser=" + idUser +
                '}';
    }
}
