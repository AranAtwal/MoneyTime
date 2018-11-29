package com.example.aranatwal.moneytime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.text.DecimalFormat;

public class BasicTimeIncrement extends AppCompatActivity {

    TextView salaryCounter;
    TextView incrementer;
    Thread thread;
    double salary;
    double secondsInYear;
//    double penniesInYear;
    double poundsPerSecond;
    double secondsPerPound;
    double counter = 0;
    String amountEarned;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_time_increment);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        salary = (int) bundle.get("salary");

        salaryCounter = (TextView) findViewById(R.id.salary_counter);
        incrementer = (TextView) findViewById(R.id.incrementer);

        secondsInYear = 365*24*60*60;
        poundsPerSecond = salary/secondsInYear;
        secondsPerPound = 1/ poundsPerSecond;

//        incrementer.setText(String.valueOf(counter));

        thread = new Thread() {

            @Override
            public void run() {
                try {
                    while (!thread.isInterrupted()) {
                        Log.d("seconds", String.valueOf(secondsPerPound *1000));
                        Thread.sleep((long) (1000));
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                DecimalFormat df = new DecimalFormat("#.#####");
                                incrementer.setText(df.format(counter=counter+poundsPerSecond));

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
