package factories;

import models.Item;

//this is the abstract class which defines the method,
//and is responsible for object creation NOT logic

public interface ItemFactory {
    Item createItem(String itemCode, String itemName, double price, String variation, String batchCode, String itemType);
}
