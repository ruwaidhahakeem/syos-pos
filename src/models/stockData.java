package models;

public class stockData {
    private String itemCode;
    private String itemName;
    private int quantityOnShelf;
    private int quantityInWarehouse;

    public stockData(String itemCode, String itemName, int quantityOnShelf,int quantityInWarehouse ) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.quantityOnShelf = quantityOnShelf;
        this.quantityInWarehouse = quantityInWarehouse;
    }




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

    public int getQuantityOnShelf() {
        return quantityOnShelf;
    }

    public void setQuantityOnShelf(int quantityOnShelf) {
        this.quantityOnShelf = quantityOnShelf;
    }

    public int getQuantityInWarehouse() {
        return quantityInWarehouse;
    }

    public void setQuantityInWarehouse(int quantityInWarehouse) {
        this.quantityInWarehouse = quantityInWarehouse;
    }
}
