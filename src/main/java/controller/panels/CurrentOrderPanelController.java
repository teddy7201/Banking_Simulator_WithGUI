package controller.panels;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class CurrentOrderPanelController {
    @FXML
    private ListView<String> orderItemsListView;

    @FXML
    private Label subtotalLabel;

    @FXML
    private Label taxLabel;

    @FXML
    private Label totalLabel;

    @FXML
    public void initialize() {
        // Initialize the ListView with sample data (for testing)
        orderItemsListView.getItems().addAll(
                "Sandwich - Roast Beef - $8.99",
                "Burger - Single Patty - $7.99",
                "Beverage - Cola (Medium) - $2.49");

        // Update price labels
        updatePriceLabels();
    }

    @FXML
    protected void onRemoveSelectedItemClick() {
        int selectedIndex = orderItemsListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            orderItemsListView.getItems().remove(selectedIndex);
            updatePriceLabels();
        }
    }

    @FXML
    protected void onClearAllItemsClick() {
        orderItemsListView.getItems().clear();
        updatePriceLabels();
    }

    @FXML
    protected void onPlaceOrderClick() {
        System.out.println("Order placed!");
    }

    private void updatePriceLabels() {
        // Calculate subtotal (summing all items) - this is a simple example
        double subtotal = 0.0;
        for (String item : orderItemsListView.getItems()) {
            // Extract price from item description
            try {
                String priceStr = item.substring(item.lastIndexOf("$") + 1);
                subtotal += Double.parseDouble(priceStr);
            } catch (Exception e) {
                System.err.println("Error parsing price: " + e.getMessage());
            }
        }

        // Calculate tax and total
        double tax = subtotal * 0.06625;
        double total = subtotal + tax;

        // Update labels
        subtotalLabel.setText(String.format("$%.2f", subtotal));
        taxLabel.setText(String.format("$%.2f", tax));
        totalLabel.setText(String.format("$%.2f", total));
    }
}