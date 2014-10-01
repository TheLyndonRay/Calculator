package com.solaris.lyndon.calculator;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import java.util.Calendar;

public class SecondActivity extends Activity {

    private Button pickDate, save, load;
    private SharedPreferences sp;
    private EditText etName;
    private EditText etAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        EventHandler eventHandler = new EventHandler();
        pickDate = (Button)findViewById(R.id.pick_date);
        save = (Button)findViewById(R.id.save);
        load = (Button)findViewById(R.id.load);
        etName = (EditText)findViewById(R.id.second_name);
        etAge = (EditText)findViewById(R.id.second_age);

        pickDate.setOnClickListener(eventHandler);
        save.setOnClickListener(eventHandler);
        load.setOnClickListener(eventHandler);

        sp = getSharedPreferences("personData", MODE_PRIVATE);

    }

    @Override
    public void onStop() {
        super.onStop();
        String name = etName.getText().toString();
        String age = etAge.getText().toString();

        if (name == null || name.equals("") && age == null || age.equals("")){

            setResult(RESULT_CANCELED);

        } else {

            Intent intent = new Intent();
            intent.putExtra("name", name);
            intent.putExtra("age", age);

            setResult(RESULT_OK, intent);

        }

        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.second, menu);
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
    public void onBackPressed() {

        String name = etName.getText().toString();
        String age = etAge.getText().toString();

        if (name == null || name.equals("") && age == null || age.equals("")){

            setResult(RESULT_CANCELED);

        } else {

            Intent intent = new Intent();
            intent.putExtra("name", name);
            intent.putExtra("age", age);

            setResult(RESULT_OK, intent);

        }

        finish();
    }

    protected void saveData(){

        SharedPreferences.Editor editor = sp.edit();

        String name = etName.getText().toString();
        Integer age = Integer.valueOf(etAge.getText().toString());

        editor.putString("name", name);
        editor.putInt("age", age);

        if (editor.commit()){
            Toast.makeText(getApplicationContext(), sp.getString("name", "") + " - " + sp.getInt("age", 0) + " has been saved!", Toast.LENGTH_SHORT).show();
        }
    }

    protected void loadData() {

        String name = sp.getString("name", "");
        Integer age = sp.getInt("age", 0);

        etName.setText(name , TextView.BufferType.EDITABLE);
        etAge.setText(Integer.toString(age), TextView.BufferType.EDITABLE);

    }

    protected void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }

    class EventHandler implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            String name = etName.getText().toString();
            String age = etAge.getText().toString();

            switch (v.getId()){
                case R.id.save :

                    if (name.equals("") || age.equals("")){
                        Toast.makeText(getApplicationContext(), "Don't leave the stuff blank!", Toast.LENGTH_SHORT).show();
                    } else {
                        saveData();
                    }
                    break;
                case R.id.load :

                    loadData();

                    break;
                case R.id.pick_date :
                    showDatePickerDialog(findViewById(R.id.pick_date));

                    break;
            }
        }
    }

    // Makes a date picker pop up as a Fragment
    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
        }
    }
}
