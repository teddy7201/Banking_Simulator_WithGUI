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
import model.Addons;
import model.Bread;
import model.Protein;
import model.Sandwich;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

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
        sandwichBreadOptionCB.getItems().addAll("Brioche", "Wheat Bread", "Pretzel", "Sourdough", "Bagel");
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
        double price = 0;
        if(Objects.equals(sandwichProteinCB.getValue(), Protein.CHICKEN.getProteinType())){
            price += Protein.CHICKEN.getPrice();
        }
        else if(Objects.equals(sandwichProteinCB.getValue(), Protein.SALMON.getProteinType())){
            price += Protein.SALMON.getPrice();
        }
        else if(Objects.equals(sandwichProteinCB.getValue(), Protein.ROAST_BEEF.getProteinType())){
            price += Protein.ROAST_BEEF.getPrice();
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
        else if(Objects.equals(sandwichProteinCB.getValue(), Protein.SALMON.getProteinType())){
            price += Protein.SALMON.getPrice();
        }
        else if(Objects.equals(sandwichProteinCB.getValue(), Protein.ROAST_BEEF.getProteinType())){
            price += Protein.ROAST_BEEF.getPrice();
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
    protected void onAddSandwichToOrderClick() throws IOException {
        Sandwich addingSanwich = createSandwich();
        //System.out.println(addingSanwich.toString());

        URL url = getClass().getResource("/com/softwaremethproject4/panels/current-order-panel.fxml");
        FXMLLoader loader = new FXMLLoader(url);

        CurrentOrderPanelController controller = loader.getController();

        for(int i = 0; i < sandwichQuantitySpinner.getValue(); i++){
            controller.addToCurrentOrder(addingSanwich);
        }


//        System.out.println(fxmlResource.toString());
    }

    private Sandwich createSandwich(){
        Bread bread = null;
        if(Objects.equals(sandwichBreadOptionCB.getValue(), Bread.WHEAT_BREAD.getBreadType())){
            bread = Bread.WHEAT_BREAD;
        }
        else if(Objects.equals(sandwichBreadOptionCB.getValue(), Bread.BRIOCHE.getBreadType())){
            bread = Bread.BRIOCHE;
        }
        else if(Objects.equals(sandwichBreadOptionCB.getValue(), Bread.BAGEL.getBreadType())){
            bread = Bread.BAGEL;
        }
        else if(Objects.equals(sandwichBreadOptionCB.getValue(), Bread.SOURDOUGH.getBreadType())){
            bread = Bread.SOURDOUGH;
        }
        else if(Objects.equals(sandwichBreadOptionCB.getValue(), Bread.PRETZEL.getBreadType())){
            bread = Bread.PRETZEL;
        }

        Protein protein = null;
        if(Objects.equals(sandwichProteinCB.getValue(), Protein.CHICKEN.getProteinType())){
            protein = Protein.CHICKEN;
        }
        else if(Objects.equals(sandwichProteinCB.getValue(), Protein.SALMON.getProteinType())){
            protein = Protein.SALMON;
        }
        else if(Objects.equals(sandwichProteinCB.getValue(), Protein.ROAST_BEEF.getProteinType())){
            protein = Protein.ROAST_BEEF;
        }

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

        Sandwich newSandwich = new Sandwich(bread, protein, addons);
        return newSandwich;
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