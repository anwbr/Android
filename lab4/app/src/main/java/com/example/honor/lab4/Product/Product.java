package com.example.honor.lab4.Product;

import android.graphics.Bitmap;

/**
 * Created by honor on 04.10.2017.
 */

public class Product {
    public String Name;
    public String Category;
    public String Price;
    public String Image;
    public String Count;
    //region Getter and Setter
    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
    public String getCount() {
        return Count;
    }

    public void setCount(String count) {
        Count = count;
    }


    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
    //endregion
    public Product(){
    }
}
