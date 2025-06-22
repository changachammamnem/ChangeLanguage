package com.tuyetanh.connectors;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.tuyetanh.model.Customer;
import com.tuyetanh.model.OrderViewer;

import java.util.ArrayList;

public class OrderViewerConnector {
    public ArrayList<OrderViewer>getAllOrderViewera(SQLiteDatabase database)
    {
        ArrayList<OrderViewer> datasets=new ArrayList<>();

        StringBuilder builder=new StringBuilder();

        builder.append (" SELECT ");
        builder.append (" o.Id AS OrderID, ");
        builder.append ("     o.Code AS OrderCode, ");
        builder.append (" o.OrderDate, ");
        builder.append ("         e.Name AS EmployeeName, ");
        builder.append (" c.Name AS CustomerName, ");
        builder.append ("     SUM(((od.Quantity * od.Price) * (1 - od.Discount / 100.0)) * (1 + od.VAT / 100.0)) AS TotalValue ");
        builder.append (" FROM Orders o ");
        builder.append (" JOIN Employee e ON o.EmployeeId = e.Id ");
        builder.append (" JOIN Customer c ON o.CustomerID = c.Id ");
        builder.append (" JOIN OrderDetails od ON o.Id = od.OrderID ");
        builder.append (" GROUP BY o.Id, o.Code, o.OrderDate, e.Name, c.Name; ");

        String sql=builder.toString();

        Cursor cursor = database.rawQuery(sql,null);
        while(cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String code = cursor.getString(1);
            String orderdate = cursor.getString(2);
            String employeeName = cursor.getString(3);
            String customerName = cursor.getString(4);
            double totalvalue = cursor.getDouble(5);

            OrderViewer ov= new OrderViewer();
            ov.setId(id);
            ov.setCode(code);
            ov.setOrderDate(orderdate);
            ov.setEmployeeName(employeeName);
            ov.setCustomerName(customerName);
            ov.setTotalOrderValue(totalvalue);
            datasets.add(ov);
        }
        cursor.close();
        return datasets;
    }

    // câu tính thue, du CRUD
}
