package com.example.honor.lab4;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Type;
import java.util.List;

import com.example.honor.lab4.Product.GlobalProduct;
import com.example.honor.lab4.Product.Manage;
import com.example.honor.lab4.Product.Product;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class CatalogActivity extends AppCompatActivity {
    private com.example.honor.lab4.databinding.ActivityCatalogBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_catalog);
        ShowAllProduct();
        binding.linLayout.getBaseline();
        binding.btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetCreateProduct();
            }
        });
        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetSearchProduct();
            }
        });
        binding.btnSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetSortList();
            }
        });
    }
    protected void GetSortList(){
        Manage.Sort(GlobalProduct.productList);
    }
    protected void GetSearchProduct(){
        Intent intent = new Intent(this, ProductSearchActivity.class);
        startActivity(intent);
    }
    protected void GetCreateProduct(){
        Intent intent = new Intent(this, ProductActivity.class);
        startActivity(intent);
    }
    protected  void ShowAllProduct(){
        String json = Manage.ReadFile(getApplicationContext());
        if(json != "") {
            Type itemsMapType = new TypeToken<List<Product>>() {}.getType();
            GlobalProduct.productList = new Gson().fromJson(json, itemsMapType);
        }
        LayoutInflater ltInflater = getLayoutInflater();
        for (Product product: GlobalProduct.productList) {
            View item = ltInflater.inflate(R.layout.item, binding.linLayout, false);
            TextView iName = (TextView) item.findViewById(R.id.iName);
            iName.setText(product.getName());
            TextView iCount = (TextView) item.findViewById(R.id.iCount);
            iCount.setText(product.getCount());
            TextView iPrice = (TextView) item.findViewById(R.id.iPrice);
            iPrice.setText(product.getPrice());
            TextView iCategory = (TextView) item.findViewById(R.id.iCategory);
            iCategory.setText(product.getCategory());
            ImageView iImage = (ImageView) item.findViewById(R.id.iImage);
            iImage.setImageBitmap(Manage.StringToBitMap(product.getImage()));
            item.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
            binding.linLayout.addView(item);
        }
    }
//    protected void onPause(){
//        super.onPause();
//        String json = new Gson().toJson(GlobalProduct.productList);
//        Manage.WriteFile(getApplicationContext(), json);
//    }
//
//    protected void onStop(){
//        super.onStop();
//
//    }
}
