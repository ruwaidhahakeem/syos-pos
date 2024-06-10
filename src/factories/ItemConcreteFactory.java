package factories;
import models.*;
import java.util.HashSet;
import java.util.Set;

public class ItemConcreteFactory extends ItemFactory {

    private Set<Item> items = new HashSet<>();

    @Override
    public Item createItem(String itemCode, String itemName, double price, String variation, String batchCode, String itemType) {

        if (price < 0) {
            //to make sure price cant' be a negative value
            throw new IllegalArgumentException("Price can't' negative: " + price);
        }
        if (sameItemAndBatchCode(itemCode, batchCode)) {
            throw new IllegalArgumentException("Item with code " + itemCode + " and batch code " + batchCode + " already exists." +"/nPlease Re-check Item and Btahc Code");

        }


        Item item = switch (itemType) {
            case "Fresh Produce" -> new FreshProduce(itemCode, itemName, price, variation, batchCode, itemType);
            case "Canned Items" -> new TinnedGoods(itemCode, itemName, price, variation, batchCode, itemType);
            case "" -> new NotCategorized(itemCode, itemName, price, variation, batchCode, itemType);
            default -> throw new IllegalArgumentException("Unknown item type: " + itemType);
        };
        items.add(item);
        return item;
    }

    private boolean sameItemAndBatchCode(String itemCode, String batchCode) {
        //to ensure that same item code doesnt exist for same batch code
        return items.stream().anyMatch(item -> item.getItemCode().equals(itemCode) && item.getBatchCode().equals(batchCode));
    }
}
