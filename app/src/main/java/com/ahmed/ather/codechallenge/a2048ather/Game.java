package com.ahmed.ather.codechallenge.a2048ather;

import java.util.HashMap;

public class Game {

    private HashMap<String, Integer> map;

    public Game(){
        map = new HashMap<>();
    }

    public String[] getBoardKeySet() {
        return new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16" };
    }

    void showBoard() {
        String[] arr = getBoardKeySet();

        for (int i = 1; i <= arr.length; i++) {
            System.out.print(map.get(i + "") + " ");
            if (i == 4 || i == 8 || i == 12 || i == 16) {
                System.out.println("");
            }
        }

    }

}
