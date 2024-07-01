package com.example.calculatorapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Double> numbers = new ArrayList<>();
    private ArrayList<String> operations = new ArrayList<>();
    private TextView screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        screen = findViewById(R.id.screen);

        Button num0 = findViewById(R.id.num0);
        Button num1 = findViewById(R.id.num1);
        Button num2 = findViewById(R.id.num2);
        Button num3 = findViewById(R.id.num3);
        Button num4 = findViewById(R.id.num4);
        Button num5 = findViewById(R.id.num5);
        Button num6 = findViewById(R.id.num6);
        Button num7 = findViewById(R.id.num7);
        Button num8 = findViewById(R.id.num8);
        Button num9 = findViewById(R.id.num9);

        Button percen = findViewById(R.id.percen);
        Button off = findViewById(R.id.off);
        Button ac = findViewById(R.id.ac);
        Button del = findViewById(R.id.del);
        Button div = findViewById(R.id.div);
        Button times = findViewById(R.id.times);
        Button min = findViewById(R.id.min);
        Button equal = findViewById(R.id.equal);
        Button plus = findViewById(R.id.plus);
        Button point = findViewById(R.id.point);

        ac.setOnClickListener(view -> {
            numbers.clear();
            operations.clear();
            screen.setText("0");
        });

        off.setOnClickListener(view -> finish());

        ArrayList<Button> numberButtons = new ArrayList<>();
        numberButtons.add(num0);
        numberButtons.add(num1);
        numberButtons.add(num2);
        numberButtons.add(num3);
        numberButtons.add(num4);
        numberButtons.add(num5);
        numberButtons.add(num6);
        numberButtons.add(num7);
        numberButtons.add(num8);
        numberButtons.add(num9);

        for (Button b : numberButtons) {
            b.setOnClickListener(view -> {
                if (screen.getText().toString().equals("0")) {
                    screen.setText(b.getText().toString());
                } else {
                    screen.append(b.getText().toString());
                }
            });
        }

        ArrayList<Button> operationButtons = new ArrayList<>();
        operationButtons.add(div);
        operationButtons.add(times);
        operationButtons.add(min);
        operationButtons.add(plus);

        for (Button b : operationButtons) {
            b.setOnClickListener(view -> {
                numbers.add(Double.parseDouble(screen.getText().toString()));
                operations.add(b.getText().toString());
                screen.setText("0");
            });
        }

        del.setOnClickListener(view -> {
            String num = screen.getText().toString();
            if (num.length() > 1) {
                screen.setText(num.substring(0, num.length() - 1));
            } else {
                screen.setText("0");
            }
        });

        point.setOnClickListener(view -> {
            if (!screen.getText().toString().contains(".")) {
                screen.append(".");
            }
        });

        percen.setOnClickListener(view -> {
            double value = Double.parseDouble(screen.getText().toString());
            value = value / 100;
            screen.setText(formatNumber(value));
        });

        equal.setOnClickListener(view -> {
            if (operations.isEmpty()) {
                Toast.makeText(MainActivity.this, "No operation selected", Toast.LENGTH_SHORT).show();
                return;
            }

            numbers.add(Double.parseDouble(screen.getText().toString()));

            double result = numbers.get(0);

            for (int i = 0; i < operations.size(); i++) {
                switch (operations.get(i)) {
                    case "÷":
                        result /= numbers.get(i + 1);
                        break;
                    case "x":
                        result *= numbers.get(i + 1);
                        break;
                    case "+":
                        result += numbers.get(i + 1);
                        break;
                    case "-":
                        result -= numbers.get(i + 1);
                        break;
                    default:
                        Toast.makeText(MainActivity.this, "Invalid operation", Toast.LENGTH_SHORT).show();
                        return;
                }
            }

            screen.setText(formatNumber(result));
            numbers.clear();
            operations.clear();
        });
    }

    private String formatNumber(double number) {
        if (number == (long) number) {
            return String.format("%d", (long) number);
        } else {
            return String.format("%s", number);
        }
    }
}
