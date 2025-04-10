package model;

import java.util.ArrayList;

public class OrderArchive {
    private ArrayList<Order> completedOrders;

    public OrderArchive() {
        completedOrders = new ArrayList<>();
    }

    public void addToArchive(Order order) {
        completedOrders.add(order);
    }
}