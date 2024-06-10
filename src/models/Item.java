package models;
//Factory pattern applied
public abstract class Item {
    private String itemCode;
    private String itemName;
    private double price;
    private String variation;
    private String batchCode;
    private String itemType;

    //removal of billItem
    public Item(String itemCode, String itemName, double price, String variation, String batchCode, String itemType) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.price = price;
        this.variation = variation;
        this.batchCode = batchCode;
        this.itemType = itemType;
    }

    // getters and setters
    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getVariation() {
        return variation;
    }

    public void setVariation(String variation) {
        this.variation = variation;
    }

    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }


    public abstract void display();
}
