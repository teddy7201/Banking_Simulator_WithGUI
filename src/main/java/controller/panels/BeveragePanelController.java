package controller.panels;

import controller.MainController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.IOException;

// Import the model classes
import model.*;

public class BeveragePanelController {
    @FXML
    private ComboBox<String> beverageFlavorCB;

    @FXML
    private ToggleGroup drinkSizeGroup;

    @FXML
    private VBox beveragePane;

    @FXML
    private Label beveragePriceLabel;

    @FXML
    private Spinner<Integer> beverageQuantitySpinner;

    // Price constants
    private static final double SMALL_PRICE = 1.99;
    private static final double MEDIUM_PRICE = 2.49;
    private static final double LARGE_PRICE = 2.99;

    @FXML
    public void initialize() {
        // Initialize combo boxes
        for(Flavor currentflavor : Flavor.values()){
            beverageFlavorCB.getItems().add(currentflavor.getFlavorName());
        }

        // Set up listeners to update price when options change
        beverageFlavorCB.setOnAction(e -> updatePrice());
        for (Toggle toggle : drinkSizeGroup.getToggles()) {
            if (toggle instanceof RadioButton) {
                ((RadioButton) toggle).setOnAction(e -> updatePrice());
            }
        }
        beverageQuantitySpinner.setOnMousePressed(e -> updatePrice());
        // Initial price update
        updatePrice();
    }

    /**
     * Update the beverage price based on selected options
     */
    private void updatePrice() {
        double price = calculateBeveragePrice();
        beveragePriceLabel.setText(String.format("$%.2f", price));
    }

    /**
     * Calculate the current beverage price
     * 
     * @return The total price of the beverage with selected options
     */
    private double calculateBeveragePrice() {
        double price = 0.0;

        // Determine price based on size
        for (Toggle toggle : drinkSizeGroup.getToggles()) {
            RadioButton radioButton = (RadioButton) toggle;
            if (radioButton.isSelected()) {
                if (radioButton.getText().contains("Small")) {
                    price = SMALL_PRICE;
                } else if (radioButton.getText().contains("Medium")) {
                    price = MEDIUM_PRICE;
                } else if (radioButton.getText().contains("Large")) {
                    price = LARGE_PRICE;
                }
                break;
            }
        }

        price = price * beverageQuantitySpinner.getValue();
        return price;
    }

    @FXML
    protected void onAddBeverageToOrderClick() {
       if(checkEmptyFields()){
           createPopUp();
           return;
       }
        try {
            // Determine flavor type
            Flavor selectedFlavor = null;
            if (beverageFlavorCB.getValue() != null) {
                switch (beverageFlavorCB.getValue()) {
                    case "Cola":
                        selectedFlavor = Flavor.COKE;
                        break;
                    case "Tea":
                        selectedFlavor = Flavor.ICED_TEA;
                        break;
                    case "Juice":
                        selectedFlavor = Flavor.APPLE_JUICE;
                        break;
                    case "Water":
                        selectedFlavor = Flavor.WATER;
                        break;
                    default:
                        selectedFlavor = Flavor.COKE;
                }
            }

            // Determine size
            Size selectedSize = Size.SMALL; // Default
            for (Toggle toggle : drinkSizeGroup.getToggles()) {
                RadioButton radioButton = (RadioButton) toggle;
                if (radioButton.isSelected()) {
                    if (radioButton.getText().contains("Small")) {
                        selectedSize = Size.SMALL;
                    } else if (radioButton.getText().contains("Medium")) {
                        selectedSize = Size.MEDIUM;
                    } else if (radioButton.getText().contains("Large")) {
                        selectedSize = Size.LARGE;
                    }
                    break;
                }
            }

            // Create the beverage
            Beverage beverage = new Beverage(selectedSize, selectedFlavor, beverageQuantitySpinner.getValue());
            OrderManager.getInstance().addItemToOrder(beverage);

            // Navigate to the current order panel using the main view with sidebar
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/softwaremethproject4/hello-view.fxml"));
            Parent root = loader.load();

            // Get the MainController to set the current order panel as visible
            MainController mainController = loader.getController();
            mainController.navigateToCurrentOrderPanel();

            // Get the scene and set it
            Stage stage = (Stage) ((Node) beveragePane).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean checkEmptyFields(){
        return beverageFlavorCB.getValue() == null;
    }

    public void createPopUp(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Missing Data for creating food item.");
        alert.setContentText("Please make sure you fill out all fields.");
        alert.showAndWait();
    }
}