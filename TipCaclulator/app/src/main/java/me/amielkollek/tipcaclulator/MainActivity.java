package me.amielkollek.tipcaclulator;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void calculateTip(View view){

        // find the input area and get its contents as a string
        EditText input =  (EditText) findViewById(R.id.bill);
        String inputBill = input.getText().toString();

        // if the area is empty, the display a message and return
        if (inputBill.matches("")) {
           Toast.makeText(this, "You did not enter a bill amount", Toast.LENGTH_SHORT).show();
           return;
        }

        // the input is not empty, so we cast the string to a float
        float bill = Float.valueOf(inputBill);

        // get the radio button objects to check if they are selected
        RadioButton low = (RadioButton) findViewById(R.id.tip_radio_low);
        RadioButton mid = (RadioButton) findViewById(R.id.tip_radio_mid);

        // declare our tip variable
        float tip;

        // calculate the tip according to button is selected
        if (low.isChecked())
            tip = bill * 13 / 100;
        else if (mid.isChecked())
            tip = bill * 15 / 100;
        else
            tip = bill * 18 / 100;


        // get the text view we are going to fill with the tip
        TextView tipView = (TextView) findViewById(R.id.display_tip);

        // set it to empty (in case this is the second time someone is calculating a tip
        // we want to make sure it is empty)
        tipView.setText(String.format(""));

        // fill it with the tip we calculated
        tipView.setText(String.format("Your tip will be $%.2f.", tip));
    }

}
