package controller.panels;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import model.*;

import java.util.ArrayList;

public class CurrentOrderPanelController {
    @FXML
    private ListView<String> orderItemsListView;

    @FXML
    private Label subtotalLabel;

    @FXML
    private Label taxLabel;

    @FXML
    private Label totalLabel;

    private Order currentOrder;

    @FXML
    public void initialize() {
        currentOrder = new Order();

        populateListView();
        // Update price labels
        updatePriceLabels();
    }


    public void populateListView(){
        ArrayList<MenuItem> itemsList = currentOrder.getItems();

//        if(itemsList.isEmpty()){
//            return;
//        }
        ArrayList<Addons> otl = new ArrayList<>();
        otl.add(Addons.ONIONS); otl.add(Addons.TOMATOES); otl.add(Addons.LETTUCE);
        Sandwich sandwich1 = new Sandwich(Bread.BRIOCHE, Protein.CHICKEN, otl); //8.99 + 0.30 + 0.30 + 0.30

        ArrayList<Addons> oac = new ArrayList<>();
        oac.add(Addons.ONIONS); oac.add(Addons.AVOCADO); oac.add(Addons.CHEESE);
        Sandwich sandwich2 = new Sandwich(Bread.WHEAT_BREAD, Protein.ROAST_BEEF, oac); //10.99 + 0.30 + 0.50 + 1.00
//        System.out.println(String.format("Sandwich1:\n %s",sandwich1.toString()));
//        System.out.println(String.format("Sandwich2:\n %s",sandwich2.toString()));
        itemsList.add(sandwich1); itemsList.add(sandwich2);
        for(MenuItem item : itemsList){
            orderItemsListView.getItems().add(item.toString());
        }
        //orderItemsListView.getItems().addAll("first", "second", "third");
    }

    @FXML
    protected void onRemoveSelectedItemClick() {
        int selectedIndex = orderItemsListView.getSelectionModel().getSelectedIndex();
        System.out.println(selectedIndex);
        if (selectedIndex >= 0) {
            orderItemsListView.getItems().remove(selectedIndex);
            currentOrder.getItems().remove(selectedIndex);
            updatePriceLabels();
        }
    }

    @FXML
    protected void onClearAllItemsClick() {
        orderItemsListView.getItems().clear();
        updatePriceLabels();
    }

    @FXML
    protected void onPlaceOrderClick() {
        System.out.println("Order placed!");
    }

    private void updatePriceLabels() {
        // Calculate subtotal (summing all items) - this is a simple example
        double subtotal = 0.0;
//        for (String item : orderItemsListView.getItems()) {
//            // Extract price from item description
//            try {
//                String priceStr = item.substring(item.lastIndexOf("$") + 1);
//                subtotal += Double.parseDouble(priceStr);
//            } catch (Exception e) {
//                System.err.println("Error parsing price: " + e.getMessage());
//            }
        //}
        for(MenuItem item : currentOrder.getItems()){
            subtotal += item.price();
        }

        // Calculate tax and total
        double tax = subtotal * 0.06625;
        double total = subtotal + tax;

        // Update labels
        subtotalLabel.setText(String.format("$%.2f", subtotal));
        taxLabel.setText(String.format("$%.2f", tax));
        totalLabel.setText(String.format("$%.2f", total));
    }

    public void addToCurrentOrder(MenuItem item){
        currentOrder.addToOrder(item);
        orderItemsListView.getItems().add(item.toString());
    }

    public void newOrder(){
        this.currentOrder = new Order();
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }
}