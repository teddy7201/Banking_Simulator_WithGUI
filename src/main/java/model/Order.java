package model;

import java.util.ArrayList;

public class Order {
    private int number = 0;
    private ArrayList<MenuItem> items;

    public Order(ArrayList items){
        this.items = items;
        this.number = number + 1;
    }

    public int getNumber() {
        return number;
    }

    public ArrayList<MenuItem> getItems() {
        return items;
    }
}
