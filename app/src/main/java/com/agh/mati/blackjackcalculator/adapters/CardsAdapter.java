package com.agh.mati.blackjackcalculator.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.agh.mati.blackjackcalculator.R;
import com.agh.mati.blackjackcalculator.objects.MyCard;

import java.util.ArrayList;

/**
 * Created by Damian on 2017-06-02.
 */

public class CardsAdapter extends ArrayAdapter<MyCard> {

    ArrayList<MyCard> cards;

    public CardsAdapter(@NonNull Context context, @LayoutRes int resource,
                        @NonNull ArrayList<MyCard> objects) {
        super(context, resource, objects);

        cards = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        MyCard myCard = cards.get(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.card_row,
                    parent, false);

        TextView cardsLeft = (TextView) convertView.findViewById(R.id.cards_left);
        TextView prabobilityCard = (TextView) convertView.findViewById(R.id.card_prawdop);
        TextView cardNumber = (TextView) convertView.findViewById(R.id.card_id);

        RelativeLayout cardColor = (RelativeLayout) convertView.findViewById(R.id.card);

        cardsLeft.setText("Pozostało kart: " + myCard.getCardsOfThisNumber());
        prabobilityCard.setText("Prawdopodobienstwo: " + myCard.getPrabobility() + "%");

        if (myCard.getNumber() > 10) {
            char card = 0;
            switch (myCard.getNumber()) {
                case 11:
                    card = 'J';
                    break;
                case 12:
                    card = 'Q';
                    break;
                case 13:
                    card = 'K';
                    break;
                case 14:
                    card = 'A';
                    break;
            }
            cardNumber.setText(card + "");
        }else
            cardNumber.setText(myCard.getNumber() + "");



        return convertView;
    }
}
