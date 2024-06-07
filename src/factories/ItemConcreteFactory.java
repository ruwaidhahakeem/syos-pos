package factories;

import models.*;

public class ItemConcreteFactory implements ItemFactory{
    @Override
    public Item createItem(String itemCode, String itemName, double price, String variation, String batchCode, String itemType){
        // ensure no value is null

        if (itemCode == null || itemCode.isEmpty() ||
                itemName == null || itemName.isEmpty() ||
                price <= 0.0 ||
                variation == null || variation.isEmpty() ||
                batchCode == null || batchCode.isEmpty() ||
                itemType == null || itemType.isEmpty()) {
            return null;  // Invalid input, return null
        }

        if("Fresh Produce".equalsIgnoreCase(itemType)){
            return new freshProduce(itemCode, itemName, price, variation, batchCode, itemType);
        }
        else if ("Packed Goods".equalsIgnoreCase(itemType)) {
            return new packedGoods(itemCode, itemName, price, variation, batchCode, itemType);
        }
        else if("Dairy and Meat".equalsIgnoreCase(itemType)){
            return new dairyAndMeat(itemCode, itemName, price, variation, batchCode, itemType);
        }
        return null;
    }
}
