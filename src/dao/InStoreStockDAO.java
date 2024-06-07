package dao;

import database.DatabaseConnection;
import models.InStoreStock;
import java.util.List;

public interface InStoreStockDAO {
    void addInStoreStock(InStoreStock inStoreStock);
    InStoreStock getInStoreStock(String itemCode);
    void updateInStoreStock(InStoreStock inStoreStock);
    void deleteInStoreStock(String itemCode);
    List<InStoreStock> getAllInStoreStock();
    void restockInStoreStock(String itemCode);
}
