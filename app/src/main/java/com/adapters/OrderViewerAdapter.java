package com.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.k22411csampleproject.R;
import com.tuyetanh.model.OrderViewer;

public class OrderViewerAdapter extends ArrayAdapter<OrderViewer> {
    Activity context;
    int resource;
    public OrderViewerAdapter(@NonNull Activity context, int resource) {
        super(context, resource);
        this.context=context;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=this.context.getLayoutInflater();
        View item =inflater.inflate(this.resource, null);

        TextView txtOrderCode=item.findViewById(R.id.txtOrderCode);
        TextView txtOrderDate=item.findViewById(R.id.txtOrderDate);
        TextView txtEmployeeName=item.findViewById(R.id.txtEmployeeName);
        TextView txtCustomerName= item.findViewById(R.id.txtCustomerName);
        TextView txtTotalOrderValue=item.findViewById(R.id.txtTotalOrderValue);

        OrderViewer ov=getItem(position);

        txtOrderCode.setText(ov.getCode());
        txtOrderDate.setText(ov.getOrderDate());
        txtEmployeeName.setText(ov.getEmployeeName());
        txtCustomerName.setText(ov.getCustomerName());
        txtTotalOrderValue.setText(ov.getTotalOrderValue()+ " VND");

        return item;
    }

}
