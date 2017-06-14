package com.agh.mati.blackjackcalculator;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;

import com.agh.mati.blackjackcalculator.adapters.CardsAdapter;
import com.agh.mati.blackjackcalculator.objects.MyCard;

import java.util.ArrayList;

public class CardsActivity extends AppCompatActivity {

    ArrayList<MyCard> cards;
    ListView listCards;
    CardsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);

        listCards = (ListView) findViewById(R.id.card_list_view);

        ArrayList<Integer> taliaKart = getIntent().getIntegerArrayListExtra("talia");
        int iloscKart = getIntent().getIntExtra("iloscTalii", 0);

        if(taliaKart == null || taliaKart.isEmpty() || iloscKart == 0){
            AlertDialog alertDialog = new AlertDialog.Builder(getBaseContext()).create();
            alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface
                    .OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    finish();
                }
            });
            alertDialog.setTitle("Błąd");
            alertDialog.setMessage("Wprowadzono niepoprawne dane, spróbuj jeszcze raz");
            alertDialog.show();
        }

        cards = new ArrayList<>();

        for (Integer cardNumber : taliaKart) {
            cards.add(new MyCard(cardNumber, iloscKart));
        }

        adapter = new CardsAdapter(getBaseContext(), R.layout.card_row, cards);
        listCards.setAdapter(adapter);

        new Obliczenia().execute();

        listCards.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cards.get(position).takeCard(1);
                new Obliczenia().execute();
            }
        });
    }


    private class Obliczenia extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            int allCards = 0;
            for (MyCard myCard : cards) {
                allCards += myCard.getCardsOfThisNumber();
            }

            for (MyCard myCard : cards) {
                float prabobility = (float) myCard.getCardsOfThisNumber()/allCards * 100;
                myCard.setPrabobility(prabobility);
            }

            return null;

        }

        @Override
        protected void onPostExecute(Void result) {
            adapter.notifyDataSetChanged();
        }
    }
}
