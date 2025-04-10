package model;

public enum Addons {
    LETTUCE("Lettuce", 0.30),
    TOMATOES("Tomatoes", 0.30),
    ONIONS("Onions", 0.30),
    AVOCADO("Avocado", 0.50),
    CHEESE("Cheese", 1.00);

    // Lettuce
    // Tomatoes
    // Onions
    // Avocado
    // Cheese
    private String addonType;
    private double price;

    Addons(String addonType, double price) {
        this.addonType = addonType;
        this.price = price;
    }

    public String getAddonType() {
        return addonType;
    }

    public double getPrice() {
        return price;
    }
}
