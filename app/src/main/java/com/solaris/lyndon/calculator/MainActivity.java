package com.solaris.lyndon.calculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {


    private final int GET_NAME_REQUEST_CODE = 0;

    Button add_button, subtract_button, multiply_button, divide_button, fact_button, second_activity;
    EditText name, age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MathEventHandler mathHandler = new MathEventHandler();
        NonMathEventHandler nonMathEventHandler = new NonMathEventHandler();
        add_button = (Button)findViewById(R.id.add);
        subtract_button = (Button)findViewById(R.id.subtract);
        multiply_button = (Button)findViewById(R.id.multiply);
        divide_button = (Button)findViewById(R.id.divide);
        fact_button = (Button)findViewById(R.id.factorial);
        second_activity = (Button)findViewById(R.id.next);

        add_button.setOnClickListener(mathHandler);
        subtract_button.setOnClickListener(mathHandler);
        multiply_button.setOnClickListener(mathHandler);
        divide_button.setOnClickListener(mathHandler);
        fact_button.setOnClickListener(mathHandler);

        second_activity.setOnClickListener(nonMathEventHandler);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            name = (EditText)findViewById(R.id.name);
            age  = (EditText)findViewById(R.id.age);
            name.setText(data.getStringExtra("name"));
            age.setText(data.getStringExtra("age"));

        } else {
            Toast.makeText(this, "No data received", Toast.LENGTH_SHORT).show();
        }

    }

    public void startSecondActivity(View view){

        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivityForResult(intent, GET_NAME_REQUEST_CODE);
    }

    class MathEventHandler implements View.OnClickListener {
        @Override
        public void onClick(View v){

            EditText result = (EditText)findViewById(R.id.result);
            EditText firstNumber = (EditText)findViewById(R.id.num1);
            EditText secondNumber = (EditText)findViewById(R.id.num2);

            Integer answer;

            if (firstNumber.getText().toString().equals("") || secondNumber.getText().toString().equals("") ) {
                Toast.makeText(getApplicationContext(), "Don't leave the stuff blank!", Toast.LENGTH_SHORT).show();

            } else {

                switch (v.getId()) {
                    case R.id.add:

                        answer = Integer.valueOf(firstNumber.getText().toString()) + Integer.valueOf(secondNumber.getText().toString());
                        result.setText(answer.toString(), TextView.BufferType.EDITABLE);

                        break;
                    case R.id.subtract:

                        answer = Integer.valueOf(firstNumber.getText().toString()) - Integer.valueOf(secondNumber.getText().toString());
                        result.setText(answer.toString(), TextView.BufferType.EDITABLE);

                        break;
                    case R.id.multiply:

                        answer = Integer.valueOf(firstNumber.getText().toString()) * Integer.valueOf(secondNumber.getText().toString());
                        result.setText(answer.toString(), TextView.BufferType.EDITABLE);

                        break;
                    case R.id.divide:

                        answer = Integer.valueOf(firstNumber.getText().toString()) / Integer.valueOf(secondNumber.getText().toString());
                        result.setText(answer.toString(), TextView.BufferType.EDITABLE);

                        break;
                    case R.id.factorial:

                        Integer input = Integer.valueOf(firstNumber.getText().toString());
                        Integer factorial = 1;
                        for (int i = 1; i <= input; i++) {
                            factorial = factorial * i;
                        }

                        result.setText(factorial.toString(), TextView.BufferType.EDITABLE);

                        break;
                    case R.id.next:

                        startSecondActivity(second_activity);

                        break;

                }
            }
        }
    }

    class NonMathEventHandler implements View.OnClickListener {
        @Override
        public void onClick(View v){

            switch (v.getId()) {
                case R.id.next:

                    startSecondActivity(second_activity);

                    break;
            }
        }
    }
}
