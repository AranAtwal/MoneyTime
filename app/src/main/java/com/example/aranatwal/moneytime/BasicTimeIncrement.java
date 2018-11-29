package com.example.aranatwal.moneytime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class BasicTimeIncrement extends AppCompatActivity {

    TextView salaryCounter;
    TextView incrementer;
    Thread thread;
    double salary;
    double secondsInYear;
    double penniesInYear;
    double penniesPerSecond;
    double secondsPerPenny;
    int counter = 0;

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
        penniesInYear = salary*100;
        penniesPerSecond = penniesInYear/secondsInYear;
        secondsPerPenny = 1/penniesPerSecond;

//        incrementer.setText(String.valueOf(counter));

        thread = new Thread() {

            @Override
            public void run() {
                try {
                    while (!thread.isInterrupted()) {
                        Log.d("seconds", String.valueOf(secondsPerPenny*1000));
                        Thread.sleep((long) (secondsPerPenny*1000));
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // update TextView here!
                                incrementer.setText(String.valueOf(counter++));

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
