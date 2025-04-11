package controller.panels;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Node;
import java.io.IOException;
import model.*;

/**
 * This class is the controller for the SidePanel.
 * 
 * @author Zeyu weng
 */
public class SidePanelController {
    @FXML
    private ComboBox<String> sideTypeCB;

    @FXML
    private ToggleGroup sideSizeGroup;

    @FXML
    private VBox sidePane;

    @FXML
    private Label sidePriceLabel;

    @FXML
    private Spinner<Integer> sideQuantitySpinner;

    // Size upcharges
    private static final double MEDIUM_UPCHARGE = 0.50;
    private static final double LARGE_UPCHARGE = 1.00;

    /**
     * Initializes the SidePanel
     */
    @FXML
    public void initialize() {
        // Initialize combo boxes
        for (SideType currentSide : SideType.values()) {
            sideTypeCB.getItems().add(currentSide.getSideName());
        }

        // Set up listeners to update price when options change
        sideTypeCB.setOnAction(e -> updatePrice());
        for (Toggle toggle : sideSizeGroup.getToggles()) {
            if (toggle instanceof RadioButton) {
                ((RadioButton) toggle).setOnAction(e -> updatePrice());
            }
        }
        sideQuantitySpinner.setOnMousePressed(e -> updatePrice());
        // Initial price update
        updatePrice();
    }

    /**
     * Update the side price based on selected options
     */
    private void updatePrice() {
        double price = calculateSidePrice();
        sidePriceLabel.setText(String.format("$%.2f", price));
    }

    /**
     * Calculate the current side price
     *
     * @return The total price of the side with selected options
     */
    private double calculateSidePrice() {
        double price = 0.0;

        // Determine base price based on side type
        if (sideTypeCB.getValue() != null) {
            switch (sideTypeCB.getValue()) {
                case "Chips":
                    price = SideType.CHIPS.getPrice();
                    break;
                case "Fries":
                    price = SideType.FRIES.getPrice();
                    break;
                case "Onion Rings":
                    price = SideType.ONION_RINGS.getPrice();
                    break;
                case "Apple Slices":
                    price = SideType.APPLE_SLICES.getPrice();
                    break;
                default:
                    price = 0;
            }
        }

        // Add upcharge for size if applicable
        for (Toggle toggle : sideSizeGroup.getToggles()) {
            RadioButton radioButton = (RadioButton) toggle;
            if (radioButton.isSelected()) {
                if (radioButton.getText().contains("Medium")) {
                    price += MEDIUM_UPCHARGE;
                } else if (radioButton.getText().contains("Large")) {
                    price += LARGE_UPCHARGE;
                }
                break;
            }
        }

        price = price * sideQuantitySpinner.getValue();
        return price;
    }

    /**
     * Create a description of the side configuration
     *
     * @return String description of the side
     */
    private String createSideDescription() {
        StringBuilder description = new StringBuilder();

        // Add side type if selected
        if (sideTypeCB.getValue() != null) {
            description.append(sideTypeCB.getValue()).append(" ");
        } else {
            description.append("Side ");
        }

        // Add size
        for (Toggle toggle : sideSizeGroup.getToggles()) {
            RadioButton radioButton = (RadioButton) toggle;
            if (radioButton.isSelected()) {
                if (radioButton.getText().contains("Small")) {
                    description.append("(Small)");
                } else if (radioButton.getText().contains("Medium")) {
                    description.append("(Medium)");
                } else if (radioButton.getText().contains("Large")) {
                    description.append("(Large)");
                }
                break;
            }
        }

        // Add quantity if more than 1
        if (sideQuantitySpinner.getValue() > 1) {
            description.append(" (").append(sideQuantitySpinner.getValue()).append(")");
        }

        return description.toString();
    }

    /**
     * Adds a side to the order
     */
    @FXML
    protected void onAddSideToOrderClick() {
        if (checkEmptyFields()) {
            createPopUp();
            return;
        }
        try {
            // Determine side type
            SideType selectedSideType;
            if (sideTypeCB.getValue() != null) {
                switch (sideTypeCB.getValue()) {
                    case "Chips":
                        selectedSideType = SideType.CHIPS;
                        break;
                    case "Fries":
                        selectedSideType = SideType.FRIES;
                        break;
                    case "Onion Rings":
                        selectedSideType = SideType.ONION_RINGS;
                        break;
                    case "Apple Slices":
                        selectedSideType = SideType.APPLE_SLICES;
                        break;
                    default:
                        selectedSideType = SideType.CHIPS;
                }
            } else {
                selectedSideType = SideType.CHIPS;
            }

            // Determine size
            Size selectedSize = Size.SMALL;
            for (Toggle toggle : sideSizeGroup.getToggles()) {
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

            // Create the side
            Side side = new Side(selectedSize, selectedSideType, sideQuantitySpinner.getValue());
            OrderManager.getInstance().addItemToOrder(side);

            // Navigate to the current order panel using the main view with sidebar
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/softwaremethproject4/hello-view.fxml"));
            Parent root = loader.load();

            // Get the MainController to set the current order panel as visible
            controller.MainController mainController = loader.getController();
            mainController.navigateToCurrentOrderPanel();

            // Get the scene and set it
            Stage stage = (Stage) ((Node) sidePane).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if the fields are empty
     * 
     * @return True if the fields are empty, false otherwise
     */
    public boolean checkEmptyFields() {
        return sideTypeCB.getValue() == null;
    }

    /**
     * Creates a pop-up
     */
    public void createPopUp() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Missing Data for creating food item.");
        alert.setContentText("Please make sure you fill out all fields.");
        alert.showAndWait();
    }
}