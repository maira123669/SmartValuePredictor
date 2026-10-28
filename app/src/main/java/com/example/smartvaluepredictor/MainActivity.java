package com.example.smartvaluepredictor;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText emailInput;
    private EditText passwordInput;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Connect Java variables to the login screen fields.
        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(v -> {
            String email = emailInput.getText().toString().trim();
            String password = passwordInput.getText().toString();

            // Validate the email field.
            if (email.isEmpty() ||
                    !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailInput.setError("Enter a valid email address");
                return;
            }

            // Validate the password field.
            if (password.trim().isEmpty()) {
                passwordInput.setError("Enter your password");
                return;
            }

            // Demo login: this checks the fields only.
            // It does not authenticate against a real account.
            Toast.makeText(
                    MainActivity.this,
                    "Login successful!",
                    Toast.LENGTH_SHORT
            ).show();

            // Open the AI prediction screen.
            Intent intent = new Intent(
                    MainActivity.this,
                    PredictionActivity.class
            );
            startActivity(intent);
        });
    }
}