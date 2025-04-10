package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

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

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void initialize() {
        // Show the sandwich panel initially
        showSandwichPanel();
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

    private void showAllOrdersPanel(){
        hideAllPanels();
        allOrdersPanel.setVisible(true);
//        root = FXMLLoader.load(getClass().getResource("all-orders-panel.fxml"));
//        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
    }

    private void hideAllPanels() {
        sandwichPanel.setVisible(false);
        burgerPanel.setVisible(false);
        beveragePanel.setVisible(false);
        sidePanel.setVisible(false);
        currentOrderPanel.setVisible(false);
        allOrdersPanel.setVisible(false);
    }
}