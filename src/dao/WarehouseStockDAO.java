package dao;

import database.DatabaseConnection;
import models.WarehouseStock;
import java.util.List;

public interface WarehouseStockDAO {
    void addWarehouseStock(WarehouseStock warehouseStock);
    WarehouseStock getWarehouseStock(String itemCode);
    void updateWarehouseStock(WarehouseStock warehouseStock);
    void deleteWarehouseStock(String itemCode);
    List<WarehouseStock> getAllWarehouseStock();

    List<WarehouseStock> getStockByExpiry(String itemCode);
    List<WarehouseStock> getStockByArrival(String itemCode);
}
