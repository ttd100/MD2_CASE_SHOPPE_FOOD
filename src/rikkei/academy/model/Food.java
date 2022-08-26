package rikkei.academy.model;

import java.io.Serializable;

public class Food implements Comparable<Food>, Serializable {
    int id;
    String name;
    int price;

    public Food() {
    }

    public Food(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Food(String newName, String newPrice) {
        this.name = newName;
        this.price = Integer.parseInt(newPrice);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
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
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public int compareTo(Food o) {
        int temp = o.getName().compareTo(o.getName());
        if (temp != 0) {
            return temp;
        }
        temp = this.getPrice() - o.getPrice();
        return temp;
    }
}
