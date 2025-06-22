package com.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.k22411csampleproject.R;
import com.tuyetanh.model.Product;

import org.w3c.dom.Text;

public class AdvancedProductAdapter extends ArrayAdapter<Product> {
    Activity context;
    int resource;
    public AdvancedProductAdapter(@NonNull Activity context, int resource) {
        super(context, resource);
        this.context=context;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        //nhan ban giao dien theo tung position ma du lieu dc duyet qua
        View item = inflater.inflate(this.resource, null);
        //luc nay toan bo view nam trong layout resource (item_advanced_product) se dc mo hinh hoa huong doi tuong va dc quan li boi bien item
        // nhu vay muon truy xuat toi view con thi phai thong qua bien item
        ImageView imgProduct = item.findViewById(R.id.imgProduct);
        TextView txtProductId = item.findViewById(R.id.txtProductID);
        TextView txtProductName = item.findViewById(R.id.txtProductName);
        TextView txtProductQuantity = item.findViewById(R.id.txtProductQuantity);
        TextView txtProductPrice = item.findViewById(R.id.txtProductPrice);
        ImageView imgCart = item.findViewById(R.id.imgCart);

        // lay mo hinh doi tuong dang dc nhan ban o vi tri position (doi so 1):
        Product p = getItem(position);

        // Rai du lieu cua Product len giao dien trong Item;
        imgProduct.setImageResource(p.getImage_id());
        txtProductId.setText(p.getId()+"");
        txtProductName.setText(p.getName());
        txtProductQuantity.setText(p.getQuantity()+"");
        txtProductPrice.setText(p.getPrice()+"(VND)");


        return item;
    }
//        return super.getView(position, convertView, parent);
    }
//}
