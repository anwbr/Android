package com.example.honor.lab2;

import android.databinding.DataBindingUtil;
import android.icu.text.DecimalFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private com.example.honor.lab2.databinding.SampleCalculatorBinding binding;
    private static final char ADDITION = '+';
    private static final char SUBTRACTION = '-';
    private static final char MULTIPLICATION = '*';
    private static final char DIVISION = '/';
    private boolean flag = false;
    private char CURRENT_ACTION;
    private double valueOne = Double.NaN;
    private double valueTwo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_calculator);
        binding = DataBindingUtil.setContentView(this, R.layout.sample_calculator);
        binding.buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!binding.editText.getText().toString().isEmpty()) {
                    computeCalculation();
                    CURRENT_ACTION = ADDITION;
                    binding.infoTextView.setText(valueOne + "+");
                    binding.editText.setText(null);
                }
            }
        });

        binding.buttonSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!binding.editText.getText().toString().isEmpty()) {
                    computeCalculation();
                    CURRENT_ACTION = SUBTRACTION;
                    binding.infoTextView.setText(valueOne + "-");
                    binding.editText.setText(null);
                }
            }
        });

        binding.buttonMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!binding.editText.getText().toString().isEmpty()) {
                    computeCalculation();
                    CURRENT_ACTION = MULTIPLICATION;
                    binding.infoTextView.setText(valueOne + "*");
                    binding.editText.setText(null);
                }
            }
        });

        binding.buttonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!binding.editText.getText().toString().isEmpty()) {
                    computeCalculation();
                    CURRENT_ACTION = DIVISION;
                    binding.infoTextView.setText(valueOne + "/");
                    binding.editText.setText(null);
                }
            }
        });
        binding.buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.infoTextView.setText("");
                binding.editText.setText(null);
                valueOne = Double.NaN;
                valueTwo = 0.00;
                CURRENT_ACTION = '0';
            }
        });

        binding.buttonPM.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!binding.editText.getText().toString().isEmpty()) {
                    binding.editText.setText(String.valueOf(Float.parseFloat(binding.editText.getText().toString()) * (-1)));
                }
            }
        });

        binding.buttonSqrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!binding.editText.getText().toString().isEmpty()) {
                    binding.infoTextView.setText(String.valueOf(Math.sqrt(Double.parseDouble(binding.editText.getText().toString()))));
                }
            }
        });
        binding.buttonSin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!binding.editText.getText().toString().isEmpty()) {
                    binding.infoTextView.setText(String.valueOf(Math.sin(Double.parseDouble(binding.editText.getText().toString()))));
                }
            }
        });
        binding.buttonCos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!binding.editText.getText().toString().isEmpty()) {
                    binding.infoTextView.setText(String.valueOf(Math.cos(Double.parseDouble(binding.editText.getText().toString()))));
                }
            }
        });
        binding.buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                computeCalculation();
                binding.infoTextView.setText(binding.infoTextView.getText().toString() + valueTwo + " = " + valueOne);
                valueOne = Double.NaN;
                CURRENT_ACTION = '0';
            }
        });
        binding.buttonPi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.editText.getText().toString().isEmpty()) {
                    binding.editText.setText(String.valueOf(3.14));
                }
            }
        });

        binding.buttonZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "0");
            }
        });
        binding.buttonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "1");
            }
        });
        binding.buttonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "2");
            }
        });
        binding.buttonThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "3");
            }
        });
        binding.buttonFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "4");
            }
        });
        binding.buttonFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "5");
            }
        });
        binding.buttonSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "6");
            }
        });
        binding.buttonSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "7");
            }
        });
        binding.buttonEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "8");
            }
        });
        binding.buttonNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "9");
            }
        });
        binding.buttonDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + ".");
            }
        });
    }
    private void computeCalculation() {
        if(!Double.isNaN(valueOne)) {
            valueTwo = Double.parseDouble(binding.editText.getText().toString());
            binding.editText.setText(null);

            if(CURRENT_ACTION == ADDITION)
                valueOne = this.valueOne + valueTwo;
            else if(CURRENT_ACTION == SUBTRACTION)
                valueOne = this.valueOne - valueTwo;
            else if(CURRENT_ACTION == MULTIPLICATION)
                valueOne = this.valueOne * valueTwo;
            else if(CURRENT_ACTION == DIVISION)
                valueOne = this.valueOne / valueTwo;
         }
        else {
            try {
                valueOne = Double.parseDouble(binding.editText.getText().toString());
            }
            catch (Exception e){}
        }
    }

}
