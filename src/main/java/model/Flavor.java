package model;

public enum Flavor {
    DR_PEPPER("Dr Pepper"),
    APPLE_JUICE("Apple Juice"),
    SPRITE("Sprite"),
    COKE("Coke"),
    PEPSI("Pepsi"),
    FANTA_ORANGE("Fanta Orange"),
    FANTA_GRAPE("Fanta Grape"),
    LEMONADE("Lemonade"),
    ICED_TEA("Iced Tea"),
    DIET_PEPSI("Diet Pepsi"),
    DIET_COKE("Diet Coke"),
    ORANGE_JUICE("Orange Juice"),
    WATER("Water"),
    SELTZER("Seltzer"),
    DIET_DR_PEPPER("Diet Dr Pepper");


    private String flavorName;

    Flavor(String flavorName){this.flavorName = flavorName;}
}
