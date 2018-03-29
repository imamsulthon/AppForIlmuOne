package com.example.eventlink.appforilmuone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;

public class FibonacciCaseActivity extends AppCompatActivity {

    Button btnCalculate, btnClear;
    EditText editTextInputNumber;
    TextView displayViewSteps, displayViewResult;
    private FirebaseAnalytics firebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fibonacci_case);

        firebaseAnalytics = FirebaseAnalytics.getInstance(this);

        btnCalculate = findViewById(R.id.btn_calculate);
        btnClear = findViewById(R.id.btn_clear);
        editTextInputNumber = findViewById(R.id.et_input_number);
        displayViewSteps = findViewById(R.id.tv_steps);
        displayViewResult = findViewById(R.id.tv_result);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editTextInputNumber.getText().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Input a number", Toast.LENGTH_SHORT).show();
                } else {
                    int steps = Integer.parseInt(editTextInputNumber.getText().toString());
                    int result = rollNumber(steps);
                    displaySteps(steps);
                    displayResult(result);

                    Bundle bundle = new Bundle();
                    bundle.putInt("fib_steps", steps);
                    bundle.putInt("fib_result", result);
                    firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearDisplay();
                Toast.makeText(getApplicationContext(), "Result cleared", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // region formula
    public int rollNumber(int steps) {
        int result = 0;     // f(n)
        int fa = 1;         // f(n-1), for f(1) = 1;
        int fb = 0;         // f(n-2), for f(0) = 0;

        if (steps == 0 || steps == 1) {
            return steps;
        }

        for (int i = 2; i <= steps; i++) {
            result = fa + fb;       // f(n) = f(n-1) + f(n-2)
            fb = fa;                // for next loop, f(n-2) is last f(n-1)
            fa = result;            // for next loop, f(n-1) is last f(n)
        }
        return result;
    }
    // endregion

    public void displayResult(int result) {
        String number = String.valueOf(result);
        displayViewResult.setText(number);
    }

    public void displaySteps(int steps) {
        String number = String.valueOf(steps);
        displayViewSteps.setText(number);
    }

    public void clearDisplay() {
        displayViewSteps.setText("");
        displayViewResult.setText("");
        editTextInputNumber.setText(null);
    }
}
