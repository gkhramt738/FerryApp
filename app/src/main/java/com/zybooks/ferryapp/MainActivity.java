package com.zybooks.ferryapp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private final String KEY_TOTAL_TICKETS = "totalTickets";
    private int mTotalTickets;

    private EditText mNumTickets;
    private TextView mNumTicketTotalCost;

    // Condition complete: create array of strings to store names of Martha Vineyeard's towns
    String[] mv_towns = { "Aquinnah", "Chilmark", "Edgartown", "Oak Bluffs", "Vineyard Haven", "West Tisbury" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get instance of Spinner and applying OnItemSelectedListener on it
        Spinner spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);

        // Create ArrayAdapter to show town selection with Spinner
        ArrayAdapter arrAdapt = new ArrayAdapter(this,android.R.layout.simple_spinner_item,mv_towns);
        arrAdapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Populate  ArrayAdapter with Spinner data
        spin.setAdapter(arrAdapt);

        // Assign values to the number of tickets and the total ticket cost
        mNumTickets = findViewById(R.id.tickets_text_field);
        mNumTicketTotalCost = findViewById(R.id.ticket_total_cost);

        // Restore state, if necessary
        if (savedInstanceState != null) {
            mTotalTickets = savedInstanceState.getInt(KEY_TOTAL_TICKETS);
            displayTotalTickets();
        }
    }

    // Save state of amount of tickets entered, town, and cost
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_TOTAL_TICKETS, mTotalTickets);
    }

    // Calculate
    public void calculateClick(View view) {

        // Convert ticket integer string to int
        int numTickets;
        try {
            String numTicketsStr = mNumTickets.getText().toString();
            numTickets = Integer.parseInt(numTicketsStr);
        } catch (NumberFormatException ex) {
            numTickets = 0;
        }

        // Create spinner, populate with Martha's Vineyard town location
        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        String chooseTown = spinner.getSelectedItem().toString();

        // Set default spinner to WESTTISBURY, randomly selected
        TicketCostCalculator.SpinnerSelect spinnerSelect = TicketCostCalculator.SpinnerSelect.WESTTISBURY;

        // Select which string (town) to use based on user selection
        if (chooseTown == "Aquinnah") {
            spinnerSelect = TicketCostCalculator.SpinnerSelect.AQUINNAH;
        } else if (chooseTown == "Chilmark") {
            spinnerSelect = TicketCostCalculator.SpinnerSelect.CHILMARK;
        } else if (chooseTown == "Edgartown") {
            spinnerSelect = TicketCostCalculator.SpinnerSelect.EDGARTOWN;
        } else if (chooseTown == "Oak Bluffs") {
            spinnerSelect = TicketCostCalculator.SpinnerSelect.OAKBLUFFS;
        } else if (chooseTown == "Vineyard Haven") {
            spinnerSelect = TicketCostCalculator.SpinnerSelect.VINEYARDHAVEN;
        }

        // Get the number of tickets needed
        TicketCostCalculator calc = new TicketCostCalculator(numTickets, spinnerSelect);
        mTotalTickets = calc.getTotalCost();
        displayTotalTickets();
    }

    // Display the total amount of tickets
    private void displayTotalTickets() {
        // Place mNumTicketTotalCost into the string resource and display
        String totalText = getString(R.string.total_tickets, mTotalTickets);
        mNumTicketTotalCost.setText(totalText);
    }

    // Display a brief Toast confirming user location selection
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int location, long l) {
        String destination = "Destination Selected: ";
        Toast.makeText(getApplicationContext(), destination + mv_towns[location], Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}