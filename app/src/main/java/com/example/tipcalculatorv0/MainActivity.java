package com.example.tipcalculatorv0;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    //Tip Calculator Class Reference (right clicked and created class)
    private TipCalculator tipCalc;
    private final NumberFormat money = NumberFormat.getCurrencyInstance(); // Number reference that will help format numbers
    private EditText billEditText;
    private EditText tipEditText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tipCalc = new TipCalculator(.17f,100f);
        setContentView(R.layout.activity_main);

        billEditText = findViewById(R.id.et_Bill_Amount);
        tipEditText = findViewById(R.id.et_Enter_Tip);
        //created inner class
        TextChangeHandler tch = new TextChangeHandler();
        billEditText.addTextChangedListener(tch);
        tipEditText.addTextChangedListener(tch);
    }

    // Called when the user clicks on the Calculate button
    public void calculate (){
        // Create a reference to the log
        // tells the log that the feedback is coming from the mainactivity class
        // we are going to record some data on the view
        // Note: had to import log and view class (right click, import class)
        // Log.w("MainActivity", "v = " + v);

        //Set up references to EditText fields
        /*
        EditText billEditText = findViewById(R.id.et_Bill_Amount);
        EditText tipEditText = findViewById(R.id.et_Enter_Tip);
         */




        // convert billEdit text and tipEditText to a string
        String billString = billEditText.getText().toString();
        String tipString = tipEditText.getText().toString();


        TextView tipTextView = findViewById(R.id.tv_Tip_Total);
        TextView totalTextView = findViewById(R.id.tv_Bill_Total);

        try {
            // Convert billString and tipString to floats
            float billAmount = Float.parseFloat(billString);
            int tipPercent = Integer.parseInt(tipString);

            // Update the model
            tipCalc.setBill(billAmount);
            tipCalc.setTip(.01f * tipPercent);

            // Ask Model to calculate tip and total amounts
            float tip = tipCalc.tipAmount();
            float total = tipCalc.totalAmount();

            // Update the view with formatted tip and total amounts
            tipTextView.setText(money.format(tip));
            totalTextView.setText(money.format(total));
        }catch (NumberFormatException nfe){
            // alert message
        }

    }

    private class TextChangeHandler implements TextWatcher {


        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            calculate();
        }
    }
}