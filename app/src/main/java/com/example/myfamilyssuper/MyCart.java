package com.example.myfamilyssuper;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MyCart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_my_cart);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;



class MyCartActivity extends AppCompatActivity {
     @Override
     protected void onCreate(Bundle savedInstanceState) {
           super.onCreate(savedInstanceState);
           setContentView(R.layout.activity_my_cart);

                    // איתור הכפתור מתוך הקובץ XML
           @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button productButton = findViewById(R.id.product_button);

                    // הגדרת מאזין ללחיצה על הכפתור
           productButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                            // יצירת אינטנט למעבר ל-ProductsActivity
           Intent intent = new Intent(MyCart.this, products.class);
           startActivity(intent); // התחלת הפעילות החדשה
                        }
                    });
                }
            }



        });
    }
}