package controller.panels;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

import model.OrderManager;

import java.net.URL;
import java.util.ResourceBundle;

public class AllOrdersPanelController implements Initializable {
    @FXML
    private ComboBox<String> orderNumberCB;

    @FXML
    private TextArea orderDetailsTextArea;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshOrderList();

        // Set up listener for order selection
        orderNumberCB.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showOrderDetails(newValue));
    }

    /**
     * Refreshes the list of orders in the ComboBox
     */
    private void refreshOrderList() {
        // Clear existing items
        orderNumberCB.getItems().clear();

        // Add all archived orders
        String[] orderIds = OrderManager.getInstance().getArchivedOrderIds();
        if (orderIds.length > 0) {
            orderNumberCB.getItems().addAll(orderIds);
        } else {
            // Add a placeholder if no orders exist
            orderDetailsTextArea.setText("No orders have been placed yet.");
        }
    }

    @FXML
    protected void onCancelSelectedOrderClick() {
        String selectedOrder = orderNumberCB.getSelectionModel().getSelectedItem();
        if (selectedOrder != null) {
            // Remove the order from the OrderManager
            OrderManager.getInstance().removeArchivedOrder(selectedOrder);

            // Update the UI
            orderNumberCB.getItems().remove(selectedOrder);
            orderDetailsTextArea.clear();

            if (orderNumberCB.getItems().isEmpty()) {
                orderDetailsTextArea.setText("No orders remaining.");
            }

            // Show confirmation alert
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Order Cancelled");
            alert.setHeaderText(null);
            alert.setContentText("Order " + selectedOrder + " has been cancelled.");
            alert.showAndWait();
        }
    }

    @FXML
    protected void onExportAllOrdersClick() {
        // Get all orders
        String[] orderIds = OrderManager.getInstance().getArchivedOrderIds();

        if (orderIds.length == 0) {
            // Show alert if no orders to export
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Orders");
            alert.setHeaderText(null);
            alert.setContentText("There are no orders to export.");
            alert.showAndWait();
            return;
        }

        // Create a comprehensive report of all orders
        StringBuilder report = new StringBuilder();
        report.append("===== ORDER EXPORT =====\n\n");

        double totalRevenue = 0.0;

        for (String orderId : orderIds) {
            OrderManager.ArchivedOrder order = OrderManager.getInstance().getArchivedOrder(orderId);
            report.append(order.getFormattedDetails()).append("\n\n");
            report.append("------------------------------\n\n");
            totalRevenue += order.getTotal();
        }

        report.append("Total Revenue: $").append(String.format("%.2f", totalRevenue));

        // Show the export in the text area
        orderDetailsTextArea.setText(report.toString());

        // In a real app, you could save this to a file, send via email, etc.
        System.out.println("Orders exported");
    }

    private void showOrderDetails(String orderId) {
        if (orderId == null) {
            return;
        }

        // Get the archived order from the OrderManager
        OrderManager.ArchivedOrder order = OrderManager.getInstance().getArchivedOrder(orderId);

        if (order != null) {
            // Display the order details
            orderDetailsTextArea.setText(order.getFormattedDetails());
        } else {
            orderDetailsTextArea.setText("Order details not available for " + orderId);
        }
    }
}