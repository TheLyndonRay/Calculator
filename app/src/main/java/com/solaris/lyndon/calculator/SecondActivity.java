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

import java.util.Calendar;


public class SecondActivity extends Activity {

    Button pickDate, save, load;
    SharedPreferences sp = getSharedPreferences("personData", MODE_PRIVATE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //Intent intentThatStartedThisActivity = getIntent();


        EventHandler eventHandler = new EventHandler();
        pickDate = (Button)findViewById(R.id.pick_date);
        save = (Button)findViewById(R.id.save);
        load = (Button)findViewById(R.id.load);

        pickDate.setOnClickListener(eventHandler);
        save.setOnClickListener(eventHandler);
        load.setOnClickListener(eventHandler);

    }

    @Override
    public void onStop() {
        super.onStop();

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

    public void saveData(){

        SharedPreferences.Editor editor = sp.edit();

        EditText etName = (EditText)findViewById(R.id.second_name);
        String name = etName.getText().toString();

        EditText etAge = (EditText)findViewById(R.id.second_age);
        Integer age = Integer.valueOf(etAge.getText().toString());

        editor.putString("name", name);
        editor.putInt("age", age);
        editor.commit();

        Toast.makeText(getApplicationContext(), sp.getString("name", name) + " - " + sp.getInt("age", age) + " has been saved!", Toast.LENGTH_SHORT).show();

    }

    public void loadData() {

        EditText etName = (EditText)findViewById(R.id.second_name);
        EditText etAge = (EditText)findViewById(R.id.second_age);

        etName.setText("" , TextView.BufferType.EDITABLE);
        etAge.setText("", TextView.BufferType.EDITABLE);

    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }

    class EventHandler implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.save :
                    saveData();

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
