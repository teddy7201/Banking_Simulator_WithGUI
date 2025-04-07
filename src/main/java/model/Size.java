package model;

public enum Size {
    LARGE("Large"),
    MEDIUM("Medium"),
    SMALL("Small");

    private String sizeName;

    Size(String sizeName) {this.sizeName = sizeName;}

    public String getSizeName() {return sizeName;}


}
