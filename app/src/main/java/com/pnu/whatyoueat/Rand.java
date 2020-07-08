package com.pnu.whatyoueat;

import java.util.Random;

public class Rand {

    public int randnumber;
    public static int savenumber;

    public Rand()
    {
        Random rand = new Random();
        randnumber = rand.nextInt(37);
    }
}