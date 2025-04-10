package model;

public class Combo extends MenuItem{
    private Sandwich sandwich;
    private Beverage beverage;
    private Side side;

    public Combo(Sandwich sandwich, Beverage beverage, Side side){
        this.sandwich = sandwich;
        this.beverage = beverage;
        this.side = side;
    }

    public Beverage getBeverage() {
        return beverage;
    }

    public Sandwich getSandwich() {
        return sandwich;
    }

    public Side getSide() {
        return side;
    }

    @Override
    public double price() {
        return this.sandwich.price() + 2.00;
    }

    @Override
    public String toString() {
        return String.format("Combo:\n %s\n %s\n %s\n", sandwich.toString(), beverage.toString(), side.toString());
    }
}
