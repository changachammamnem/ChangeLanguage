package com.example.k22411csampleproject;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.tuyetanh.connectors.CategoryConnector;
import com.tuyetanh.model.Category;

public class CategoryManagementActivity extends AppCompatActivity {
    ListView lvCatalog;
    ArrayAdapter<Category> adapter;
    CategoryConnector connector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_category_management);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
        addEvents();
    }

    private void addViews() {
        lvCatalog = findViewById(R.id.lvCategory);
        adapter = new ArrayAdapter<>(CategoryManagementActivity.this,
                android.R.layout.simple_list_item_1);
        connector = new CategoryConnector();
        adapter.addAll(connector.get_all_categories());
        lvCatalog.setAdapter(adapter);
    }

    private void addEvents() {
        lvCatalog.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Category selected = adapter.getItem(position);
                adapter.remove(selected);
                return false;
            }
        });
    }

}