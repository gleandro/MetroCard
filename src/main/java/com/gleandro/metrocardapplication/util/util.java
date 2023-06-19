package com.gleandro.metrocardapplication.util;

public class util {

    public static String formatCodeNumber(long number) {
        return String.format("%03d", ++number);
    }

}
