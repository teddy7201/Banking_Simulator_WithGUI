package model;

public class Beverage extends MenuItem{
    private Size size;
    private Flavor flavor;

    public Beverage(Size size, Flavor flavor){
        this.size = size;
        this.flavor = flavor;
    }

    public Flavor getFlavor() {return flavor;}

    public Size getSize() {return size;}

    @Override
    public double price() {
        if(this.size == Size.SMALL){
            return 1.99;
        }
        else if(this.size == Size.MEDIUM){
            return 2.49;
        }
        return 2.99;
    }

    @Override
    public String toString() {
        return String.format("%s\n %s", flavor.getFlavorName(), size.getSizeName());
    }
}
