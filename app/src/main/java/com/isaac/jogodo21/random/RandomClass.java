package com.isaac.jogodo21.random;

import java.util.Random;

public class RandomClass {

    public int random(int valorMin,int valorMax){
        Random rnd = new Random();
        return  rnd.nextInt(valorMax - valorMin + 1) + valorMin;
    }
}
