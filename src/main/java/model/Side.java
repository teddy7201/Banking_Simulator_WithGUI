package model;

public class Side extends MenuItem {

    private Size size;
    private SideType type;

    public Side(Size size, SideType type, int quantity) {
        this.size = size;
        this.type = type;
        this.quantity = quantity;
    }

    public double price() {
        double price = 0;

        price += type.getPrice();

        if (this.size == Size.MEDIUM) {
            price += 0.50;
        } else if (this.size == Size.LARGE) {
            price += 1.00;
        }
        price = price * quantity;
        return price;
    }

    @Override
    public String toString() {
        return String.format("%s\n Size: %s Quantity: %d", type.getSideName(), size.getSizeName(), quantity);
    }
}