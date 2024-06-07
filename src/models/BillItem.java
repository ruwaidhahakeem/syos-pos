package models;

public class BillItem {
    private int billItemId;
    private String itemCode;
    private int quantity;
    private double itemTotal;
    private String batchCode;

    public BillItem() {

    }

    public BillItem(int billItemId, String itemCode, int quantity, double itemTotal, String batchCode) {
        this.billItemId = billItemId;
        this.itemCode = itemCode;
        this.quantity = quantity;
        this.itemTotal = itemTotal;
        this.batchCode = batchCode;
    }

    //getters and setters
    public int getBillItemId() {
        return billItemId;
    }

    public void setBillItemId(int billItemId) {
        this.billItemId = billItemId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        //ensure that the itemcode isnt empty
        if(itemCode==null || itemCode.isEmpty()){
            throw new IllegalArgumentException("Item code should not be null");
        }
        this.itemCode = itemCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        //ensure that quantity is more than 1
        if (quantity <= 0) {
            throw new IllegalArgumentException("quantity must be more than 0");
        }
        this.quantity = quantity;
    }

    public double getItemTotal() {
        return itemTotal;
    }

    public void setItemTotal(double itemTotal) {
        this.itemTotal = itemTotal;
    }

    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        //ensure that batch code isnt empty
        if(batchCode==null || batchCode.isEmpty()){
            throw new IllegalArgumentException("batch code should not be null");
        }

        this.batchCode = batchCode;
    }

    // Methods
    public void calculateSubtotal() {
        // Implementation here
    }


}

