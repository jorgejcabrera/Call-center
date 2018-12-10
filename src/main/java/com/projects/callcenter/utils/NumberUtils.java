package com.projects.callcenter.utils;

import java.util.Random;

public class NumberUtils {

    public static int getRandomBetween(int low, int high) {
        Random r = new Random();
        return r.nextInt(high-low) + low;
    }
}
