package models;

public class FreshProduce extends Item {
    public FreshProduce(String itemCode, String itemName, double price, String variation, String batchCode, String itemType) {
        super(itemCode, itemName, price, variation, batchCode, itemType);
    }

    @Override
    public void display() {
        System.out.println("Type: Fresh Produce - "+ getItemName());
    }

    public boolean isExpired(){
    //logic to check if it's stale and needs to be thrown
        //ensure a variable is introduced for this asw
        return false;
    }
}


