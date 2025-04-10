package model;

import java.util.ArrayList;

public class Burger extends Sandwich {
    private boolean doublePatty;

    /**
     * Constructor for Burger with 4 parameters.
     *
     * @param bread       A Bread object representing the type of bread of the
     *                    burger.
     * @param protein     A Protein object representing the type of protein of the
     *                    burger.
     * @param addOns      An ArrayList of Addons containing all the addons added to
     *                    the burger.
     * @param doublePatty A boolean indicating if the burger will have a double
     *                    patty or not.
     */
    public Burger(Bread bread, Protein protein, ArrayList<Addons> addOns, boolean doublePatty, int quantity) {
        super(bread, protein, addOns, quantity);
        this.doublePatty = doublePatty;
    }

    /**
     * This method indicates if the burger has a double patty or not.
     *
     * @return True if the burger has a double patty, otherwise false.
     */
    public boolean isDoublePatty() {
        return doublePatty;
    }

    /**
     * Overriden price() method that calculates the price of the burger.
     *
     * @return A double representing the price of the burger.
     */
    @Override
    public double price() {
        double price = 0;
        if (isDoublePatty()) {
            price += this.protein.getPrice() + 2.50;
        } else {
            price += this.protein.getPrice();
        }

        if (addOns.isEmpty()) {
            return price;
        } else {
            for (Addons addon : addOns) {
                price += addon.getPrice();
            }
        }
        price = price * quantity;
        return price;
    }

    @Override
    public String toString() {
        String pattySD;
        if (doublePatty) {
            pattySD = "Double Patty";
        } else {
            pattySD = "Single Patty";
        }

        if (addOns.isEmpty()) {
            return String.format("Burger\n Bread: %s Protein: %s Addons: None", bread.getBreadType(), pattySD);
        }

        StringBuilder addonNames = new StringBuilder();
        for (Addons addon : addOns) {
            addonNames.append(addon.getAddonType() + " ");
        }

        return String.format("Burger\n Bread: %s Protein: %s Addons: %s Quantity: %d", bread.getBreadType(), pattySD, addonNames, quantity);
    }
}