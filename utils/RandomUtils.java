package com.zhizoo.util;

import java.util.Random;

/**
 * Created by temp on 2016/7/19.
 */
public class RandomUtils {
    protected static final Random rand = new Random();

    public static Object randomInt(int lower,int upper) {
        return rand.nextInt(upper)%(upper-lower+1)+lower;
    }

}
