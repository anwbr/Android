package com.example.honor.lab4;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.honor.lab4.Product.GlobalProduct;
import com.example.honor.lab4.Product.Manage;

import java.io.IOException;

public class ProductImageActivity extends AppCompatActivity {
    static final int GALLERY_REQUEST = 1;
    private com.example.honor.lab4.databinding.ActivityProductImageandpriceBinding binding;
    private boolean FlagImage = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_imageandprice);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_imageandprice);
        binding.addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
            }
        });
        binding.btnPrevCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetProductActivity();
            }
        });
        binding.btnNextCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetProductPresearchActivity();
            }
        });
    }
    protected  void GetProductActivity() {
        Intent intent = new Intent(this, ProductActivity.class);
        startActivity(intent);
    }
    protected  void GetProductPresearchActivity() {
        if(!binding.editPrice.getText().toString().isEmpty() & !binding.editCount.getText().toString().isEmpty() & FlagImage == true){
            GlobalProduct.Count = binding.editCount.getText().toString();
            GlobalProduct.Price = binding.editPrice.getText().toString();
            Intent intent = new Intent(this, ProductPresearchActivity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Print value", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        Bitmap bitmap = null;
        switch (requestCode) {
            case GALLERY_REQUEST:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = imageReturnedIntent.getData();
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    binding.viewImage.setImageBitmap(bitmap);
                    GlobalProduct.Image = Manage.BitMapToString(bitmap);
                    FlagImage = true;
                }
                else FlagImage = false;
        }
    }
}
