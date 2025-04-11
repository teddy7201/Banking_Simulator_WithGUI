package model;

public class Combo extends MenuItem {
    private Sandwich sandwich;
    private Beverage beverage;
    private Side side;

    public Combo(Sandwich sandwich, Beverage beverage, Side side, int quantity) {
        this.sandwich = sandwich;
        this.beverage = beverage;
        this.side = side;
        this.quantity = quantity;
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
        double price = 0;
        price = this.sandwich.price() + (2.00 * quantity);
        return price;
    }

    @Override
    public String toString() {
        StringBuilder description = new StringBuilder();

        // Start with basic combo description
        if (sandwich instanceof Burger) {
            Burger burger = (Burger) sandwich;
            String pattyType = burger.isDoublePatty() ? "Double" : "Single";
            description.append(pattyType).append(" Burger Combo:\n");
        } else {
            Protein protein = sandwich.getProtein();
            String proteinName = protein.getProteinType();
            description.append(proteinName).append(" Sandwich Combo:\n");
        }

        // Add sandwich details with bread type
        description.append(" - Base Item: ");
        description.append(sandwich.getBread().getBreadType()).append(" ");
        description.append(sandwich.getProtein().getProteinType()).append(" ");

        // Add toppings info if available
        if (!sandwich.getAddOns().isEmpty()) {
            description.append("with ");
            for (Addons addon : sandwich.getAddOns()) {
                description.append(addon.getAddonType().toLowerCase()).append(", ");
            }
            // Remove trailing comma and space
            description.setLength(description.length() - 2);
        }
        description.append("\n");

        // Add beverage details
        description.append(" - Drink: ").append(beverage.getFlavor()).append(" (")
                .append(beverage.getSize().getSizeName()).append(")\n");

        // Add side details
        description.append(" - Side: ").append(side.toString().split("\n")[0]);
        description.append("\n");
        description.append("- Quantity: " + quantity);
        description.append("\n");
        return description.toString();
    }
}
