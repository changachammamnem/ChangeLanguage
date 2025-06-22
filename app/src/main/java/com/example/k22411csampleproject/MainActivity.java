package com.example.k22411csampleproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity
{
    ImageView imgEmployee;
    TextView txtEmployee;
    ImageView imgCustomer;
    TextView txtCustomer;
    ImageView imgProduct;
    TextView txtProduct;
    ImageView imgCatalog;
    TextView txtCatalog;
    ImageView imgAdvancedProduct;
    TextView txtAdvancedProduct;

    ImageView imgPayment;
    TextView txtPayment;

    ImageView imgOrder;
    TextView txtOrder;
    ImageView imgTelephone;
    TextView txtTelephone;
    ImageView imgMultiThreading;
    TextView txtMultiThreading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        addViews();
        addEvents();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void addEvents() {
        imgEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //gọi code mở màn hình quản trị nhân sự
                openEmployeeManagementActivity();
            }
        });
        txtEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //gọi code mở màn hình quản trị nhân sự
                openEmployeeManagementActivity();
            }
        });

        imgCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCustomerManagementActivity();
            }
        });
        txtCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCustomerManagementActivity();
            }
        });

        imgProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openProductManagementActivity();
            }
        });
        txtProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openProductManagementActivity();
            }
        });

        imgAdvancedProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAdvancedProductManagementActivity();
            }
        });
        txtAdvancedProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAdvancedProductManagementActivity();
            }
        });

        imgPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPaymentMethodActivity();
            }
        });
        txtPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPaymentMethodActivity();
            }
        });
        imgOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOrderViewerActivity();
            }
        });
        txtOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOrderViewerActivity();
            }
        });
        imgTelephone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTelephoneActivity();
            }
        });
        txtTelephone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTelephoneActivity();
            }
        });
        imgMultiThreading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMultiThreadingCategoriesActivity();
            }
        });
        txtMultiThreading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMultiThreadingCategoriesActivity();
            }
        });

    }

    private void openMultiThreadingCategoriesActivity() {
        Intent intent=new Intent(MainActivity.this, MultiThreadingCategoriesActivity.class);
        startActivity(intent);
    }

    private void openTelephoneActivity() {
        Intent intent=new Intent(MainActivity.this, TelephonieActivity.class);
        startActivity(intent);
    }

    private void openOrderViewerActivity() {
        Intent intent=new Intent(MainActivity.this, OrderViewerActivity.class);
        startActivity(intent);
    }

    private void openPaymentMethodActivity() {
        Intent intent=new Intent(MainActivity.this, PaymentMethodActivity.class);
        startActivity(intent);
    }

    private void openAdvancedProductManagementActivity() {
        Intent intent=new Intent(MainActivity.this, AdvancedProductActivity.class);
        startActivity(intent);
    }

    private void openProductManagementActivity() {
        Intent intent=new Intent(MainActivity.this, ProductManagementActivity.class);
        startActivity(intent);
    }

    void openEmployeeManagementActivity()
    {
        Intent intent=new Intent(MainActivity.this, EmployeeManagementMainActivity.class);
        startActivity(intent);
    }
    void openCustomerManagementActivity()
    {
        Intent intent=new Intent(MainActivity.this, CustomerManagementActivity2.class);
        startActivity(intent);
    }

    private void addViews() {
        imgEmployee=findViewById(R.id.imgEmployee);
        txtEmployee=findViewById(R.id.txtEmployee);
        imgCustomer=findViewById(R.id.imgCustomer);
        txtCustomer=findViewById(R.id.txtCustomer);
        imgProduct=findViewById(R.id.imgProduct);
        txtProduct=findViewById(R.id.txtProduct);
        imgAdvancedProduct=findViewById(R.id.imgAdvancedProduct);
        txtAdvancedProduct=findViewById(R.id.txtAdvancedProduct);
        imgPayment=findViewById(R.id.imgPayment);
        txtPayment=findViewById(R.id.txtPayment);
        imgOrder=findViewById(R.id.imgOrder);
        txtOrder=findViewById(R.id.txtOrder);
        imgTelephone=findViewById(R.id.img_telephone);
        txtTelephone=findViewById(R.id.txt_telephone);
        imgMultiThreading=findViewById(R.id.imgMultiThreading);
        txtMultiThreading=findViewById(R.id.txtMultiThreading);
    }
}