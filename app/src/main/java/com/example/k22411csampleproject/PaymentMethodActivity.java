package com.example.k22411csampleproject;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.adapters.PaymentMethodAdapter;
import com.tuyetanh.connectors.PaymentConnector;
import com.tuyetanh.connectors.SQLiteConnector;
import com.tuyetanh.model.ListPaymentMethod;

public class PaymentMethodActivity extends AppCompatActivity {
    ListView lvPaymentMethod;
    PaymentMethodAdapter adapter;
    ListPaymentMethod lpm;
    PaymentConnector connector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_payment_method);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
    }

    private void addViews() {
        lvPaymentMethod = findViewById(R.id.lvPaymentMethod);
        adapter = new PaymentMethodAdapter(this, R.layout.item_paymentmrthod);
        lvPaymentMethod.setAdapter(adapter);

        // Load từ SQLite qua connector
        connector = new PaymentConnector();
        lpm = connector.getAllPaymentMethods(new SQLiteConnector(this).openDatabase());
        adapter.addAll(lpm.getPaymentMethods());
    }

}