package model;

public class Beverage extends MenuItem {
    private Size size;
    private Flavor flavor;

    public Beverage(Size size, Flavor flavor, int quantity) {
        this.size = size;
        this.flavor = flavor;
        this.quantity = quantity;
    }

    public Flavor getFlavor() {
        return flavor;
    }

    public Size getSize() {
        return size;
    }

    @Override
    public double price() {
        double price = 0;
        if (this.size == Size.SMALL) {
            price += 1.99;
        } else if (this.size == Size.MEDIUM) {
            price += 2.49;
        }
        else{
            price += 2.99;
        }
        return price * quantity;
    }

    @Override
    public String toString() {
        return String.format("%s\n Size: %s Quantity: %d", flavor.getFlavorName(), size.getSizeName(), quantity);
    }

}
