package factories;
import models.Item;

//defines the method that will create items
public abstract class ItemFactory {
    public abstract Item createItem(String itemCode, String itemName, double price, String variation, String batchCode, String itemType);
}




