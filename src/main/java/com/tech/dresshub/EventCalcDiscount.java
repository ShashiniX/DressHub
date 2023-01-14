package com.tech.dresshub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EventCalcDiscount extends AppCompatActivity implements View.OnClickListener {

    //Here Variables are called
    private EditText priceTag;
    private EditText discountRate;
    private TextView savedAmount;
    private TextView discountedPrice;
    private android.widget.Button Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_calc_discount);

        // Creating Names for our variables
        Button = findViewById(R.id.calcbtn);
        Button.setOnClickListener(this);
        priceTag = findViewById(R.id.price);
        discountRate = findViewById(R.id.discount);
        savedAmount = findViewById(R.id.save_price);
        discountedPrice = findViewById(R.id.discounted_price);
    }

    //OnClick Method is created,happens when users clicks button
    @Override
    public void onClick(View v) {

// Here is the most important part of the code
//It is the calculating function
        if (v.getId() == R.id.calcbtn) {
            if (!priceTag.getText().toString().equals("") && !discountRate.getText().toString().equals("")) {
                double number = Double.parseDouble(priceTag.getText()
                        .toString());
                double percentage = Double.parseDouble(discountRate.getText()
                        .toString());

                if (percentage >= 0 && percentage <= 100) {
                    double savedprice = (percentage / 100.0) * number;
                    double discountedAmount = number - savedprice;
                    String svdPrice = String.valueOf(savedprice);
                    String disprice = String.valueOf(discountedAmount);

                    // Here the output of the equation is pulled to a text
                    savedAmount.setText("Saved : " + svdPrice);
                    discountedPrice.setText("Price After Discount : " + disprice);

                }

            } else {
// Display error if  -1 or 100+ number is chosen
                Toast.makeText(this, "Discount 0 to 100 %",
                        Toast.LENGTH_LONG).show();
            }
        }
        else {
            Toast.makeText(this, "Only fill in Price and Discount%",
                    Toast.LENGTH_LONG).show();
        }
    }

    public void btnBack(View view) {
        Intent intent = new Intent(this, AddEvents.class);
        startActivity(intent);
    }

}