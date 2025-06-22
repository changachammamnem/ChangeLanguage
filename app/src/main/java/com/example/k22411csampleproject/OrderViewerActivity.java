package com.example.k22411csampleproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.adapters.OrderViewerAdapter;
import com.tuyetanh.connectors.OrderViewerConnector;
import com.tuyetanh.connectors.SQLiteConnector;
import com.tuyetanh.model.OrderViewer;

import java.util.ArrayList;

public class OrderViewerActivity extends AppCompatActivity {
    ListView lvOrderView;
    OrderViewerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order_viewer);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
    }

    private void addViews() {
        lvOrderView=findViewById(R.id.lvOrderViewer);
        adapter=new OrderViewerAdapter(this, R.layout.item_orderviewer);
        lvOrderView.setAdapter(adapter);

        SQLiteConnector connector=new SQLiteConnector(this);
        OrderViewerConnector ovc=new OrderViewerConnector();
        ArrayList<OrderViewer> dataset=ovc.getAllOrderViewera(connector.openDatabase());
        adapter.addAll(dataset);


        lvOrderView.setOnItemClickListener((parent, view, position, id) -> {
            OrderViewer selectedOrder = adapter.getItem(position);
            Intent intent = new Intent(OrderViewerActivity.this, OrderDetailsActivity.class);
            intent.putExtra("ORDER_ID", selectedOrder.getId());
            startActivity(intent);
        });



    }

}