package models;

public class dairyAndMeat extends Item {
    public dairyAndMeat(String itemCode, String itemName, double price, String variation, String batchCode, String itemType) {
        super(itemCode, itemName, price, variation, batchCode, "Dairy And Meat");
    }

    @Override
    public void display() {
        System.out.println("Dairy and Meat: " + getItemName());
    }
}
