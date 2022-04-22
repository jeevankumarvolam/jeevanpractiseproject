package com.example.demo.util;

import java.util.HashMap;
import java.util.Map;

public class BookingSeasonHelper {
    public static HashMap<String, Integer> seasonsMap = new HashMap<>();
    static {
        seasonsMap.put("SPRING", 1);
        seasonsMap.put("SUMMER", 2);
        seasonsMap.put("FALL", 3);
        seasonsMap.put("HOLIDAY", 4);
    }
}
