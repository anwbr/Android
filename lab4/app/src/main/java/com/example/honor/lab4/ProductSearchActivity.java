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

public class ProductSearchActivity extends AppCompatActivity {
    private com.example.honor.lab4.databinding.ActivityProductSearchBinding binding;
    private Product product = new Product();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_search);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_search);
        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.textName.setText("");
                binding.textCount.setText("");
                binding.textPrice.setText("");
                binding.textCategory.setText("");
                binding.viewImage.setImageBitmap(null);
                if(!binding.editName.getText().toString().isEmpty()){
                    product = Manage.SearchProduct(binding.editName.getText().toString());
                    if(product != null){
                        binding.textName.setText(product.getName());
                        binding.textCount.setText(product.getCount());
                        binding.textPrice.setText(product.getPrice());
                        binding.textCategory.setText(product.getCategory());
                        binding.viewImage.setImageBitmap(Manage.StringToBitMap(product.getImage()));
                    }
                }
            }
        });
        binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(product != null){
                    Manage.DeleteProduct(product);
                    String json = new Gson().toJson(GlobalProduct.productList, ArrayList.class);
                    Manage.WriteFile(getApplicationContext(), json);
                }
            }
        });
        binding.btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetHomeActivity();
            }

        });
    }
    protected void GetHomeActivity(){
        Intent intent = new Intent(this, CatalogActivity.class);
        startActivity(intent);
    }
}
