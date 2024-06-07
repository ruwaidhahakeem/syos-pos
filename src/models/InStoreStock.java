package models;

public class InStoreStock {
    private String itemCode;
    //added after report
    private String itemName;
    private String batchCode;
    private String variation;
    private int quantityOnShelf;
    private int shelfRubric;
    private int restockQuantity;

    public InStoreStock() {}

    public InStoreStock(String itemCode, String itemName, String batchCode, String variation, int quantityOnShelf, int shelfRubric, int restockQuantity) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.batchCode = batchCode;
        this.variation = variation;
        this.quantityOnShelf = quantityOnShelf;
        this.shelfRubric = shelfRubric;
        this.restockQuantity = restockQuantity;
    }

    // getters and setters
    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    public String getVariation() {
        return variation;
    }

    public void setVariation(String variation) {
        this.variation = variation;
    }

    public int getQuantityOnShelf() {
        return quantityOnShelf;
    }

    public  void setQuantityOnShelf(int quantityOnShelf) {
        this.quantityOnShelf = quantityOnShelf;
    }

    public int getShelfRubric() {
        return shelfRubric;
    }

    public void setShelfRubric(int shelfRubric) {
        this.shelfRubric = shelfRubric;
    }

    public int getRestockQuantity() {
        return restockQuantity;
    }

    public void setRestockQuantity(int restockQuantity) {
        this.restockQuantity = restockQuantity;
    }

    // Methods
    public void checkStockStatus() {
        // Implementation here
    }

    public void updateQuantityInStore(int restockQuantity) {
        // Implementation here
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
