package com.example.k22411csampleproject;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.tuyetanh.model.Category;
import com.tuyetanh.model.Customer;
import com.tuyetanh.model.ListCategory;
import com.tuyetanh.model.Product;


public class ProductManagementActivity extends AppCompatActivity {
    Spinner spinnerCategory;
    ArrayAdapter<Category>adapterCategory;
    ListCategory listCategory;

    ListView lvProduct;
    ArrayAdapter<Product>adapterProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_management);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
        addEvents();
    }

    private void addEvents() {
        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Category c=adapterCategory.getItem(i);
                displayProductsByCategory(c);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        lvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product p= adapterProduct.getItem(position);
                displayProductDetailActivity(p);
            }
        });
    }

    private void displayProductDetailActivity(Product p) {
        Intent intent=new Intent(ProductManagementActivity.this, ProductDetailActivity.class);
        intent.putExtra("SELECTED_PRODUCT", p);
        startActivity(intent);
    }

    private void displayProductsByCategory(Category c) {
        //xóa dữ liệu cũ trong listview đi:
        adapterProduct.clear();
        //nạp mới lại dữ liệu cho adapter;
        adapterProduct.addAll(c.getProducts());
    }

    private void addViews() {
        spinnerCategory=findViewById(R.id.spinnerCategory);
        adapterCategory=new ArrayAdapter<>(
                ProductManagementActivity.this,
                android.R.layout.simple_spinner_item);
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapterCategory);

        listCategory=new ListCategory();
        listCategory.generate_sample_dataset();
        adapterCategory.addAll(listCategory.getCategories());

        lvProduct=findViewById(R.id.lvProduct);
        adapterProduct=new ArrayAdapter<>(
                ProductManagementActivity.this,
                android.R.layout.simple_list_item_1);
        lvProduct.setAdapter(adapterProduct);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.option_menu_product,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.menu_new_product){
            Toast.makeText(ProductManagementActivity.this,
                    "Mở màn hình thêm mới sản phẩm",
                    Toast.LENGTH_LONG).show();
            Intent intent=new Intent(ProductManagementActivity.this, ProductDetailActivity.class);
            startActivity(intent);
        }
        else if (item.getItemId()==R.id.menu_modify_stock) {
            Toast.makeText(ProductManagementActivity.this,
                    "Mở màn hình điều chỉnh tồn kho",
                    Toast.LENGTH_LONG).show();
        }
        else if (item.getItemId()==R.id.menu_sync_product){
            Toast.makeText(ProductManagementActivity.this,
                    "Mở màn hình đồng bộ thông tin sản phẩm",
                    Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }
}