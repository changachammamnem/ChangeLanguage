package com.example.k22411csampleproject;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.utilis.BMIResult;
import com.utilis.HealthCare;

import org.w3c.dom.Text;

public class EmployeeHealthcareActivity extends AppCompatActivity {
    EditText edtHeight;
    EditText edtWeight;
    Button btnCalculate;
    Button btnClear;
    Button btnClose;
    Text txtResult;
    View.OnClickListener myClick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_activity2_employee_healthcare);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        addViews();
        addEvents();
    }

    private void addEvents() {
        myClick =new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //kiem tra cac View su dung bien su kien o day
                if(view.equals(btnCalculate))
                {
                    //viewCalculate dang su dung su kien nay
                    double h=Double.parseDouble(edtHeight.getText().toString());
                    double w=Double.parseDouble(edtWeight.getText().toString());
                    BMIResult result= HealthCare.calculate(h,w,EmployeeHealthcareActivity.this);
                    txtResult.setTextContent(result.getBMI()+"=>"+result.getDescription());
                }
                else if(view.equals(btnClear))
                {
                    //view Clear dang su dung su kien nay
                    edtHeight.setText("");
                    edtWeight.setText("");
                    txtResult.setTextContent("");
                    edtHeight.requestFocus();
                }
                else if (view.equals(btnClose))
                {
                    // view Close dang su dung su kien nay
                    finish();
                }


            }
        };
        //gan bien su kien cho cac View (sharing events);
        btnCalculate.setOnClickListener(myClick);
        btnClear.setOnClickListener(myClick);
        btnClose.setOnClickListener(myClick);
    }


    private void addViews() {
        edtHeight=findViewById(R.id.edtHeight);
        edtWeight=findViewById(R.id.edtWeight);

        btnCalculate=findViewById(R.id.btnCalculate);
        btnClear=findViewById(R.id.btnClear);
        btnClose=findViewById(R.id.btnClose);

        txtResult=findViewById(R.id.txtResult);
    }
}