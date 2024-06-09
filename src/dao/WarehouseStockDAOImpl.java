package dao;

import models.WarehouseStock;

import java.util.List;

public class WarehouseStockDAOImpl implements WarehouseStockDAO {
    @Override
    public void addWarehouseStock(WarehouseStock warehouseStock) {

    }

    @Override
    public WarehouseStock getWarehouseStock(String itemCode) {
        return null;
    }

    @Override
    public void updateWarehouseStock(WarehouseStock warehouseStock) {

    }

    @Override
    public void deleteWarehouseStock(String itemCode) {

    }

    @Override
    public List<WarehouseStock> getAllWarehouseStock() {
        return List.of();
    }

    @Override
    public List<WarehouseStock> getStockByExpiry(String itemCode) {
        return List.of();
    }
}
