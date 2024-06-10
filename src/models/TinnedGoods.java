package models;

public class TinnedGoods extends Item {
    public TinnedGoods(String itemCode, String itemName, double price, String variation, String batchCode, String itemType) {
        super(itemCode, itemName, price, variation, batchCode, itemType);
    }

    @Override
    public void display() {
        System.out.println("Type: Tinned Goods - "+ getItemName());
    }
}

