package com.example.musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Button plusButton, minusButton, addToCart;
    TextView num, price_num;
    Spinner spinner;
    ArrayList spinnerArrayList;
    ArrayAdapter spinnerAdapter;
    HashMap goodsMap;
    String goodsName;
    double price;
    int quantity = 0;
    ImageView guitar;
    EditText userNameEditTextName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        plusButton = findViewById(R.id.plusButton);
        minusButton = findViewById(R.id.minusButton);
        spinner = findViewById(R.id.spinner);
        price_num = findViewById(R.id.price_num);
        num = findViewById(R.id.num);
        guitar = findViewById(R.id.guitar);
        addToCart = findViewById(R.id.addToCart);
        userNameEditTextName = findViewById(R.id.editTextName);


        spinnerArrayList = new ArrayList();
        spinner.setOnItemSelectedListener(this);

        spinnerArrayList.add("guitar");
        spinnerArrayList.add("drams");
        spinnerArrayList.add("keyboard");

        spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerArrayList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        goodsMap = new HashMap();
        goodsMap.put("guitar", 450.0);
        goodsMap.put("drams", 1500.0);
        goodsMap.put("keyboard", 500.0);


        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity = quantity + 1;
                num.setText("" + quantity);
                price = (double) goodsMap.get(goodsName);
                price_num.setText(" " + quantity * price);
            }
        });

        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quantity >= 1) {
                    quantity = quantity - 1;
                    num.setText("" + quantity);
                } else {
                    num.setText("0");
                }
                price = (double) goodsMap.get(goodsName);
                price_num.setText(" " + quantity * price);

            }
        });

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Order order = new Order();
                order.userName = userNameEditTextName.getText().toString();
                order.goodsNAme = goodsName;
                order.quantity = quantity;
                order.orderPrice = quantity * price;
                order.price = price;

                Intent orderIntent = new Intent(MainActivity.this, OrderAcrivity.class );
                orderIntent.putExtra("UserName" , order.userName);
                orderIntent.putExtra("goodsName" , order.goodsNAme);
                orderIntent.putExtra("quantity" , order.quantity);
                orderIntent.putExtra("orderPrice" , order.orderPrice);
                orderIntent.putExtra("price" , order.price);
                startActivity(orderIntent);


            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        goodsName = spinner.getSelectedItem().toString();
        price = (double) goodsMap.get(goodsName);
        price_num.setText(" " + quantity * price);

        if (goodsName.equals("guitar")) {
            guitar.setImageResource(R.drawable.electric_guitar);
        } else if (goodsName.equals("drams")) {
            guitar.setImageResource(R.drawable.drams);
        } else if (goodsName.equals("keyboard")) {
            guitar.setImageResource(R.drawable.piano);

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}
