package com.tuyetanh.model;

public class OrderDetails {
    private int Id;
    private String code, orderDate, employeeName, customerName;
    private int ProductId;
    private int Quantity;
    private double Price;
    private double Discount;
    private double VAT;
    private double TotalValue;

    public OrderDetails() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public double getDiscount() {
        return Discount;
    }

    public void setDiscount(double discount) {
        Discount = discount;
    }

    public double getVAT() {
        return VAT;
    }

    public void setVAT(double VAT) {
        this.VAT = VAT;
    }

    public double getTotalValue() {
        TotalValue=((Quantity*Price)*(1-Discount/100))*(1+VAT/100);
        return TotalValue;
    }

    public void setTotalValue(double totalValue) { this.TotalValue = totalValue; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getOrderDate() { return orderDate; }
    public void setOrderDate(String orderDate) { this.orderDate = orderDate; }

    public String getEmployeeName() { return employeeName; }
    public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }









}
