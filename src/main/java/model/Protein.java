package model;

public enum Protein {
    ROAST_BEEF("Roast Beef", 10.99),
    SALMON("Salmon", 9.99),
    CHICKEN("Chicken", 8.99),
    BEEF_PATTY("Beef Patty", 6.99);

    private String proteinType;
    private double price;

    Protein(String proteinType, double price){
        this.proteinType = proteinType;
        this.price = price;
    }

    public String getProteinType() {return proteinType;}

    public double getPrice() {return price;}
}
