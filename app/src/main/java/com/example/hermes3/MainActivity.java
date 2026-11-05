package com.example.hermes3;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;

public class MainActivity extends Activity {

    EditText number1, number2;
    TextView result;

    String operation = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        number1 = findViewById(R.id.number1);
        number2 = findViewById(R.id.number2);
        result = findViewById(R.id.result);

        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnSubtract = findViewById(R.id.btnSubtract);
        Button btnMultiply = findViewById(R.id.btnMultiply);
        Button btnDivide = findViewById(R.id.btnDivide);
        Button btnEqual = findViewById(R.id.btnEqual);
        Button btnRestart = findViewById(R.id.btnRestart);

        btnAdd.setOnClickListener(v -> operation = "+");
        btnSubtract.setOnClickListener(v -> operation = "-");
        btnMultiply.setOnClickListener(v -> operation = "*");
        btnDivide.setOnClickListener(v -> operation = "/");

        btnEqual.setOnClickListener(v -> calculate());

        btnRestart.setOnClickListener(v -> restart());
    }

    private void calculate() {

        String first = number1.getText().toString().trim();
        String second = number2.getText().toString().trim();

        if (first.isEmpty() || second.isEmpty()) {
            Toast.makeText(this,
                    "لطفاً هر دو عدد را وارد کنید",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if (operation.isEmpty()) {
            Toast.makeText(this,
                    "لطفاً یک عملگر انتخاب کنید",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        try {

            double a = Double.parseDouble(first.replace(',', '.'));
            double b = Double.parseDouble(second.replace(',', '.'));

            double answer;

            switch (operation) {

                case "+":
                    answer = a + b;
                    break;

                case "-":
                    answer = a - b;
                    break;

                case "*":
                    answer = a * b;
                    break;

                case "/":
                    if (b == 0) {
                        Toast.makeText(this,
                                "تقسیم بر صفر امکان پذیر نیست",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }
                    answer = a / b;
                    break;

                default:
                    return;
            }

            if (Double.isNaN(answer) || Double.isInfinite(answer)) {
                Toast.makeText(this,
                        "نتیجه قابل محاسبه نیست",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            result.setText(
                    BigDecimal.valueOf(answer)
                            .stripTrailingZeros()
                            .toPlainString()
            );

        } catch (NumberFormatException e) {

            Toast.makeText(this,
                    "لطفاً عدد معتبر وارد کنید",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void restart() {

        number1.setText("");
        number2.setText("");
        result.setText("0");

        operation = "";

        number1.requestFocus();

        Toast.makeText(this,
                "شروع مجدد",
                Toast.LENGTH_SHORT).show();
    }
}
