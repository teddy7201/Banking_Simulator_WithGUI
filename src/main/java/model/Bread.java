package model;

/**
 * This enum class contains the types of bread available to use for the sandwiches and burgers.
 * @author Henry Rodriguez
 */
public enum Bread {
    BRIOCHE("Brioche"),
    WHEAT_BREAD("Wheat bread"),
    PRETZEL("Pretzel"),
    BAGEL("Bagel"),
    SOURDOUGH("Sourdough");


    private String breadType;

    Bread(String breadType){this.breadType = breadType;}

    public String getBreadType() {return breadType;}
}
