package controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private ComboBox<String> sandwichBreadOptionCB;

    @FXML
    private ComboBox<String> sandwichProteinCB;

    @FXML
    private ComboBox<String> burgerBreadCB;

    @FXML
    private ComboBox<String> BeverageFlavorCB;

    @FXML
    private ComboBox<String> SideTypeCB;

    @FXML
    private ComboBox<String> burgerComboSideTypeCB;

    @FXML
    private ComboBox<String> burgerComboDrinkTypeCB;

    @FXML
    private ComboBox<String> sandwichComboSideTypeCB;

    @FXML
    private ComboBox<String> sandwichComboDrinkTypeCB;

    @FXML
    private VBox sandwichPane;

    @FXML
    private VBox burgerPane;

    @FXML
    private VBox beveragePane;

    @FXML
    private VBox sidePane;

    @FXML
    private VBox currentOrderPane;

    @FXML
    private VBox allOrdersPane;

    // New FXML fields for combo options in sandwich pane
    @FXML
    private CheckBox sandwichMakeComboCB;

    @FXML
    private VBox sandwichComboExtras;

    // New FXML fields for combo options in burger pane
    @FXML
    private CheckBox burgerMakeComboCB;

    @FXML
    private VBox burgerComboExtras;

    @FXML
    public void initialize() {
        sandwichBreadOptionCB.getItems().addAll("Brioche", "Wheat Bread", "Pretzel", "Sourdough");
        burgerBreadCB.getItems().addAll("Brioche", "Wheat Bread", "Pretzel");
        sandwichProteinCB.getItems().addAll("Roast Beef", "Salmon", "Chicken");
        BeverageFlavorCB.getItems().addAll("Cola", "Tea", "Juice");
        SideTypeCB.getItems().addAll("Chips", "Fries", "Onion rings", "Apple Slices");
        burgerComboSideTypeCB.getItems().addAll("Chips", "Apple");
        burgerComboDrinkTypeCB.getItems().addAll("Cola", "Tea", "Juice");
        sandwichComboSideTypeCB.getItems().addAll("Chips", "Apple");
        sandwichComboDrinkTypeCB.getItems().addAll("Cola", "Tea", "Juice");
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void onSandwichButtonClick() {
        // Hide all panes
        sandwichPane.setVisible(false);
        burgerPane.setVisible(false);
        beveragePane.setVisible(false);
        sidePane.setVisible(false);
        currentOrderPane.setVisible(false);
        allOrdersPane.setVisible(false);

        // Show sandwich pane
        sandwichPane.setVisible(true);
    }

    @FXML
    protected void onBurgerButtonClick() {
        // Hide all panes
        sandwichPane.setVisible(false);
        burgerPane.setVisible(false);
        beveragePane.setVisible(false);
        sidePane.setVisible(false);
        currentOrderPane.setVisible(false);
        allOrdersPane.setVisible(false);

        // Show burger pane
        burgerPane.setVisible(true);
    }

    @FXML
    protected void onBeverageButtonClick() {
        // Hide all panes
        sandwichPane.setVisible(false);
        burgerPane.setVisible(false);
        beveragePane.setVisible(false);
        sidePane.setVisible(false);
        currentOrderPane.setVisible(false);
        allOrdersPane.setVisible(false);

        // Show beverage pane
        beveragePane.setVisible(true);
    }

    @FXML
    protected void onSideButtonClick() {
        // Hide all panes
        sandwichPane.setVisible(false);
        burgerPane.setVisible(false);
        beveragePane.setVisible(false);
        sidePane.setVisible(false);
        currentOrderPane.setVisible(false);
        allOrdersPane.setVisible(false);

        // Show side pane
        sidePane.setVisible(true);
    }

    @FXML
    protected void onCurrentOrderButtonClick() {
        // Hide all panes
        sandwichPane.setVisible(false);
        burgerPane.setVisible(false);
        beveragePane.setVisible(false);
        sidePane.setVisible(false);
        currentOrderPane.setVisible(false);
        allOrdersPane.setVisible(false);

        // Show current order pane
        currentOrderPane.setVisible(true);
    }

    @FXML
    protected void onAllOrdersButtonClick() {
        // Hide all panes
        sandwichPane.setVisible(false);
        burgerPane.setVisible(false);
        beveragePane.setVisible(false);
        sidePane.setVisible(false);
        currentOrderPane.setVisible(false);
        allOrdersPane.setVisible(false);

        // Show all orders pane
        allOrdersPane.setVisible(true);
    }

    // New event handler for sandwich combo checkbox
    @FXML
    protected void onSandwichMakeComboAction() {
        sandwichComboExtras.setVisible(sandwichMakeComboCB.isSelected());
    }

    // New event handler for burger combo checkbox
    @FXML
    protected void onBurgerMakeComboAction() {
        burgerComboExtras.setVisible(burgerMakeComboCB.isSelected());
    }
}