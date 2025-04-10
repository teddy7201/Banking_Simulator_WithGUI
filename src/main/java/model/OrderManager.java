package model;

/**
 * Singleton class to manage the current order across different controllers.
 */
public class OrderManager {
    private static OrderManager instance = null;
    private Order currentOrder;

    private OrderManager() {
        // Initialize with an empty order
        currentOrder = new Order();
    }

    /**
     *
     * @return The OrderManager instance
     */
    public static OrderManager getInstance() {
        if (instance == null) {
            instance = new OrderManager();
        }
        return instance;
    }

    /**
     * Get the current order
     * 
     * @return The current order
     */
    public Order getCurrentOrder() {
        return currentOrder;
    }

    /**
     * Add an item to the current order
     * 
     * @param item MenuItem to add to the order
     */
    public void addItemToOrder(MenuItem item) {
        currentOrder.addToOrder(item);
    }

    /**
     * Clear the current order and create a new one
     */
    public void startNewOrder() {
        currentOrder = new Order();
    }
}