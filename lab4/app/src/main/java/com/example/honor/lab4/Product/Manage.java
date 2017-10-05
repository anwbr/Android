package com.example.honor.lab4.Product;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.util.Base64;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by honor on 05.10.2017.
 */

public class Manage {
    private final static String filename = "ListProducts";
    public static void AddProductToList(Product product){
        GlobalProduct.productList.add(product);
    }
    public static void Sort(ArrayList<Product> listproduct){
        Collections.sort(listproduct, new Comparator<Product>() {
            @Override
            public int compare(Product t2, Product t1) {return t2.getName().compareTo(t1.getName());}
        });
    }
    @Nullable
    public static Product SearchProduct(String name){
        for (Product product:GlobalProduct.productList) {
            if(product.getName().compareTo(name) == 0){
                return product;
            }
        }
        return null;
    }
    public static void DeleteProduct(Product product){
        GlobalProduct.productList.remove(product);
    }
    public static void WriteFile(Context context, String json) {
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(context.openFileOutput(filename, MODE_PRIVATE)));
            bw.write(json);
            bw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String ReadFile(Context context) {
        String temp = "";
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(context.openFileInput(filename)));
            String str = "";
            while ((str = br.readLine()) != null) {
                temp += str;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return temp;
    }
    public static String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos = new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp= Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }
    public static Bitmap StringToBitMap(String encodedString){
        try {
            byte [] encodeByte=Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch(Exception e) {
            e.getMessage();
            return null;
        }
    }
}
