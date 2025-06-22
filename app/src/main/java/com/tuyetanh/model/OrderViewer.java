package com.tuyetanh.model;

public class OrderViewer extends Orders {
    private String EmployeeName;
    private String CustomerName;
    private double TotalOrderValue;

    public String getEmployeeName() {
        return EmployeeName;
    }

    public void setEmployeeName(String employeeName) {
        EmployeeName = employeeName;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public double getTotalOrderValue() {
        return TotalOrderValue;
    }
    private int Id;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }


    public void setTotalOrderValue(double totalOrderValue) {
        TotalOrderValue = totalOrderValue;
    }


}
