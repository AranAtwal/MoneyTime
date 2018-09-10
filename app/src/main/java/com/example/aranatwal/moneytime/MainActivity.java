package com.example.aranatwal.moneytime;

import android.content.Intent;
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

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    Button salary_enter;
    NumberPicker salary_input;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        salary_enter = findViewById(R.id.salary_enter);
        salary_input = findViewById(R.id.salary_input);

        salary_input.setValue(25000);
        salary_input.setMinValue(0);
        salary_input.setMaxValue(1000000);


        salary_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(salary_input.getValue()<=0) {
                    Toast.makeText(getApplicationContext(), "Put something in the the input field", Toast.LENGTH_LONG).show();
                } else {

                    Intent intent = new Intent(MainActivity.this, BasicTimeIncrement.class);
                    intent.putExtra("salary", salary_input.getValue());
                    startActivity(intent);

                }

            }
        });
    }





}
