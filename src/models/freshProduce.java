package models;

public class freshProduce extends Item {
    public freshProduce(String itemCode, String itemName, double price, String variation, String batchCode, String itemType) {
        super(itemCode, itemName, price, variation, batchCode, "Fresh Produce");
    }

    @Override
    public void display() {
        System.out.println("Fresh Produce: " + getItemName());
    }
}


//
//public class freshProduce extends Item {
//    private String itemCode;
//    private String itemName;
//    private double price;
//    private String variation;
//    private String batchCode;
//    private String itemType;
//
//    public freshProduce(String itemCode, String itemName, double price, String variation, String batchCode, String itemType) {
//        this.itemCode = itemCode;
//        this.itemName = itemName;
//        this.price = price;
//        this.variation = variation;
//        this.batchCode = batchCode;
//        this.itemType = "Fresh Produce";
//    }
//
//    @Override
//    public String getItemCode() {
//        return itemCode;
//    }
//    @Override
//    public String getItemName() {
//        return itemName;
//    }
//
//    @Override
//    public double getPrice() {
//        return price;
//    }
//
//    @Override
//    public String getVariation() {
//        return variation;
//    }
//
//    @Override
//    public String getBatchCode() {
//        return batchCode;
//    }
//
//    @Override
//    public String getItemType() {
//        return itemType;
//    }
//
//
//    @Override
//    public void setItemCode(String itemCode) {
//        this.itemCode=itemCode;
//    }
//
//    @Override
//    public void setItemName(String itemName) {
//        this.itemName=itemName;
//    }
//
//    @Override
//    public void setPrice(double price) {
//        this.price=price;
//    }
//
//    @Override
//    public void setVariation(String variation) {
//        this.variation=variation;
//    }
//
//    @Override
//    public void setBatchCode(String batchCode) {
//        this.batchCode=batchCode;
//    }
//
//    @Override
//    public void setItemType(String itemType) {
//        this.itemType=itemType;
//    }
//}
