package controller.panels;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

// Import the model classes
import model.*;

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


    @FXML
    public void initialize() {
        // Initialize combo boxes
        for(Bread currentBread : Bread.values()){
            sandwichBreadOptionCB.getItems().add(currentBread.getBreadType());
        }
        sandwichProteinCB.getItems().addAll("Roast Beef", "Salmon", "Chicken");

        // Set up listeners to update price when options change
        sandwichBreadOptionCB.setOnAction(e -> updatePrice());
        sandwichProteinCB.setOnAction(e -> updatePrice());
        lettuceCB.setOnAction(e -> updatePrice());
        tomatoesCB.setOnAction(e -> updatePrice());
        onionsCB.setOnAction(e -> updatePrice());
        avocadoCB.setOnAction(e -> updatePrice());
        cheeseCB.setOnAction(e -> updatePrice());
        sandwichQuantitySpinner.setOnMousePressed(e -> updatePrice());
        // Initial price update
        updatePrice();
    }

    /**
     * Update the sandwich price based on selected options
     */
    private void updatePrice() {
        double price = 0;
        if(Objects.equals(sandwichProteinCB.getValue(), Protein.CHICKEN.getProteinType())){
            price += Protein.CHICKEN.getPrice();
        }
        else if(Objects.equals(sandwichProteinCB.getValue(), Protein.ROAST_BEEF.getProteinType())){
            price += Protein.ROAST_BEEF.getPrice();
        }
        else if(Objects.equals(sandwichProteinCB.getValue(), Protein.SALMON.getProteinType())){
            price += Protein.SALMON.getPrice();
        }
        else{
            price = 0;
        }


        // Add costs for selected add-ons
        if (lettuceCB.isSelected())
            price += Addons.LETTUCE.getPrice();
        if (tomatoesCB.isSelected())
            price += Addons.TOMATOES.getPrice();
        if (onionsCB.isSelected())
            price += Addons.ONIONS.getPrice();
        if (avocadoCB.isSelected())
            price += Addons.AVOCADO.getPrice();
        if (cheeseCB.isSelected())
            price += Addons.CHEESE.getPrice();

        price *= sandwichQuantitySpinner.getValue();
        // Update the displayed price
        sandwichPriceLabel.setText(String.format("$%.2f", price));
    }

    /**
     * Calculate the current sandwich price
     * 
     * @return The total price of the sandwich with selected options
     */
    private double calculateSandwichPrice() {
        double price = 0;
        if(Objects.equals(sandwichProteinCB.getValue(), Protein.CHICKEN.getProteinType())){
            price += Protein.CHICKEN.getPrice();
        }
        else if(Objects.equals(sandwichProteinCB.getValue(), Protein.ROAST_BEEF.getProteinType())){
            price += Protein.ROAST_BEEF.getPrice();
        }
        else if(Objects.equals(sandwichProteinCB.getValue(), Protein.SALMON.getProteinType())){
            price += Protein.SALMON.getPrice();
        }
        else{
            price = 0;
        }


        // Add costs for selected add-ons
        if (lettuceCB.isSelected())
            price += Addons.LETTUCE.getPrice();
        if (tomatoesCB.isSelected())
            price += Addons.TOMATOES.getPrice();
        if (onionsCB.isSelected())
            price += Addons.ONIONS.getPrice();
        if (avocadoCB.isSelected())
            price += Addons.AVOCADO.getPrice();
        if (cheeseCB.isSelected())
            price += Addons.CHEESE.getPrice();

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
        if(checkEmptyFields()){
            createPopUp();
            return;
        }
        try {
            // Determine bread type
            Bread selectedBread;
            if (sandwichBreadOptionCB.getValue() != null) {
                switch (sandwichBreadOptionCB.getValue()) {
                    case "Brioche":
                        selectedBread = Bread.BRIOCHE;
                        break;
                    case "Wheat Bread":
                        selectedBread = Bread.WHEAT_BREAD;
                        break;
                    case "Pretzel":
                        selectedBread = Bread.PRETZEL;
                        break;
                    case "Sourdough":
                        selectedBread = Bread.SOURDOUGH;
                        break;
                    case  "Bagel":
                        selectedBread = Bread.BAGEL;
                    default:
                        selectedBread = Bread.BRIOCHE;
                }
            } else {
                selectedBread = Bread.BRIOCHE;
            }

            // Determine protein type
            Protein selectedProtein;
            if (sandwichProteinCB.getValue() != null) {
                switch (sandwichProteinCB.getValue()) {
                    case "Roast Beef":
                        selectedProtein = Protein.ROAST_BEEF;
                        break;
                    case "Salmon":
                        selectedProtein = Protein.SALMON;
                        break;
                    case "Chicken":
                        selectedProtein = Protein.CHICKEN;
                        break;
                    default:
                        selectedProtein = Protein.CHICKEN;
                }
            } else {
                selectedProtein = Protein.CHICKEN;
            }

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

            // Create the sandwich
            Sandwich sandwich = new Sandwich(selectedBread, selectedProtein, addons, sandwichQuantitySpinner.getValue());

            // Add to the order manager
            OrderManager.getInstance().addItemToOrder(sandwich);

            // Navigate to the current order panel using the main view with sidebar
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/softwaremethproject4/hello-view.fxml"));
            Parent root = loader.load();

            // Get the MainController to set the current order panel as visible
            controller.MainController mainController = loader.getController();
            mainController.navigateToCurrentOrderPanel();

            // Get the scene and set it
            Stage stage = (Stage) ((Node) sandwichPane).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onMakeComboClick() {
        if(checkEmptyFields()){
            createPopUp();
            return;
        }
        try {
            // Create the sandwich first
            Bread selectedBread = sandwichBreadOptionCB.getValue() != null
                    ? Bread.valueOf(sandwichBreadOptionCB.getValue().toUpperCase().replace(" ", "_"))
                    : Bread.BRIOCHE;

            Protein selectedProtein = sandwichProteinCB.getValue() != null
                    ? Protein.valueOf(sandwichProteinCB.getValue().toUpperCase().replace(" ", "_"))
                    : Protein.CHICKEN;

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

            Sandwich sandwich = new Sandwich(selectedBread, selectedProtein, addons, sandwichQuantitySpinner.getValue());

            // Load the combo panel with sidebar navigation
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/softwaremethproject4/panels/combo-panel.fxml"));
            Parent root = loader.load();

            // Initialize the combo panel with sandwich details
            ComboPanelController comboController = loader.getController();
            double basePrice = calculateSandwichPrice();
            comboController.initializeWithItem("Sandwich", sandwich, basePrice, createSandwichDescription());

            // Switch to the combo scene
            Stage stage = (Stage) ((Node) sandwichPane).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean checkEmptyFields(){
        return sandwichBreadOptionCB.getValue() == null || sandwichProteinCB.getValue() == null;
    }

    public void createPopUp(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Missing Data for creating food item.");
        alert.setContentText("Please make sure you fill out all fields.");
        alert.showAndWait();
    }
}