package model;

public enum SideType {
    CHIPS("Chips", 1.99),
    FRIES("Fries", 2.49),
    ONION_RINGS("Onion Rings", 3.29),
    APPLE_SLICES("Apple Slices", 1.29);

    private String sideName;
    private double price;

    SideType(String sideName, double price){
        this.sideName = sideName;
        this.price = price;
    }

    public double getPrice() {return price;}

    public String getSideName() {return sideName;}

}
