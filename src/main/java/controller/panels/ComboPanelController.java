package controller.panels;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Controller for the combo panel where users can customize their combo meal
 */
public class ComboPanelController {

    @FXML
    private ComboBox<String> comboSideTypeCB;

    @FXML
    private ComboBox<String> comboDrinkTypeCB;

    @FXML
    private Label itemTypeLabel;

    @FXML
    private Label itemDetailsLabel;

    @FXML
    private Label baseItemPriceLabel;

    @FXML
    private Label comboPriceLabel;

    @FXML
    private ImageView comboImageView;

    @FXML
    private VBox comboPane;

    private String itemType; // "Burger" or "Sandwich"
    private Object originalItem; // Reference to the original burger or sandwich
    private double basePrice; // Original item price
    private static final double COMBO_UPCHARGE = 3.00; // Standard combo upcharge

    /**
     * Initialize the controller
     */
    @FXML
    public void initialize() {
        // Initialize combo boxes if needed
        if (comboSideTypeCB.getItems().isEmpty()) {
            comboSideTypeCB.getItems().addAll("Chips", "Apple", "French Fries");
        }

        if (comboDrinkTypeCB.getItems().isEmpty()) {
            comboDrinkTypeCB.getItems().addAll("Cola", "Tea", "Juice", "Water");
        }

        // Add listeners to update price when options change
        comboSideTypeCB.setOnAction(e -> updateComboPrice());
        comboDrinkTypeCB.setOnAction(e -> updateComboPrice());
    }

    /**
     * Initialize the combo panel with the item details
     * 
     * @param itemType    The type of item ("Burger" or "Sandwich")
     * @param item        The original item object
     * @param basePrice   The base price of the item
     * @param itemDetails Brief description of the item configuration
     */
    public void initializeWithItem(String itemType, Object item, double basePrice, String itemDetails) {
        this.itemType = itemType;
        this.originalItem = item;
        this.basePrice = basePrice;

        // Set labels
        itemTypeLabel.setText("Item: " + itemType);
        itemDetailsLabel.setText(itemDetails);
        baseItemPriceLabel.setText(String.format("$%.2f", basePrice));

        // Update image based on item type
        String imagePath = "/com/softwaremethproject4/images/" + itemType.toLowerCase() + ".png";
        comboImageView.setImage(new Image(getClass().getResourceAsStream(imagePath)));

        // Calculate and display initial combo price
        updateComboPrice();
    }

    /**
     * Update the combo price based on selections
     */
    private void updateComboPrice() {
        // Add combo upcharge to base price
        double comboPrice = basePrice + COMBO_UPCHARGE;
        comboPriceLabel.setText(String.format("$%.2f", comboPrice));
    }

    /**
     * Handle add to order button click
     */
    @FXML
    private void onAddToOrderClick() {
        // Logic to add the combo to the order
        // In a real implementation, this would add to an order object
        System.out.println("Adding " + itemType + " combo to order");

        // Navigate back to main ordering screen or another appropriate screen
        // This implementation would depend on your application flow
    }

    /**
     * Handle proceed to checkout button click
     */
    @FXML
    private void onProceedToCheckoutClick() {
        // Logic to proceed to checkout
        // In a real implementation, this would navigate to a checkout screen
        System.out.println("Proceeding to checkout with " + itemType + " combo");

        // Navigate to checkout screen
        // This implementation would depend on your application flow
    }

    /**
     * Handle back button click - return to the original item configuration screen
     */
    @FXML
    private void onBackToItemClick() {
        try {
            // Determine which panel to return to
            String panelName = itemType.toLowerCase() + "-panel.fxml";
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/softwaremethproject4/panels/" + panelName));
            Parent itemView = loader.load();

            // Return to the original panel
            Scene currentScene = comboPane.getScene();
            currentScene.setRoot(itemView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}