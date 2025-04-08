package controller.panels;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class AllOrdersPanelController {
    @FXML
    private ComboBox<String> orderNumberCB;

    @FXML
    private TextArea orderDetailsTextArea;

    @FXML
    public void initialize() {
        // Add sample order numbers for testing
        orderNumberCB.getItems().addAll("Order #1", "Order #2", "Order #3");

        // Set up listener for order selection
        orderNumberCB.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showOrderDetails(newValue));
    }

    @FXML
    protected void onCancelSelectedOrderClick() {
        String selectedOrder = orderNumberCB.getSelectionModel().getSelectedItem();
        if (selectedOrder != null) {
            System.out.println("Cancelling order: " + selectedOrder);
            // In a real app, you would remove the order from your database/data structure
            orderNumberCB.getItems().remove(selectedOrder);
            orderDetailsTextArea.clear();
        }
    }

    @FXML
    protected void onExportAllOrdersClick() {
        System.out.println("Exporting all orders");
        // In a real app, you would implement export functionality here
    }

    private void showOrderDetails(String orderNumber) {
        if (orderNumber == null)
            return;

        // In a real app, you would retrieve order details from your database/data
        // structure
        // This is just sample data for testing
        String orderDetails = switch (orderNumber) {
            case "Order #1" -> """
                    Order #1
                    Date: May 1, 2023
                    Items:
                    - Sandwich - Roast Beef - $8.99
                    - Beverage - Cola (Medium) - $2.49

                    Subtotal: $11.48
                    Tax: $0.76
                    Total: $12.24
                    """;
            case "Order #2" -> """
                    Order #2
                    Date: May 2, 2023
                    Items:
                    - Burger - Double Patty - $10.49
                    - Side - Fries (Large) - $3.49
                    - Beverage - Tea (Large) - $2.99

                    Subtotal: $16.97
                    Tax: $1.12
                    Total: $18.09
                    """;
            case "Order #3" -> """
                    Order #3
                    Date: May 3, 2023
                    Items:
                    - Sandwich - Chicken - $7.99
                    - Side - Chips (Small) - $1.99

                    Subtotal: $9.98
                    Tax: $0.66
                    Total: $10.64
                    """;
            default -> "No details available for " + orderNumber;
        };

        orderDetailsTextArea.setText(orderDetails);
    }
}