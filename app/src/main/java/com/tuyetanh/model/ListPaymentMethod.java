package com.tuyetanh.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ListPaymentMethod {
    ArrayList<PaymentMethod> paymentMethods;

    public ListPaymentMethod() {
        paymentMethods = new ArrayList<>();
    }

    public ArrayList<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(ArrayList<PaymentMethod> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    // NEW: Load từ SQLite thay vì hardcoded
    public void loadFromDatabase(SQLiteDatabase database) {
        paymentMethods.clear(); // clear để tránh trùng
        Cursor cursor = database.rawQuery("SELECT * FROM PaymentMethod", null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String description = cursor.getString(2);
            paymentMethods.add(new PaymentMethod(id, name, description));
        }
        cursor.close();
    }
}

//package com.tuyetanh.model;
//
//import java.util.ArrayList;
//
//public class ListPaymentMethod {
//    ArrayList<PaymentMethod> paymentMethods;
//    public ListPaymentMethod()
//    {
//        paymentMethods=new ArrayList<>();
//    }
//    public ArrayList<PaymentMethod> getPaymentMethods()
//    {
//        return paymentMethods;
//    }
//
//    public void setPaymentMethods (ArrayList<PaymentMethod> paymentMethods){
//        this.paymentMethods=paymentMethods;
//    }
//    public void gen_payments_method()
//    {
//        paymentMethods.add(new PaymentMethod(1, "Banking Account", "Chuyển khoản ngân hàng"));
//        paymentMethods.add(new PaymentMethod(2, "Momo", "Ví Momo"));
//        paymentMethods.add(new PaymentMethod(3, "Cash", "Tiền mặt"));
//        paymentMethods.add(new PaymentMethod(4, "COD", "Nhận hàng rồi thanh toán"));
//    }
//
//
//}
