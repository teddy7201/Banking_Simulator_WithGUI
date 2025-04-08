package controller.panels;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToggleGroup;

public class SidePanelController {
    @FXML
    private ComboBox<String> sideTypeCB;

    @FXML
    private ToggleGroup sideSizeGroup;

    @FXML
    public void initialize() {
        // Initialize combo boxes
        sideTypeCB.getItems().addAll("Chips", "Fries", "Onion rings", "Apple Slices");
    }

    @FXML
    protected void onAddSideToOrderClick() {
        // Add side to order logic
        System.out.println("Adding side to order");
    }
}