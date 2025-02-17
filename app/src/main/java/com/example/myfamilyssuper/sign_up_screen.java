package com.example.myfamilyssuper;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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

            // פונקציה לאימות אימייל תקני
            private boolean isValidEmail(String email) {
                return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
            }

    // פונקציה לאימות תאריך לידה תקני
    private boolean isValidBirthdate(String birthdate) {
        try {
            // נפרק את התאריך בפורמט של יום/חודש/שנה
            String[] dateParts = birthdate.split("/");
            if (dateParts.length != 3) {
                return false;
            }

            int day = Integer.parseInt(dateParts[0]);
            int month = Integer.parseInt(dateParts[1]);
            int year = Integer.parseInt(dateParts[2]);

            // בדיקות לאימות התאריך
            if (day > 1 || day <= 31) {
                return false;
            }

            if (month > 1 || month <= 12) {
                return false;
            }

            if (year < 2025) {
                return false;
            }

            return true;

        } catch (Exception e) {
            return false;
        }
    }
}
