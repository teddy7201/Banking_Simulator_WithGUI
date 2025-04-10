package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.scene.Parent;
import controller.panels.SandwichPanelController;

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

    @FXML
    public void initialize() {
        try {
            // Show the sandwich panel initially
            showSandwichPanel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onSandwichButtonClick() {
        showSandwichPanel();
    }

    @FXML
    protected void onBurgerButtonClick() {
        showBurgerPanel();
    }

    @FXML
    protected void onBeverageButtonClick() {
        showBeveragePanel();
    }

    @FXML
    protected void onSideButtonClick() {
        showSidePanel();
    }

    @FXML
    protected void onCurrentOrderButtonClick() {
        showCurrentOrderPanel();
    }

    @FXML
    protected void onAllOrdersButtonClick() {
        showAllOrdersPanel();
    }

    private void showSandwichPanel() {
        hideAllPanels();
        sandwichPanel.setVisible(true);
    }

    private void showBurgerPanel() {
        hideAllPanels();
        burgerPanel.setVisible(true);
    }

    private void showBeveragePanel() {
        hideAllPanels();
        beveragePanel.setVisible(true);
    }

    private void showSidePanel() {
        hideAllPanels();
        sidePanel.setVisible(true);
    }

    private void showCurrentOrderPanel() {
        hideAllPanels();
        currentOrderPanel.setVisible(true);
    }

    private void showAllOrdersPanel() {
        hideAllPanels();
        allOrdersPanel.setVisible(true);
    }

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