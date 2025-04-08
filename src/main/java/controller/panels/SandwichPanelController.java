package controller.panels;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class SandwichPanelController {
    @FXML
    private ComboBox<String> sandwichBreadOptionCB;

    @FXML
    private ComboBox<String> sandwichProteinCB;

    @FXML
    private ImageView sandwichImageView;

    @FXML
    private VBox sandwichPane;

    @FXML
    private Label sandwichPriceLabel;

    @FXML
    private Spinner<Integer> sandwichQuantitySpinner;

    @FXML
    private CheckBox lettuceCB;

    @FXML
    private CheckBox tomatoesCB;

    @FXML
    private CheckBox onionsCB;

    @FXML
    private CheckBox avocadoCB;

    @FXML
    private CheckBox cheeseCB;

    // Base price for a sandwich
    private static final double BASE_SANDWICH_PRICE = 8.99;

    // Add-on prices
    private static final double LETTUCE_PRICE = 0.30;
    private static final double TOMATOES_PRICE = 0.30;
    private static final double ONIONS_PRICE = 0.30;
    private static final double AVOCADO_PRICE = 0.50;
    private static final double CHEESE_PRICE = 1.00;

    @FXML
    public void initialize() {
        // Initialize combo boxes
        sandwichBreadOptionCB.getItems().addAll("Brioche", "Wheat Bread", "Pretzel", "Sourdough");
        sandwichProteinCB.getItems().addAll("Roast Beef", "Salmon", "Chicken");

        // Set up listeners to update price when options change
        sandwichBreadOptionCB.setOnAction(e -> updatePrice());
        sandwichProteinCB.setOnAction(e -> updatePrice());
        lettuceCB.setOnAction(e -> updatePrice());
        tomatoesCB.setOnAction(e -> updatePrice());
        onionsCB.setOnAction(e -> updatePrice());
        avocadoCB.setOnAction(e -> updatePrice());
        cheeseCB.setOnAction(e -> updatePrice());

        // Initial price update
        updatePrice();
    }

    /**
     * Update the sandwich price based on selected options
     */
    private void updatePrice() {
        double price = BASE_SANDWICH_PRICE;

        // Add costs for selected add-ons
        if (lettuceCB.isSelected())
            price += LETTUCE_PRICE;
        if (tomatoesCB.isSelected())
            price += TOMATOES_PRICE;
        if (onionsCB.isSelected())
            price += ONIONS_PRICE;
        if (avocadoCB.isSelected())
            price += AVOCADO_PRICE;
        if (cheeseCB.isSelected())
            price += CHEESE_PRICE;

        // Update the displayed price
        sandwichPriceLabel.setText(String.format("$%.2f", price));
    }

    /**
     * Calculate the current sandwich price
     * 
     * @return The total price of the sandwich with selected options
     */
    private double calculateSandwichPrice() {
        double price = BASE_SANDWICH_PRICE;

        // Add costs for selected add-ons
        if (lettuceCB.isSelected())
            price += LETTUCE_PRICE;
        if (tomatoesCB.isSelected())
            price += TOMATOES_PRICE;
        if (onionsCB.isSelected())
            price += ONIONS_PRICE;
        if (avocadoCB.isSelected())
            price += AVOCADO_PRICE;
        if (cheeseCB.isSelected())
            price += CHEESE_PRICE;

        // Multiply by quantity
        price *= sandwichQuantitySpinner.getValue();

        return price;
    }

    /**
     * Create a description of the sandwich configuration
     * 
     * @return String description of the sandwich
     */
    private String createSandwichDescription() {
        StringBuilder description = new StringBuilder();

        // Add bread type if selected
        if (sandwichBreadOptionCB.getValue() != null) {
            description.append(sandwichBreadOptionCB.getValue()).append(" bread, ");
        }

        // Add protein type if selected
        if (sandwichProteinCB.getValue() != null) {
            description.append(sandwichProteinCB.getValue()).append(" sandwich");
        } else {
            description.append("Custom sandwich");
        }

        // Add toppings
        if (lettuceCB.isSelected() || tomatoesCB.isSelected() || onionsCB.isSelected()
                || avocadoCB.isSelected() || cheeseCB.isSelected()) {
            description.append(" with ");

            if (lettuceCB.isSelected())
                description.append("lettuce, ");
            if (tomatoesCB.isSelected())
                description.append("tomatoes, ");
            if (onionsCB.isSelected())
                description.append("onions, ");
            if (avocadoCB.isSelected())
                description.append("avocado, ");
            if (cheeseCB.isSelected())
                description.append("cheese, ");

            // Remove trailing comma and space
            description.setLength(description.length() - 2);
        }

        // Add quantity if more than 1
        if (sandwichQuantitySpinner.getValue() > 1) {
            description.append(" (").append(sandwichQuantitySpinner.getValue()).append(")");
        }

        return description.toString();
    }

    @FXML
    protected void onAddSandwichToOrderClick() {
        // Add sandwich to order logic
        System.out.println("Adding sandwich to order: " + createSandwichDescription());
    }

    @FXML
    protected void onMakeComboClick() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/softwaremethproject4/panels/combo-panel.fxml"));
            Parent comboView = loader.load();

            // Get the controller and initialize it with sandwich details
            ComboPanelController comboController = loader.getController();
            double basePrice = calculateSandwichPrice();
            comboController.initializeWithItem("Sandwich", null, basePrice, createSandwichDescription());

            // Get the current scene and set the new content
            Scene currentScene = sandwichPane.getScene();
            currentScene.setRoot(comboView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}