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
    TextView name;

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
        second_activity = (Button)findViewById(R.id.next);

        add_button.setOnClickListener(buttonHandler);
        subtract_button.setOnClickListener(buttonHandler);
        multiply_button.setOnClickListener(buttonHandler);
        divide_button.setOnClickListener(buttonHandler);
        fact_button.setOnClickListener(buttonHandler);
        second_activity.setOnClickListener(buttonHandler);

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
            name = (TextView)findViewById(R.id.name);
            name.setText(data.getStringExtra("name"));

        } else {
            Toast.makeText(this, "No data received", Toast.LENGTH_SHORT).show();
        }

    }

    public void startSecondActivity(View view){

        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivityForResult(intent, GET_NAME_REQUEST_CODE);
    }

    class EventHandler implements View.OnClickListener {
        @Override
        public void onClick(View v){

            EditText result = (EditText)findViewById(R.id.result);
            EditText firstNumber = (EditText)findViewById(R.id.num1);
            EditText secondNumber = (EditText)findViewById(R.id.num2);

            Integer answer;

            switch (v.getId()){
                case R.id.add :

                    answer = Integer.valueOf(firstNumber.getText().toString()) + Integer.valueOf(secondNumber.getText().toString());
                    result.setText(answer.toString(), TextView.BufferType.EDITABLE);

                    break;
                case R.id.subtract :

                    answer = Integer.valueOf(firstNumber.getText().toString()) - Integer.valueOf(secondNumber.getText().toString());
                    result.setText(answer.toString(), TextView.BufferType.EDITABLE);

                    break;
                case R.id.multiply :

                    answer = Integer.valueOf(firstNumber.getText().toString()) * Integer.valueOf(secondNumber.getText().toString());
                    result.setText(answer.toString(), TextView.BufferType.EDITABLE);

                    break;
                case R.id.divide :

                    answer = Integer.valueOf(firstNumber.getText().toString()) / Integer.valueOf(secondNumber.getText().toString());
                    result.setText(answer.toString(), TextView.BufferType.EDITABLE);

                    break;
                case R.id.factorial :

                    Integer input = Integer.valueOf(firstNumber.getText().toString());
                    Integer factorial = 1;
                    for (int i = 1; i <= input; i++) {
                        factorial = factorial * i;
                    }

                    result.setText(factorial.toString(), TextView.BufferType.EDITABLE);

                    break;
                case R.id.next :

                    startSecondActivity(second_activity);

                    break;
            }
        }
    }

}
