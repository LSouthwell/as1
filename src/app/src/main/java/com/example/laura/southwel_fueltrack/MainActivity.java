package com.example.laura.southwel_fueltrack;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText dateTxt, stationTxt, odometerTxt, fuelGradeTxt, fuelAmountTxt, fuelUnitCostTxt, fuelTotalCostTxt;

    List<Entry> Entries = new ArrayList<Entry>();
    ListView entryListView;

    // variable for fuel sum
    float sum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dateTxt = (EditText) findViewById(R.id.txtDate);
        stationTxt = (EditText) findViewById(R.id.txtStation);
        odometerTxt = (EditText) findViewById(R.id.txtOdometer);
        fuelGradeTxt = (EditText) findViewById(R.id.txtFuelGrade);
        fuelAmountTxt = (EditText) findViewById(R.id.txtFuelAmount);
        fuelUnitCostTxt = (EditText) findViewById(R.id.txtFuelUnitCost);
        fuelTotalCostTxt = (EditText) findViewById(R.id.txtFuelTotalCost);

        entryListView = (ListView)findViewById(R.id.listView);

        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec tabSpec = tabHost.newTabSpec("creator");
        tabSpec.setContent(R.id.tabCreator);
        tabSpec.setIndicator("Creator");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("log");
        tabSpec.setContent(R.id.tabLog);
        tabSpec.setIndicator("Log");
        tabHost.addTab(tabSpec);

        final Button addBtn = (Button) findViewById(R.id.btnAdd);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEntry(dateTxt.getText().toString(), stationTxt.getText().toString(), odometerTxt.getText().toString(),
                        fuelGradeTxt.getText().toString(), fuelAmountTxt.getText().toString(), fuelUnitCostTxt.getText().toString(), fuelTotalCostTxt.getText().toString());
                Toast.makeText(getApplicationContext(),"Your Entry has been Added",Toast.LENGTH_SHORT).show();


                // code to convert string input to integer for addition & display
                // http://stackoverflow.com/questions/2709253/converting-a-string-to-an-integer-on-android
                float myNum = 0;
                try {
                    myNum = Float.parseFloat(fuelTotalCostTxt.getText().toString());
                } catch(NumberFormatException nfe) {
                    System.out.println("Could not parse " + nfe);
                }
                sum = sum + myNum;

                TextView t = (TextView)findViewById(R.id.TVtotalFuel);
                t.setText(""+sum);

                populateList();

                Toast.makeText(getApplicationContext(),dateTxt.getText() + "has been added.",Toast.LENGTH_SHORT).show();
            }
        });

        dateTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                addBtn.setEnabled(!dateTxt.getText().toString().trim().isEmpty());
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    private void populateList(){
        ArrayAdapter<Entry> adapter = new EntryListAdapter();
        entryListView.setAdapter(adapter);
    }

    private void addEntry(String date, String station, String odometer, String fuelGrade, String fuelAmount, String fuelUnitCost, String fuelTotalCost){
        Entries.add(new Entry(date,station,odometer,fuelGrade,fuelAmount,fuelUnitCost,fuelTotalCost));
        //entryAdapter.notifyDataSetChanged();
    }


    //make adapter constructor
    private class EntryListAdapter extends ArrayAdapter<Entry>{
        public EntryListAdapter(){
            super(MainActivity.this, R.layout.logview_item, Entries);
        }

        @Override
        public View getView(int position, View view, ViewGroup parent){
            if (view == null)
                view = getLayoutInflater().inflate(R.layout.logview_item, parent, false);

            Entry currentEntry = Entries.get(position);

            TextView date = (TextView) view.findViewById(R.id.entryDate);
            date.setText(currentEntry.get_date());

            TextView station = (TextView) view.findViewById(R.id.entryStation);
            station.setText(currentEntry.get_station());

            TextView odometer = (TextView) view.findViewById(R.id.entryOdometer);
            odometer.setText(currentEntry.get_odometer());

            TextView fuelGrade = (TextView) view.findViewById(R.id.entryFuelGrade);
            fuelGrade.setText(currentEntry.get_fuelGrade());

            TextView fuelAmount = (TextView) view.findViewById(R.id.entryFuelAmount);
            fuelAmount.setText(currentEntry.get_fuelAmount());

            TextView fuelUnitCost = (TextView) view.findViewById(R.id.entryFuelUnitCost);
            fuelUnitCost.setText(currentEntry.get_fuelUnitCost());

            TextView fuelTotalCost = (TextView) view.findViewById(R.id.entryFuelTotalCost);
            fuelTotalCost.setText(currentEntry.get_fuelTotalCost());

            return view;

        }

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
}
