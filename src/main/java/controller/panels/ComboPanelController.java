package controller.panels;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.IOException;

// Import the model classes
import model.*;

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

    @FXML
    private ToggleGroup drinkSizeGroup;

    @FXML
    private ToggleGroup sideSizeGroup;

    private String itemType;
    private Object originalItem;
    private double basePrice;
    private static final double COMBO_UPCHARGE = 3.00;

    /**
     * Initialize the controller
     */
    @FXML
    public void initialize() {
        if (comboSideTypeCB.getItems().isEmpty()) {
            comboSideTypeCB.getItems().addAll(SideType.CHIPS.getSideName(), SideType.APPLE_SLICES.getSideName(),
                    SideType.FRIES.getSideName());
        }

        if (comboDrinkTypeCB.getItems().isEmpty()) {
            comboDrinkTypeCB.getItems().addAll(Flavor.COKE.getFlavorName(), Flavor.ICED_TEA.getFlavorName(),
                    Flavor.APPLE_JUICE.getFlavorName(), Flavor.WATER.getFlavorName());
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

        itemTypeLabel.setText("Item: " + itemType);
        itemDetailsLabel.setText(itemDetails);
        baseItemPriceLabel.setText(String.format("$%.2f", basePrice));

        // Update image based on item type
        String imagePath = "/com/softwaremethproject4/images/" + itemType.toLowerCase() + ".png";
        comboImageView.setImage(new Image(getClass().getResourceAsStream(imagePath)));

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
        if(checkEmptyFields()){
            createPopUp();
            return;
        }
        try {
            Flavor selectedFlavor;
            if (comboDrinkTypeCB.getValue() != null) {
                switch (comboDrinkTypeCB.getValue()) {
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
            } else {
                selectedFlavor = Flavor.COKE;
            }

            // MEDIUM for combo
            Size drinkSize = Size.MEDIUM;
            if (drinkSizeGroup != null) {
                for (Toggle toggle : drinkSizeGroup.getToggles()) {
                    if (toggle instanceof RadioButton && ((RadioButton) toggle).isSelected()) {
                        RadioButton rb = (RadioButton) toggle;
                        if (rb.getText().contains("Small")) {
                            drinkSize = Size.SMALL;
                        } else if (rb.getText().contains("Large")) {
                            drinkSize = Size.LARGE;
                        }
                        break;
                    }
                }
            }

            Beverage beverage = new Beverage(drinkSize, selectedFlavor, 1);

            // Create the side based on selection
            SideType selectedSideType;
            if (comboSideTypeCB.getValue() != null) {
                switch (comboSideTypeCB.getValue()) {
                    case "Chips":
                        selectedSideType = SideType.CHIPS;
                        break;
                    case "French Fries":
                    case "Fries":
                        selectedSideType = SideType.FRIES;
                        break;
                    case "Apple":
                    case "Apple Slices":
                        selectedSideType = SideType.APPLE_SLICES;
                        break;
                    default:
                        selectedSideType = SideType.CHIPS;
                }
            } else {
                selectedSideType = SideType.CHIPS;
            }

            // default to MEDIUM for combo
            Size sideSize = Size.MEDIUM;
            if (sideSizeGroup != null) {
                for (Toggle toggle : sideSizeGroup.getToggles()) {
                    if (toggle instanceof RadioButton && ((RadioButton) toggle).isSelected()) {
                        RadioButton rb = (RadioButton) toggle;
                        if (rb.getText().contains("Small")) {
                            sideSize = Size.SMALL;
                        } else if (rb.getText().contains("Large")) {
                            sideSize = Size.LARGE;
                        }
                        break;
                    }
                }
            }

            Side side = new Side(sideSize, selectedSideType, 1);

            // Create combo with original item, beverage, and side
            Combo combo;
            if (originalItem instanceof Sandwich) {
                combo = new Combo((Sandwich) originalItem, beverage, side, 1);
            } else if (originalItem instanceof Burger) {
                // Need to wrap Burger in a Combo
                Sandwich burger = (Sandwich) originalItem; // Burger extends Sandwich
                combo = new Combo(burger, beverage, side, 1);
            } else {
                // Fallback case - should not happen if panels are working properly
                throw new IllegalStateException("Unknown original item type in combo: " + itemType);
            }

            // Add to order manager
            OrderManager.getInstance().addItemToOrder(combo);

            // Navigate to the current order panel using the main view with sidebar
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/softwaremethproject4/hello-view.fxml"));
            Parent root = loader.load();

            // Get the MainController to set the current order panel as visible
            controller.MainController mainController = loader.getController();
            mainController.navigateToCurrentOrderPanel();

            // Get the scene and set it
            Stage stage = (Stage) ((Node) comboPane).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handle back button click - return to the original item configuration screen
     */
    @FXML
    private void onBackToItemClick() {
        try {
            Parent root = FXMLLoader.load(getClass()
                    .getResource("/com/softwaremethproject4/panels/" + itemType.toLowerCase() + "-panel.fxml"));
            Stage stage = (Stage) ((Node) comboPane).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean checkEmptyFields(){
        return comboSideTypeCB.getValue() == null || comboDrinkTypeCB.getValue() == null;
    }

    public void createPopUp(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Missing Data for creating food item.");
        alert.setContentText("Please make sure you fill out all fields.");
        alert.showAndWait();
    }
}