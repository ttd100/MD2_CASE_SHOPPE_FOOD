package rikkei.academy.model;

import java.io.Serializable;

public class Food implements Serializable {
    int id;
    int name;
    int price;

    public Food() {
    }

    public Food(int id, int name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", name=" + name +
                ", price=" + price +
                '}';
    }
}
