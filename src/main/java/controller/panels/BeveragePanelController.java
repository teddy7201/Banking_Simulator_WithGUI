package controller.panels;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToggleGroup;

public class BeveragePanelController {
    @FXML
    private ComboBox<String> beverageFlavorCB;

    @FXML
    private ToggleGroup drinkSizeGroup;

    @FXML
    public void initialize() {
        // Initialize combo boxes
        beverageFlavorCB.getItems().addAll("Cola", "Tea", "Juice");
    }

    @FXML
    protected void onAddBeverageToOrderClick() {
        // Add beverage to order logic
        System.out.println("Adding beverage to order");
    }
}