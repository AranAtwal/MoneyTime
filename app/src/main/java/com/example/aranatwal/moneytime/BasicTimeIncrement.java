package com.example.aranatwal.moneytime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.text.DecimalFormat;

public class BasicTimeIncrement extends AppCompatActivity {

    Thread thread;

    int salary_amount;
    private int money_per_second;
    private int total_money_earned;
    TextView salary_counter;
    TextView incrementer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_time_increment);

        Intent intent = getIntent();

        salary_amount = intent.getExtras().getInt("salary");


        salary_counter = findViewById(R.id.salary_counter);
        incrementer = findViewById(R.id.incrementer);

        salary_counter.setText(String.valueOf(salary_amount));

        total_money_earned = 0;
        money_per_second = 0;

//        money_per_second = (salary_amount/1000)/(60*60*24*365); //salary into pounds divided by seconds in a year
        money_per_second = 1;

        count_money(money_per_second);


    }

    public void count_money(final int money_per_sec) {

        thread = new Thread() {

            @Override
            public void run() {
                try {
                    while (!thread.isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                total_money_earned += money_per_sec;

//                                if(total_money_earned>0.001) {

//                                DecimalFormat df = new DecimalFormat("#.####");
                                incrementer.setText("£" + total_money_earned);


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
