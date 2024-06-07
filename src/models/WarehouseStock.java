package models;

import java.util.Date;

public class WarehouseStock {
    private String itemCode;
    private String batchCode;
    private Date expiryDate;
    private Date arrivalDate;
    private int quantityInStock;
    private int stockRubric;

    public WarehouseStock() {
    }


    public WarehouseStock(String itemCode, String batchCode, Date expiryDate, Date arrivalDate, int quantityInStock, int stockRubric) {
        this.itemCode = itemCode;
        this.batchCode = batchCode;
        this.expiryDate = expiryDate;
        this.arrivalDate = arrivalDate;
        this.quantityInStock = quantityInStock;
        this.stockRubric = stockRubric;
    }

    //getters and setters
    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public int getStockRubric() {
        return stockRubric;
    }

    public void setStockRubric(int stockRubric) {
        this.stockRubric = stockRubric;
    }


}