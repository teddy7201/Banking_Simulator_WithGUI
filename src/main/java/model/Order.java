package model;

import java.util.ArrayList;

public class Order {
    private int number = 0;
    private ArrayList<MenuItem> items;

    public Order() {
        this.items = new ArrayList<>();
        this.number = number + 1;
    }

    public Order(ArrayList items) {
        this.items = items;
        this.number = number + 1;
    }

    public int getNumber() {
        return number;
    }

    public ArrayList<MenuItem> getItems() {
        return items;
    }

    public void addToOrder(MenuItem item) {
        items.add(item);
    }

    public void removeItem(MenuItem item) {
        items.remove(item);
    }

    public void removeItemByIndex(int i) {
        items.remove(i);
    }

    @Override
    public String toString() {
        StringBuilder orderDetails = new StringBuilder();
        for (MenuItem item : items) {
            orderDetails.append(item.toString() + "\n");
        }
        return orderDetails.toString();
    }

}