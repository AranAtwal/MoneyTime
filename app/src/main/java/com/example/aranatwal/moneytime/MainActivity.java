package com.example.aranatwal.moneytime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    Thread thread;

    Button salary_enter;
    NumberPicker salary_input;
    TextView salary_counter;
    TextView incrementer;

    float salary_amount;
    private float money_per_second;
    private float total_money_earned;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        salary_enter = findViewById(R.id.salary_enter);
        salary_input = findViewById(R.id.salary_input);
        salary_counter = findViewById(R.id.salary_counter);
        incrementer = findViewById(R.id.incrementer);

        salary_input.setValue(25000);
        salary_input.setMinValue(0);
        salary_input.setMaxValue(1000000);


        salary_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(salary_input.getValue()<=0) {
                    Toast.makeText(getApplicationContext(), "Put something in the the input field", Toast.LENGTH_LONG).show();
                } else {

//                    if () {
//
//                    }
                    salary_amount = salary_input.getValue();
                    salary_counter.setText(String.valueOf(salary_amount));

                    total_money_earned = 0;
                    money_per_second = 0;

                    money_per_second = (salary_amount/1000)/(60*60*24*365); //salary into pounds divided by seconds in a year

                    count_money(money_per_second);




                }

            }
        });
    }


    public void count_money(final float money_per_sec) {

        thread = new Thread() {

            @Override
            public void run() {
                try {
                    while (!thread.isInterrupted()) {
                        Thread.sleep(1);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                total_money_earned += money_per_sec;

//                                if(total_money_earned>0.001) {

                                    DecimalFormat df = new DecimalFormat("#.####");
                                    incrementer.setText("£" + df.format(total_money_earned));


                                    Log.d("total", String.valueOf(total_money_earned));
                                    Log.d("per_sec", String.valueOf(money_per_sec));
//                                }

                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        thread.start();

    }


}
