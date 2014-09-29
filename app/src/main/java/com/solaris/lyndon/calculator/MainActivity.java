package com.solaris.lyndon.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity {

    Button add_button, subtract_button, multiply_button, divide_button, fact_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EventHandler buttonHandler = new EventHandler();
        add_button = (Button)findViewById(R.id.add);
        subtract_button = (Button)findViewById(R.id.subtract);
        multiply_button = (Button)findViewById(R.id.multiply);
        divide_button = (Button)findViewById(R.id.divide);
        fact_button = (Button)findViewById(R.id.factorial);

        add_button.setOnClickListener(buttonHandler);
        subtract_button.setOnClickListener(buttonHandler);
        multiply_button.setOnClickListener(buttonHandler);
        divide_button.setOnClickListener(buttonHandler);
        fact_button.setOnClickListener(buttonHandler);

    }

    class EventHandler implements View.OnClickListener {
        @Override
        public void onClick(View v){

            EditText result = (EditText)findViewById(R.id.result);
            EditText firstNumber = (EditText)findViewById(R.id.num1);
            EditText secondNumber = (EditText)findViewById(R.id.num2);

            Double answer;

            switch (v.getId()){
                case R.id.add :

                    answer = Double.valueOf(firstNumber.toString()) + Double.valueOf(secondNumber.toString());
                    result.setText(answer.toString());

                    break;
                case R.id.subtract :

                    answer = Double.valueOf(firstNumber.toString()) - Double.valueOf(secondNumber.toString());
                    result.setText(answer.toString());

                    break;
                case R.id.multiply :

                    answer = Double.valueOf(firstNumber.toString()) * Double.valueOf(secondNumber.toString());
                    result.setText(answer.toString());

                    break;
                case R.id.divide :

                    answer = Double.valueOf(firstNumber.toString()) / Double.valueOf(secondNumber.toString());
                    result.setText(answer.toString());

                    break;
                case R.id.factorial :

                    Integer input = Integer.valueOf(firstNumber.toString());
                    Integer factorial = 1;
                    for (int i = 1; i <= input; i++) {
                        factorial = factorial * i;
                    }

                    result.setText(factorial.toString());

                    break;

            }
        }
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

    public int buttonEventHandler(Button button){

        return 2;
    }
}
