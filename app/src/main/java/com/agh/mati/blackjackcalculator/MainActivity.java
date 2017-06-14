package com.agh.mati.blackjackcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner zakresKartSpinner;
    NumberPicker iloscTaliPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        zakresKartSpinner = (Spinner) findViewById(R.id.zakres_spinner);
        iloscTaliPicker = (NumberPicker) findViewById(R.id.talie_picker);

        ArrayList<String> strings = new ArrayList<>();
        strings.add("Od 9 do As");
        strings.add("Od 2 do As");
        strings.add("Inne");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getBaseContext(),
                android.R.layout.simple_list_item_1, strings);

        zakresKartSpinner.setAdapter(adapter);
        iloscTaliPicker.setMaxValue(10);
        iloscTaliPicker.setMinValue(1);
    }

    public void onStart(View view) {
        Intent intentForStart = new Intent(getBaseContext(), CardsActivity.class);
        ArrayList<Integer> taliaKart = new ArrayList<>();

        //11 - walet, 12 - dama, 13 - krol, 14 - as
        switch (zakresKartSpinner.getSelectedItemPosition()){
            case 0:
                for (int i = 9; i <= 14 ; i++)
                    taliaKart.add(i);
                break;
            case 1:
                for (int i = 2; i <= 14 ; i++)
                    taliaKart.add(i);
                break;
            case 2:
                //TODO: custom list of cards
                break;
        }

        intentForStart.putExtra("talia", taliaKart);
        intentForStart.putExtra("iloscTalii", iloscTaliPicker.getValue());

        startActivity(intentForStart);
    }
}
