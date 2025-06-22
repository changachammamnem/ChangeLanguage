package com.tuyetanh.connectors;

import android.database.sqlite.SQLiteDatabase;

import com.tuyetanh.model.ListPaymentMethod;

public class PaymentConnector {
    public ListPaymentMethod getAllPaymentMethods(SQLiteDatabase database) {
        ListPaymentMethod lpm = new ListPaymentMethod();
        lpm.loadFromDatabase(database);
        return lpm;
    }
}
