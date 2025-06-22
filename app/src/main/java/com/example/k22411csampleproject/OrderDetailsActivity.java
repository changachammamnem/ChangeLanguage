package com.example.k22411csampleproject;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.tuyetanh.connectors.OrderDetailsConnector;
import com.tuyetanh.connectors.SQLiteConnector;
import com.tuyetanh.model.OrderDetails;

public class OrderDetailsActivity extends AppCompatActivity {
    TextView tvOrderCode, tvOrderDate, tvEmployee, tvCustomer, tvTotalValue;
    int orderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();

        orderId = getIntent().getIntExtra("ORDER_ID", -1);

        if (orderId != -1) {
            loadOrderDetails(orderId);
        }
    }

    private void loadOrderDetails(int orderId) {
        SQLiteConnector connector = new SQLiteConnector(this);
        SQLiteDatabase db = connector.openDatabase();

        OrderDetailsConnector odc = new OrderDetailsConnector();
        OrderDetails order = odc.getOrderDetailById(db, orderId);

        if (order != null) {
            tvOrderCode.setText("Mã hóa đơn: " + order.getCode());
            tvOrderDate.setText("Ngày đặt: " + order.getOrderDate());
            tvEmployee.setText("Nhân viên: " + order.getEmployeeName());
            tvCustomer.setText("Khách hàng: " + order.getCustomerName());
            tvTotalValue.setText("Tổng giá trị: " + order.getTotalValue() + " VND");
        }
    }

    private void addViews() {
        tvOrderCode = findViewById(R.id.tvOrderCode);
        tvOrderDate = findViewById(R.id.tvOrderDate);
        tvEmployee = findViewById(R.id.tvEmployee);
        tvCustomer = findViewById(R.id.tvCustomer);
        tvTotalValue = findViewById(R.id.tvTotalValue);
    }
    
}