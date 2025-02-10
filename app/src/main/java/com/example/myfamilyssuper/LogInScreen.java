package com.example.myfamilyssuper;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LogInScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_log_in_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button buttonSignIn = (Button)findViewById(R.id.button_V);
        buttonSignIn.setOnClickListener(view -> {
            Intent i = new Intent(this,Start_Screen.class);
            startActivity(i);
        });

        Button buttonSignUp = (Button)findViewById(R.id.button_signup);
        buttonSignUp.setOnClickListener(view -> {
            Intent i = new Intent(this,sign_up_screen.class);
            startActivity(i);
        });

    }
}