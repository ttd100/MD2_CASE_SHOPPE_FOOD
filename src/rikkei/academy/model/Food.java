package rikkei.academy.model;

import java.io.Serializable;

public class Food implements Comparable<Food>, Serializable {
    private int id;
    private String name;
    private int price;
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Food(int id, String name, int price, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }



    public Food(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Food(String newName, String newPrice, Category category) {
        this.name = newName;
        this.price = Integer.parseInt(newPrice);
        this.category = category;
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
                ", category=" + category +
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
