package com.ahmed.ather.codechallenge.a2048ather;

import java.util.HashMap;

public class MapMatrixMapper {
    public static class Horizontal {
        public static String[] row1 = new String[] {"1","2","3","4"};
        public static String[] row2 = new String[] {"5","6","7","8"};
        public static String[] row3 = new String[] {"9","10","11","12"};
        public static String[] row4 = new String[] {"13","14","15","16"};
    }
    public static class Vertical {
        public static String[] col1 = new String[] {"1","5","9","13"};
        public static String[] col2 = new String[] {"2","6","10","14"};
        public static String[] col3 = new String[] {"3","7","11","15"};
        public static String[] col4 = new String[] {"4","8","12","16"};
    }

    public static int[] getValueFromKey(String[] keyArray, HashMap<String, Integer> map) {
        int[] arr = new int[keyArray.length];

        for(int i=0;i<arr.length;i++) {
            arr[i] = map.get(keyArray[i]);
        }

        return arr;
    }
}
