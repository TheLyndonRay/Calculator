package com.solaris.lyndon.calculator;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class SecondActivity extends Activity {

    Button pickDate, save, load;

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
        SharedPreferences sp = getSharedPreferences("personData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        EditText etName = (EditText)findViewById(R.id.second_name);
        String name = etName.getText().toString();

        editor.putString("name", name);
        editor.commit();

        Toast.makeText(getApplicationContext(), sp.getString("name", name), Toast.LENGTH_SHORT).show();
    }

    class EventHandler implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.save :
                    saveData();

                break;
            }
        }

    }

}
