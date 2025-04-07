package model;

public class Side {

    private Size size;
    private SideType type;

    public Side(Size size, SideType type){
        this.size = size;
        this.type = type;
    }

    public double price(){
        double price = 0;

        price += type.getPrice();

        if(this.size == Size.MEDIUM){
            price += 0.50;
        }
        else if(this.size == Size.LARGE){
            price += 1.00;
        }

        return price;
    }
}
