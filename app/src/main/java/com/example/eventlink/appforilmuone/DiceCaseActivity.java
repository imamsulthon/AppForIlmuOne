package com.example.eventlink.appforilmuone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;

public class DiceCaseActivity extends AppCompatActivity {

    Button btnCalculate, btnClear;
    EditText editTextInputNumber;
    TextView displayViewSteps, displayViewResult;
    private FirebaseAnalytics firebaseAnalytics;

    public static String KEY_STEPS = "dice_steps";
    public static String KEY_RESULTS = "dice_results";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice_case);

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
                    int result = results(steps);
                    displaySteps(steps);
                    displayResult(result);

                    Bundle bundle = new Bundle();
                    bundle.putInt(KEY_STEPS, steps);
                    bundle.putInt(KEY_RESULTS, result);
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
    public int results(int steps) {
        /*
        The formula for this case is (i == step number that reached, f(i) = probability to rolling dice to the step):
        f(i) -->    if i = 0,       f(0) = 1;
                    if i = 1,       f(1) = f(0);
                    if i = 2,       f(2) = f(1) + f(0)
                    if i = 3,       f(3) = f(2) + f(1) + f(0)
                    if i = 4,       f(4) = f(3) + f(2) + f(1) + f(0)
                    if i = 5,       f(5) = f(4) + f(3) + f(2) + f(1) + f(0)
                    if i >= 6,      f(6) = f(6-i)+ f(5-i) + f(4-i) + f(3-i) + f(2-i) + f(1-i);
        */
        int result = 0;
        int f0 = 1;
        int f1 = 1;
        int f2 = 2;
        int f3 = 4;
        int f4 = 10;
        int f5 = 20;

        if (steps <= 5) {
            switch (steps) {
                case 1:
                    result = f1;
                    break;
                case 2:
                    result = f2;
                    break;
                case 3:
                    result = f3;
                    break;
                case 4:
                    result = f4;
                    break;
                case 5:
                    result = f5;
                    break;
                default:
                    result = f0;
            }
        }

//        int[] f = new int[steps];
//        int result = 0;
//        for (int i = 1; i < steps; i++) {
//            for (int j = 1; j<=6 && i-j>=0; j++) {
//                f[i] += f[i-j];
//                result = f[i];
//            }
//        }

        return result;
    }
    // endregion

    public int solution(int step) {
        int result = 0;
        int[] f = new int[step];
////        int f0 = 1;
////        int f1 = 1;
////        int f2 = 2;
////        int f3 = 4;
////        int f4 = 10;
////        int f5 = 20;
//
//        if (step <= 5) {
//            switch (step) {
//                case 1:
//                    f[1] = 1;
//                    result = f[1];
//                    break;
//                case 2:
//                    f[2] = 1;
//                    result = f[2];
//                    break;
//                case 3:
//                    result = f3;
//                    break;
//                case 4:
//                    result = f4;
//                    break;
//                case 5:
//                    result = f5;
//                    break;
//            }
//        } else {
//            for (int i = 6; i <= step; i++) {
//
//            }
//
//        }
        return result;
    }

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
