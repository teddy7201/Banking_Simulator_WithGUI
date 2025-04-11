package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.scene.Parent;
import controller.panels.SandwichPanelController;

/**
 * This class is the controller for the MainController.
 * 
 * @author Zeyu weng
 */
public class MainController {
    // Panel references from fx:include
    @FXML
    private VBox sandwichPanel;

    @FXML
    private VBox burgerPanel;

    @FXML
    private VBox beveragePanel;

    @FXML
    private VBox sidePanel;

    @FXML
    private VBox currentOrderPanel;

    @FXML
    private VBox allOrdersPanel;

    private SandwichPanelController sandwichController;

    /**
     * Initializes the MainController
     */
    @FXML
    public void initialize() {
        try {
            // Show the sandwich panel initially
            showSandwichPanel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the sandwich panel
     */
    @FXML
    protected void onSandwichButtonClick() {
        showSandwichPanel();
    }

    /**
     * Shows the burger panel
     */
    @FXML
    protected void onBurgerButtonClick() {
        showBurgerPanel();
    }

    /**
     * Shows the beverage panel
     */
    @FXML
    protected void onBeverageButtonClick() {
        showBeveragePanel();
    }

    /**
     * Shows the side panel
     */
    @FXML
    protected void onSideButtonClick() {
        showSidePanel();
    }

    /**
     * Shows the current order panel
     */
    @FXML
    protected void onCurrentOrderButtonClick() {
        showCurrentOrderPanel();
    }

    /**
     * Shows the all orders panel
     */
    @FXML
    protected void onAllOrdersButtonClick() {
        showAllOrdersPanel();
    }

    /**
     * Shows the sandwich panel
     */
    private void showSandwichPanel() {
        hideAllPanels();
        sandwichPanel.setVisible(true);
    }

    /**
     * Shows the burger panel
     */
    private void showBurgerPanel() {
        hideAllPanels();
        burgerPanel.setVisible(true);
    }

    /**
     * Shows the beverage panel
     */
    private void showBeveragePanel() {
        hideAllPanels();
        beveragePanel.setVisible(true);
    }

    /**
     * Shows the side panel
     */
    private void showSidePanel() {
        hideAllPanels();
        sidePanel.setVisible(true);
    }

    /**
     * Shows the current order panel
     */
    private void showCurrentOrderPanel() {
        hideAllPanels();
        currentOrderPanel.setVisible(true);
    }

    /**
     * Shows the all orders panel
     */
    private void showAllOrdersPanel() {
        hideAllPanels();
        allOrdersPanel.setVisible(true);
    }

    /**
     * Hides all panels
     */
    private void hideAllPanels() {
        sandwichPanel.setVisible(false);
        burgerPanel.setVisible(false);
        beveragePanel.setVisible(false);
        sidePanel.setVisible(false);
        currentOrderPanel.setVisible(false);
        allOrdersPanel.setVisible(false);
    }

    /**
     * Public method to show sandwich panel that can be called from other
     * controllers
     */
    public void navigateToSandwichPanel() {
        showSandwichPanel();
    }

    /**
     * Public method to show current order panel that can be called from other
     * controllers
     */
    public void navigateToCurrentOrderPanel() {
        showCurrentOrderPanel();
    }

}