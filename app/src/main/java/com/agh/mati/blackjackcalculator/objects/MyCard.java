package com.agh.mati.blackjackcalculator.objects;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Created by Damian on 2017-06-02.
 */

public class MyCard {

    private int mNumber;
    private char mChar;
    private int mCardsOfThisNumber;
    private float mPrabobility;

    public MyCard(int number, int cardsOfThisNumber){
        mNumber = number;
        mCardsOfThisNumber = 4*cardsOfThisNumber;
    }


    public int getNumber() {
        return mNumber;
    }

    public int getCardsOfThisNumber() {
        return mCardsOfThisNumber;
    }

    public String getPrabobility() {
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        return df.format(mPrabobility);
    }

    public void setCardsOfThisNumber(int mCardsOfThisNumber) {
        this.mCardsOfThisNumber = mCardsOfThisNumber;
    }

    public void takeCard(int cardTaken) {
        if(mCardsOfThisNumber > 0)
            this.mCardsOfThisNumber -= cardTaken;
    }

    public void setPrabobility(float mPrabobility) {

        this.mPrabobility = mPrabobility;
    }
}
