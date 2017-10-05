package com.example.honor.lab4;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.honor.lab4.Product.GlobalProduct;

public class ProductActivity extends AppCompatActivity {
    private com.example.honor.lab4.databinding.ActivityProductNameandcategoryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_nameandcategory);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_nameandcategory);
        GetSpinnerValue();
        binding.listCategories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> arg0, View v, int position, long id) {
                GlobalProduct.Category = binding.listCategories.getSelectedItem().toString();
            }
            public void onNothingSelected(AdapterView parent) {

            }
        });
        binding.btnNextCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetProductImageActivity();
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
    protected  void GetProductImageActivity() {
        if(!binding.editName.getText().toString().isEmpty()) {
            GlobalProduct.Name = binding.editName.getText().toString();
            Intent intent = new Intent(this, ProductImageActivity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Print name", Toast.LENGTH_LONG).show();
        }
    }
    protected void GetSpinnerValue(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.listCategories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.listCategories.setAdapter(adapter);
        binding.listCategories.setPrompt("Title");
        binding.listCategories.setSelection(adapter.getCount()-1);
    }
}
