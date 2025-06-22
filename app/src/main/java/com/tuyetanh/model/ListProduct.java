package com.tuyetanh.model;

import com.example.k22411csampleproject.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class ListProduct {
    ArrayList<Product> products;
    public ListProduct()
    {
        products=new ArrayList<>();
    }
    public ArrayList<Product> getProducts(){
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void generate_sample_dataset() {
        Category c1 = new Category(110, "Soft Drink", 1);
        Product p1 = new Product(1, "Coca Cola", 100, 10.0, "Nước ngot", R.mipmap.coca);
        Product p2 = new Product(2, "Pepsi", 120, 9.5, "Nước ngot", R.mipmap.pepsi);
        Product p3 = new Product(3, "7Up", 90, 8.0, "Nước ngot", R.mipmap.sevenup);
        Product p4 = new Product(4, "Fanta", 85, 8.5, "Nước ngot", R.mipmap.fanta);
        Product p5 = new Product(5, "Sprite", 95, 9.0, "Nước ngot", R.mipmap.sprite);
        products.add(p1);
        products.add(p2);
        products.add(p3);
        products.add(p4);
        products.add(p5);

    }

}
