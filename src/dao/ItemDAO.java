package dao;

import models.Item;
import java.util.List;

public interface ItemDAO  {
    void addItem(Item item);
    void deleteItem(String itemCode);
    Item getItem(String itemCode);
    void updateItem(Item item);
    List<Item> getAllItems();
}

