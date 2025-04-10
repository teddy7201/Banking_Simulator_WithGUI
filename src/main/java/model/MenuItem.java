package model;

/**
 * This is an abstract class used to declare the quantity variable and price
 * method that all MenuItems will have in
 * common.
 * 
 * @author Henry Rodriguez
 */
public abstract class MenuItem {
    protected int quantiy;

    /**
     * An abstract method that will be used to calculate the price of the menu
     * price.
     * 
     * @return A double representing the price of the item.
     */
    public abstract double price();
}
