package com.example.myfamilyssuper;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;
import java.util.Date;

public class sign_up_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up_screen);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

                // קישורים לשדות הטופס
                EditText usernameEditText = findViewById(R.id.editTextText2); // שם משתמש
                EditText emailEditText = findViewById(R.id.editTextTextEmailAddress); // אימייל
                EditText birthdateEditText = findViewById(R.id.editTextDate); // תאריך לידה
                EditText passwordEditText = findViewById(R.id.editTextTextPassword); // סיסמה משפחתית
                Button signUpButton = findViewById(R.id.button); // כפתור הרשמה
                Button backButton = findViewById(R.id.back_button); // הכפתור ב-XML

                backButton.setOnClickListener(view -> {
                    // יצירת אינטנט למעבר למסך הבא
                    Intent intent = new Intent(sign_up_screen.this,LogInScreen.class);
                    startActivity(intent);
                });


                // אימות נתונים בעת לחיצה על כפתור ההרשמה
                signUpButton.setOnClickListener(v -> {
                    String username = usernameEditText.getText().toString().trim();
                    String email = emailEditText.getText().toString().trim();
                    String birthdate = birthdateEditText.getText().toString().trim();
                    String password = passwordEditText.getText().toString().trim();

                    // בדיקת אם שם המשתמש לא ריק
                    if (TextUtils.isEmpty(username)) {
                        usernameEditText.setError("אנא הכנס שם משתמש");
                        return;
                    }

                    // בדיקת אם האימייל לא ריק ושהוא תקני
                    if (TextUtils.isEmpty(email)) {
                        emailEditText.setError("אנא הכנס אימייל");
                        return;
                    }

                    if (!isValidEmail(email)) {
                        emailEditText.setError("אימייל לא תקין");
                        return;
                    }

                    // בדיקת אם תאריך הלידה לא ריק
                    if (TextUtils.isEmpty(birthdate)) {
                        birthdateEditText.setError("אנא הכנס תאריך לידה");
                        return;
                    }

                    // בדיקת אם הסיסמה לא ריקה ושהיא באורך מינימלי
                    if (TextUtils.isEmpty(password)) {
                        passwordEditText.setError("אנא הכנס סיסמה");
                        return;
                    }

                    if (password.length() < 6) {
                        passwordEditText.setError("הסיסמה חייבת להיות באורך של לפחות 6 תווים");
                        return;
                    }

                    // אם כל הבדיקות עברו בהצלחה, לעבור למסך הבא
                    Intent intent = new Intent(sign_up_screen.this, LogInScreen.class); // להחליף ל-Activity הבא
                    startActivity(intent);
                });



    }

    // פונקציה לאימות תאריך הלידה
    private boolean isValidBirthdate(String birthdate) {
        // תאריך הלידה בתצורת DD/MM/YYYY
        String[] parts = birthdate.split("/");

        if (parts.length != 3) {
            return false;
        }

        try {
            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);

            // קבלת התאריך הנוכחי
            Calendar calendar = Calendar.getInstance();
            int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
            int currentMonth = calendar.get(Calendar.MONTH) + 1; // החודש הוא בין 0 ל-11
            int currentYear = calendar.get(Calendar.YEAR);

            // בדיקה אם התאריך תקין
            if (day < 1 || day > 31 || month < 1 || month > 12 || year > 2025 || year < 1900) {
                return false;
            }

            // בדיקה אם התאריך נמצא בעבר
            if (year > currentYear || (year == currentYear && month > currentMonth) ||
                    (year == currentYear && month == currentMonth && day > currentDay)) {
                return false;
            }

            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // פונקציה לאימות אימייל תקני
    private boolean isValidEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }


}


