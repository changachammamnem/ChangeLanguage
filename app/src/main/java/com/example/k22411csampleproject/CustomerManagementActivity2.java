package com.example.k22411csampleproject;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.tuyetanh.connectors.CustomerConnecter;
import com.tuyetanh.connectors.SQLiteConnector;
import com.tuyetanh.model.Customer;
import com.tuyetanh.model.ListCustomer;

import java.util.Locale;

public class CustomerManagementActivity2 extends AppCompatActivity {
    ListView lvCustomer;
    ArrayAdapter<Customer> adapter;
    CustomerConnecter connector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_customer_management2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
        addEvents();
    }

    private void addEvents() {
        lvCustomer.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
/*              Customer selected=adapter.getItem(position);
                adapter.remove(selected);*/
                Customer c=adapter.getItem(position);
                displayCustomerDetailActivity(c);
                return false;
            }

        });
/*        lvCustomer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Customer c=adapter.getItem(position);
                displayCustomerDetailActivity(c);

            }
        });*/
    }

    private void displayCustomerDetailActivity(Customer c) {
        Intent intent=new Intent(CustomerManagementActivity2.this, CustomerDetailActivity.class);
        intent.putExtra("SELECTED_CUSTOMER",c);
//        startActivity(intent);
        // 300 là them moi, 400 là xem và sửa dữ liệu
        startActivityForResult(intent, 400);
    }

    private void addViews()
    {
        lvCustomer= findViewById(R.id.lvCustomer);
        adapter=new ArrayAdapter<>(CustomerManagementActivity2.this,
                android.R.layout.simple_list_item_1);
        connector=new CustomerConnecter();
        //thêm
        ListCustomer lc=connector.getAllCustomers(new SQLiteConnector(this).openDatabase());
//        connector.getAllCustomers(new SQLiteConnector(this).openDatabase());
//        adapter.addAll(connector.get_all_customers());
        adapter.addAll(lc.getCustomers());
        lvCustomer.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.option_menu_customer,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.menu_new_customer){
            Toast.makeText(CustomerManagementActivity2.this,
                    "Mở màn hình thêm mới khách hàng",
                    Toast.LENGTH_LONG).show();
            Intent intent=new Intent(CustomerManagementActivity2.this,CustomerDetailActivity.class);
            //startActivity(intent);
            //dong goi va dat ma request code la 300
            startActivityForResult(intent,300);
            // doi so thu nhat la tap tin muon gui di, doi so thu hai la ma so tap tin do (de do nham lan voi tap tin khac)
        }
        else if (item.getItemId()==R.id.menu_broadcast_advertising) {
            Toast.makeText(CustomerManagementActivity2.this,
                    "Mở màn hình gửi quảng cáo cho hàng loạt khách hàng",
                    Toast.LENGTH_LONG).show();
        }
        else if (item.getItemId()==R.id.menu_help){
        Toast.makeText(CustomerManagementActivity2.this,
                "Mở màn hình chăm sóc khách hàng",
                Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // turong hop xu li New Cus, chi quan tam 300 va 500
        if (requestCode==300 && resultCode==500){
            // thêm mới
            // lay goi tin ra hui
            Customer c= (Customer) data.getSerializableExtra("NEW_CUSTOMER");
            process_save_customer(c);
        }
        else if (requestCode==400 & resultCode==500)
        {
            // xem và cập nhật
            Customer c=(Customer) data.getSerializableExtra("NEW_CUSTOMER");
            process_save_update_customer(c);
        }
        else if(requestCode==400 && resultCode==600)
        {
            String id= data.getStringExtra("CUSTOMER_ID_REMOVE");
            // GỌI CUSTOMER CONNECTOR
            process_remove_customer(id);
        }
    }

    private void process_remove_customer(String id) {
        SQLiteConnector con=new SQLiteConnector(this);
        SQLiteDatabase database=con.openDatabase();
        CustomerConnecter cc=new CustomerConnecter();
        long flag =cc.removeCustomer(id, database);
        if (flag>0)
        {
            adapter.clear();
            adapter.addAll(cc.getAllCustomers(database).getCustomers());
        }
    }
    // làm sao biết đây là thêm mới hay không thêm mới. ta thêm một cái cờ để cập nhat

    private void process_save_update_customer(Customer c)
    {
        SQLiteConnector con=new SQLiteConnector(this);
        SQLiteDatabase database=con.openDatabase();
        CustomerConnecter cc=new CustomerConnecter();
        long flag =cc.saveUpdateCustomer(c, database);
        Toast.makeText(this,"FLAG"+flag, Toast.LENGTH_LONG).show();
        if (flag>0)
        {
            adapter.clear();
            adapter.addAll(cc.getAllCustomers(database).getCustomers());
        }
    }
    private void process_save_customer(Customer c) {
        SQLiteConnector con=new SQLiteConnector(this);
        SQLiteDatabase database=con.openDatabase();
        CustomerConnecter cc=new CustomerConnecter();
        long flag =cc.saveNewCustomer(c, database);
        if (flag>0)
        {
            adapter.clear();
            adapter.addAll(cc.getAllCustomers(database).getCustomers());
        }


//        boolean result= connector.isExit(c);
//        if (result==true){
//            //thong tin da ton tai trong he thong, ho co nhu cau sua cac thong tin khac
//            // ve nha lam truong hop sua thong tin
//        }
//        else{
//            // them moi cus vi chua ton tai
//            connector.addCustomer(c);
//            adapter.clear();
//            adapter.addAll(connector.get_all_customers());
//        }


    }
}