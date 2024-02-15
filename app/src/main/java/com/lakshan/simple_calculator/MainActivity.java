package com.lakshan.simple_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    double firstNum = 0;
    String operation = "";
    boolean isNewOperation = true;
    StringBuilder currentExpression = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        Button on = findViewById(R.id.on);
        Button off = findViewById(R.id.off);
        Button del = findViewById(R.id.delete);
        Button AC = findViewById(R.id.ac);
        Button div = findViewById(R.id.devide);
        Button mul = findViewById(R.id.multiply);
        Button sub = findViewById(R.id.subtraction);
        Button add = findViewById(R.id.add);
        Button sum = findViewById(R.id.equal);
        Button point = findViewById(R.id.dot);

        TextView screen = findViewById(R.id.screen);

        AC.setOnClickListener(view -> {
            firstNum = 0;
            operation = "";
            isNewOperation = true;
            currentExpression.setLength(0);
            screen.setText("0");
        });

        off.setOnClickListener(view -> screen.setVisibility(View.GONE));
        on.setOnClickListener(view -> {
            screen.setVisibility(View.VISIBLE);
            screen.setText("0");
        });

        ArrayList<Button> nums = new ArrayList<>();
        nums.add(num0);
        nums.add(num1);
        nums.add(num2);
        nums.add(num3);
        nums.add(num4);
        nums.add(num5);
        nums.add(num6);
        nums.add(num7);
        nums.add(num8);
        nums.add(num9);

        for (Button b : nums) {
            b.setOnClickListener(view -> {
                if (isNewOperation) {
                    currentExpression.setLength(0); // Clear the expression if it's a new operation
                    isNewOperation = false;
                }
                currentExpression.append(b.getText().toString());
                screen.setText(currentExpression.toString());
            });
        }

        ArrayList<Button> operators = new ArrayList<>();
        operators.add(div);
        operators.add(mul);
        operators.add(sub);
        operators.add(add);

        for (Button b : operators) {
            b.setOnClickListener(view -> {
                if (!isNewOperation) {
                    firstNum = Double.parseDouble(screen.getText().toString());
                    operation = b.getText().toString();
                    currentExpression.append(" " + operation + " ");
                    screen.setText(currentExpression.toString());
                    isNewOperation = true;
                }
            });
        }

        del.setOnClickListener(view -> {
            String num = screen.getText().toString();
            if (num.length() > 1) {
                currentExpression.deleteCharAt(currentExpression.length() - 1);
                screen.setText((num.substring(0, num.length() - 1)));
            } else if (num.length() == 1 && !num.equals("0")) {
                screen.setText("0");
            }
        });

        point.setOnClickListener(view -> {
            if (!screen.getText().toString().contains(".")) {
                currentExpression.append(".");
                screen.setText(currentExpression.toString());
            }
        });

        sum.setOnClickListener(view -> {
            if (!operation.isEmpty()) {
                double secondNum = Double.parseDouble(screen.getText().toString());
                double result = 0;
                switch (operation) {
                    case "/":
                        result = firstNum / secondNum;
                        break;
                    case "x":
                        result = firstNum * secondNum;
                        break;
                    case "+":
                        result = firstNum + secondNum;
                        break;
                    case "-":
                        result = firstNum - secondNum;
                        break;
                }
                screen.setText(String.valueOf(result));
                isNewOperation = true;
                firstNum = result;
                operation = "";
                currentExpression.setLength(0);
            }
        });
    }
}
