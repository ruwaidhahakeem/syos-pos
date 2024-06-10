package models;

public class NotCategorized extends Item {
    public NotCategorized(String itemCode, String itemName, double price, String variation, String batchCode, String itemType) {
        super(itemCode, itemName, price, variation, batchCode, itemType);
    }

    @Override
    public void display() {
        System.out.println("Type: Not Categorized - "+ getItemName());
    }
}


