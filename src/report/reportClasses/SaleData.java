package report.reportClasses;

public class SaleData {
    private String itemCode;
    private String itemName;
    private int quantitySold;
    private double totalPerItem;
    private double totalAmount;



public SaleData(String itemCode, String itemName, int quantitySold, double totalPerItem, double totalAmount){

    this.itemCode = itemCode;
    this.itemName = itemName;
    this.quantitySold = quantitySold;
    this.totalPerItem = totalPerItem;
    this.totalAmount = totalAmount;
    }


    //getters and setters

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

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }

    public double getTotalPerItem() {
        return totalPerItem;
    }

    public void setTotalPerItem(double totalPerItem) {
        this.totalPerItem = totalPerItem;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
