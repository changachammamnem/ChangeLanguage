package com.example.k22411csampleproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.tuyetanh.model.Customer;

public class CustomerDetailActivity extends AppCompatActivity {

    TextView txt_customer_id;
    EditText edt_customer_id;
    EditText edt_customer_name;
    EditText edt_customer_phone;
    EditText edt_customer_email;
    EditText edt_customer_username;
    EditText edt_customer_password;
    Button btnNew;
    Button btnSave;
    Button btnRemove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_customer_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
        addEvent();
    }

    private void addEvent() {
        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process_save_customer();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process_save_customer();
            }
        });
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process_remove_customer();
            }
        });
    }

    private void process_remove_customer()
    {
        Intent intent=getIntent();
        String id=edt_customer_id.getText().toString();
        intent.putExtra("CUSTOMER_ID_REMOVE", id);
        setResult(600, intent);
        finish();
    }
    private void process_save_customer() {
        //lay du lieu tren giao dien va mo hinh hoa lai huong doi tipng Cus
        Customer c=new Customer();
        String id=edt_customer_id.getText().toString();
        if (id.trim().length()>0)
            c.setId(Integer.parseInt(id));
         c.setId(Integer.parseInt(edt_customer_id.getText().toString()));
         c.setName(edt_customer_name.getText().toString());
         c.setPhone(edt_customer_phone.getText().toString());
         c.setEmail(edt_customer_email.getText().toString());
         c.setUsername(edt_customer_username.getText().toString());
         c.setPassword(edt_customer_password.getText().toString());

         // lay intent tu man hinh goi no
        Intent intent=getIntent();
        //dong goi du lieu vao intent;
        intent.putExtra("NEW_CUSTOMER",c);
        setResult(500, intent); // ma tap tin gui di la 500
        // dong man hinh nay lai, de man hinh goi no nhan dc ket qua
        finish();
    }


    private void addViews() {
        txt_customer_id=findViewById(R.id.txt_customer_id);
         edt_customer_id=findViewById(R.id.edt_customer_id);
         edt_customer_name=findViewById(R.id.edt_customer_name);
         edt_customer_phone=findViewById(R.id.edt_customer_phone);
         edt_customer_email=findViewById(R.id.edt_customer_email);
         edt_customer_username=findViewById(R.id.edt_customer_username);
         edt_customer_password=findViewById(R.id.edt_customer_password);

         display_infor();

         btnNew=findViewById(R.id.btn_new);
         btnSave=findViewById(R.id.btn_save);
         btnRemove=findViewById(R.id.btn_remove);
    }
    private void display_infor(){
        //lay Intetn tu ben Customer Activity gui qua
        Intent intent =getIntent();
        // lay du lieu selected customer từ intent;
        Customer c=(Customer) intent.getSerializableExtra("SELECTED_CUSTOMER");
        //HIỂN thị thông tin Customer lên giao diện;
        if (c==null)  // khách hàng đòi thêm mới, mình muốn ẩn luôn hàng id tại mình tự ra id
        {
            txt_customer_id.setVisibility(View.GONE);
            edt_customer_id.setVisibility(View.GONE);
            return;
        }
        edt_customer_id.setText(c.getId()+"");
        //tất cả kiểu dữ liệu là sô mà gán vào text thì nó hiểu là idString trong String xml, vì vậy phải +""
        edt_customer_name.setText(c.getName());
        edt_customer_phone.setText(c.getPhone());
        edt_customer_email.setText(c.getEmail());
        edt_customer_username.setText(c.getUsername());
        edt_customer_password.setText(c.getPassword());
    }
}