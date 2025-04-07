package model;

public class Combo extends MenuItem{
    private Sandwich sandwich;
    private Beverage beverage;
    private Side side;

    @Override
    public double price() {
        return 0;
    }
}
