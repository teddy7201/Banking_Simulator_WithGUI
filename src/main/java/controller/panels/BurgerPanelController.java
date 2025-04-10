package controller.panels;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.IOException;
import java.util.ArrayList;

// Import the model classes
import model.*;

public class BurgerPanelController {
    @FXML
    private ComboBox<String> burgerBreadCB;

    @FXML
    private ImageView burgerImageView;

    @FXML
    private VBox burgerPane;

    @FXML
    private Label burgerPriceLabel;

    @FXML
    private Spinner<Integer> burgerQuantitySpinner;

    @FXML
    private RadioButton singlePattyRB;

    @FXML
    private RadioButton doublePattyRB;

    @FXML
    private ToggleGroup burgerPattyGroup;

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

    // Base price for a burger
    private static final double BASE_BURGER_PRICE = 7.99;
    private static final double DOUBLE_PATTY_UPCHARGE = 2.50;

    // Add-on prices
    private static final double LETTUCE_PRICE = 0.30;
    private static final double TOMATOES_PRICE = 0.30;
    private static final double ONIONS_PRICE = 0.30;
    private static final double AVOCADO_PRICE = 0.50;
    private static final double CHEESE_PRICE = 1.00;

    @FXML
    public void initialize() {
        // Initialize combo boxes
        burgerBreadCB.getItems().addAll("Brioche", "Wheat Bread", "Pretzel");

        // Set up listeners to update price when options change
        burgerBreadCB.setOnAction(e -> updatePrice());
        singlePattyRB.setOnAction(e -> updatePrice());
        doublePattyRB.setOnAction(e -> updatePrice());
        lettuceCB.setOnAction(e -> updatePrice());
        tomatoesCB.setOnAction(e -> updatePrice());
        onionsCB.setOnAction(e -> updatePrice());
        avocadoCB.setOnAction(e -> updatePrice());
        cheeseCB.setOnAction(e -> updatePrice());

        // Initial price update
        updatePrice();
    }

    /**
     * Update the burger price based on selected options
     */
    private void updatePrice() {
        double price = BASE_BURGER_PRICE;

        // Add cost for double patty if selected
        if (doublePattyRB.isSelected()) {
            price += DOUBLE_PATTY_UPCHARGE;
        }

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
        burgerPriceLabel.setText(String.format("$%.2f", price));
    }

    /**
     * Calculate the current burger price
     * 
     * @return The total price of the burger with selected options
     */
    private double calculateBurgerPrice() {
        double price = BASE_BURGER_PRICE;

        // Add cost for double patty if selected
        if (doublePattyRB.isSelected()) {
            price += DOUBLE_PATTY_UPCHARGE;
        }

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
        price *= burgerQuantitySpinner.getValue();

        return price;
    }

    /**
     * Create a description of the burger configuration
     * 
     * @return String description of the burger
     */
    private String createBurgerDescription() {
        StringBuilder description = new StringBuilder();

        // Add bread type if selected
        if (burgerBreadCB.getValue() != null) {
            description.append(burgerBreadCB.getValue()).append(" ");
        }

        // Add patty type
        if (doublePattyRB.isSelected()) {
            description.append("Double ");
        } else {
            description.append("Single ");
        }

        description.append("Burger");

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
        if (burgerQuantitySpinner.getValue() > 1) {
            description.append(" (").append(burgerQuantitySpinner.getValue()).append(")");
        }

        return description.toString();
    }

    @FXML
    protected void onAddBurgerToOrderClick() {
        try {
            // Determine bread type
            Bread selectedBread;
            if (burgerBreadCB.getValue() != null) {
                switch (burgerBreadCB.getValue()) {
                    case "Brioche":
                        selectedBread = Bread.BRIOCHE;
                        break;
                    case "Wheat Bread":
                        selectedBread = Bread.WHEAT_BREAD;
                        break;
                    case "Pretzel":
                        selectedBread = Bread.PRETZEL;
                        break;
                    default:
                        selectedBread = Bread.BRIOCHE;
                }
            } else {
                selectedBread = Bread.BRIOCHE;
            }

            // Protein is always beef patty for burgers
            Protein selectedProtein = Protein.BEEF_PATTY;

            // Create list of addons
            ArrayList<Addons> addons = new ArrayList<>();
            if (lettuceCB.isSelected())
                addons.add(Addons.LETTUCE);
            if (tomatoesCB.isSelected())
                addons.add(Addons.TOMATOES);
            if (onionsCB.isSelected())
                addons.add(Addons.ONIONS);
            if (avocadoCB.isSelected())
                addons.add(Addons.AVOCADO);
            if (cheeseCB.isSelected())
                addons.add(Addons.CHEESE);

            // Determine if double patty is selected
            boolean isDoublePatty = doublePattyRB.isSelected();

            // Create the burger
            Burger burger = new Burger(selectedBread, selectedProtein, addons, isDoublePatty);

            // Set quantity if more than 1
            if (burgerQuantitySpinner.getValue() > 1) {
                // Multiple burgers will be added as separate items
                for (int i = 0; i < burgerQuantitySpinner.getValue(); i++) {
                    OrderManager.getInstance().addItemToOrder(burger);
                }
            } else {
                // Add to the order manager
                OrderManager.getInstance().addItemToOrder(burger);
            }

            // Navigate to the current order panel using the main view with sidebar
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/softwaremethproject4/hello-view.fxml"));
            Parent root = loader.load();

            // Get the MainController to set the current order panel as visible
            controller.MainController mainController = loader.getController();
            mainController.navigateToCurrentOrderPanel();

            // Get the scene and set it
            Stage stage = (Stage) ((Node) burgerPane).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onMakeComboClick() {
        try {
            // Create the burger first
            Bread selectedBread;
            if (burgerBreadCB.getValue() != null) {
                switch (burgerBreadCB.getValue()) {
                    case "Brioche":
                        selectedBread = Bread.BRIOCHE;
                        break;
                    case "Wheat Bread":
                        selectedBread = Bread.WHEAT_BREAD;
                        break;
                    case "Pretzel":
                        selectedBread = Bread.PRETZEL;
                        break;
                    default:
                        selectedBread = Bread.BRIOCHE;
                }
            } else {
                selectedBread = Bread.BRIOCHE;
            }

            // Protein is always beef patty for burgers
            Protein selectedProtein = Protein.BEEF_PATTY;

            // Create list of addons
            ArrayList<Addons> addons = new ArrayList<>();
            if (lettuceCB.isSelected())
                addons.add(Addons.LETTUCE);
            if (tomatoesCB.isSelected())
                addons.add(Addons.TOMATOES);
            if (onionsCB.isSelected())
                addons.add(Addons.ONIONS);
            if (avocadoCB.isSelected())
                addons.add(Addons.AVOCADO);
            if (cheeseCB.isSelected())
                addons.add(Addons.CHEESE);

            // Determine if double patty is selected
            boolean isDoublePatty = doublePattyRB.isSelected();

            // Create the burger
            Burger burger = new Burger(selectedBread, selectedProtein, addons, isDoublePatty);

            // Load the combo panel and get its controller
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/softwaremethproject4/panels/combo-panel.fxml"));
            Parent root = loader.load();

            // Initialize the combo panel with burger details
            ComboPanelController comboController = loader.getController();
            double basePrice = calculateBurgerPrice();
            comboController.initializeWithItem("Burger", burger, basePrice, createBurgerDescription());

            // Switch to the combo scene
            Stage stage = (Stage) ((Node) burgerPane).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}