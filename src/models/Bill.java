package models;

import dao.*;
import java.util.Date;
import java.util.List;



public class Bill {
    private int billId;
    private Date billDate;
    private List<BillItem> items;
    private double totalAmount;
    private double cashReceived;
    private double cashChange;

    public Bill(){

    }

    public Bill(int billId, Date date,List<BillItem> items, double totalAmount, double cashReceived, double cashChange) {
        this.billId = billId;
        this.billDate = billDate;
        this.items = items;
        this.totalAmount = totalAmount;
        this.cashReceived = cashReceived;
        this.cashChange = cashChange;
    }


    //getters and setters

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public List<BillItem> getItems() {
        return items;
    }

    public void setItems(List<BillItem> items) {
        this.items = items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getCashReceived() {
        return cashReceived;
    }

    public void setCashReceived(double cashReceived) {
        this.cashReceived = cashReceived;
    }

    public double getCashChange() {
        return cashChange;
    }

    public void setCashChange(double cashChange) {
        this.cashChange = cashChange;
    }


}
