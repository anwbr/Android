package com.example.honor.lab4;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.honor.lab4.Product.GlobalProduct;
import com.example.honor.lab4.Product.Manage;
import com.example.honor.lab4.Product.Product;
import com.google.gson.Gson;

import java.util.ArrayList;

public class ProductPresearchActivity extends AppCompatActivity {
    private com.example.honor.lab4.databinding.ActivityProductPresearchBinding binding;
    private Product product = new Product();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_presearch);
        GetValue();
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Manage.AddProductToList(product);
                String json = new Gson().toJson(GlobalProduct.productList, ArrayList.class);
                Manage.WriteFile(getApplicationContext(), json);
                GetCatalogActivity();
            }
        });
    }
    protected  void GetCatalogActivity() {
        Intent intent = new Intent(this, CatalogActivity.class);
        startActivity(intent);
    }
    protected void GetValue(){
        product.setName(GlobalProduct.Name.toString());
        product.setCount(GlobalProduct.Count.toString());
        product.setPrice(GlobalProduct.Price.toString());
        product.setCategory(GlobalProduct.Category.toString());
        product.setImage(GlobalProduct.Image);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_presearch);
        binding.textName.setText(GlobalProduct.Name.toString());
        binding.textCount.setText(GlobalProduct.Count.toString());
        binding.textPrice.setText(GlobalProduct.Price.toString());
        binding.textCategory.setText(GlobalProduct.Category.toString());
        binding.viewImage.setImageBitmap(Manage.StringToBitMap(GlobalProduct.Image));

    }

}
