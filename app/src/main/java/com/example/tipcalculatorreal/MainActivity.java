package com.example.tipcalculatorreal;

import android.content.res.Resources;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.inputmethod.InputBinding;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tipCalculation();
    }

    private void tipCalculation() {
        EditText editTextAmount = findViewById(R.id.editBillAmount);
        Button button15 = findViewById(R.id.button15);
        Button button18 = findViewById(R.id.button18);
        Button button20 = findViewById(R.id.button20);
        TextView textViewResult = findViewById(R.id.defaultMessage);

        button15.setOnClickListener(v -> handleTipCalculation(editTextAmount, textViewResult, 15));
        button18.setOnClickListener(v -> handleTipCalculation(editTextAmount, textViewResult, 18));
        button20.setOnClickListener(v -> handleTipCalculation(editTextAmount, textViewResult, 20));
    }

    private void handleTipCalculation(EditText editTextAmount, TextView textViewResult, int percentage) {
        String inputAmount = editTextAmount.getText().toString();

        double amount = Double.parseDouble(inputAmount);
        String result = calculateTipAndTotal(amount, percentage);

        textViewResult.setText(result);
    }
    private String calculateTipAndTotal(double amount, int percentage) {
        double tip = amount * percentage / 100;
        double total = amount + tip;
        return String.format("Tip: $%.2f, Total Bill: $%.2f", tip, total);
    }


}