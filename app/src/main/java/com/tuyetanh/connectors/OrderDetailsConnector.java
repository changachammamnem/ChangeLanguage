package com.tuyetanh.connectors;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.tuyetanh.model.OrderDetails;

public class OrderDetailsConnector {

    public OrderDetails getOrderDetailById(SQLiteDatabase db, int orderId) {
        String sql = "SELECT o.Code, o.OrderDate, e.Name, c.Name, " +
                "SUM(((od.Quantity * od.Price) * (1 - od.Discount / 100.0)) * (1 + od.VAT / 100.0)) AS Total " +
                "FROM Orders o " +
                "JOIN Employee e ON o.EmployeeId = e.Id " +
                "JOIN Customer c ON o.CustomerID = c.Id " +
                "JOIN OrderDetails od ON o.Id = od.OrderID " +
                "WHERE o.Id = ? " +
                "GROUP BY o.Code, o.OrderDate, e.Name, c.Name";

        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(orderId)});

        if (cursor.moveToFirst()) {
            String code = cursor.getString(0);
            String orderDate = cursor.getString(1);
            String empName = cursor.getString(2);
            String cusName = cursor.getString(3);
            double total = cursor.getDouble(4);

            OrderDetails od = new OrderDetails();
            od.setCode(code);
            od.setOrderDate(orderDate);
            od.setEmployeeName(empName);
            od.setCustomerName(cusName);
            od.setTotalValue(total);

            cursor.close();
            return od;
        }

        cursor.close();
        return null;
    }
}
