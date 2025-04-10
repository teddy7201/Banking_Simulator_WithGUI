package controller.panels;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.scene.Node;
import model.MenuItem;
import model.Order;
import model.OrderManager;

import java.io.IOException;

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
    private Label orderNumberLabel;

    @FXML
    public void initialize() {
        // Load order items from OrderManager
        refreshOrderView();
    }

    /**
     * Refresh the order view from the current OrderManager data
     */
    public void refreshOrderView() {
        // Clear existing items
        orderItemsListView.getItems().clear();

        // Get the current order from OrderManager
        Order currentOrder = OrderManager.getInstance().getCurrentOrder();
        orderNumberLabel.setText(String.format("%d",currentOrder.getNumber()));
        // Add all items to the ListView
        for (MenuItem item : currentOrder.getItems()) {
            String itemDescription = item.toString() + " - $" + String.format("%.2f", item.price());
            orderItemsListView.getItems().add(itemDescription);
        }

        // Update price labels
        updatePriceLabels();
    }

    @FXML
    protected void onRemoveSelectedItemClick() {
        int selectedIndex = orderItemsListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            // Remove from OrderManager
            OrderManager.getInstance().getCurrentOrder().removeItemByIndex(selectedIndex);

            // Update the view
            refreshOrderView();
        }
    }

    @FXML
    protected void onClearAllItemsClick() {
        // Start a new order in OrderManager
        OrderManager.getInstance().startNewOrder();

        // Update the view
        refreshOrderView();
    }

    @FXML
    protected void onPlaceOrderClick() {
        // Place the order and get the order ID
        String orderId = OrderManager.getInstance().placeOrder();

        if (orderId == null) {
            return;
        }

        refreshOrderView();

        // Navigate back to main view (which has sidebar navigation)
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/softwaremethproject4/hello-view.fxml"));
            Parent root = loader.load();

            // Get the MainController to set the sandwich panel as visible
            controller.MainController mainController = loader.getController();
            mainController.navigateToSandwichPanel();

            // Get the scene and set it
            Stage stage = (Stage) ((Node) orderItemsListView).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updatePriceLabels() {
        // Calculate subtotal from the current order
        Order currentOrder = OrderManager.getInstance().getCurrentOrder();
        double subtotal = 0.0;

        for (MenuItem item : currentOrder.getItems()) {
            subtotal += item.price();
        }

        // Calculate tax and total ( if we want to add tax)
        double tax = subtotal * 0.06625;
        double total = subtotal + tax;

        // Update labels
        subtotalLabel.setText(String.format("$%.2f", subtotal));
        taxLabel.setText(String.format("$%.2f", tax));
        totalLabel.setText(String.format("$%.2f", total));
    }
}