package com.ahmed.ather.codechallenge.a2048ather;

import java.util.HashMap;
import java.util.Random;

public class BoardHelper {
    public BoardHelper() {
    }

    public String[] getBoardKeySet() {
        return new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16" };
    }

    public void populateBoardMap(HashMap<String, Integer> map) {
        String[] arr = getBoardKeySet();
        for (int i = 0; i < arr.length; i++)
            map.put(arr[i], 0);
    }

    public void showBoardMap(HashMap<String, Integer> map) {
        String[] arr = getBoardKeySet();

        for (int i = 1; i <= arr.length; i++) {
            System.out.print(i + " -> " + map.get(i + "") + " ");
            if (i == 4 || i == 8 || i == 12 || i == 16) {
                System.out.println("");
            }
        }

    }

    public void showBoard(HashMap<String, Integer> map) {
        String[] arr = getBoardKeySet();

        for (int i = 1; i <= arr.length; i++) {
            System.out.print(map.get(i + "") + " ");
            if (i == 4 || i == 8 || i == 12 || i == 16) {
                System.out.println("");
            }
        }

    }

    public String[] generateIndex(boolean generateTwoNumber) {
        Random random1 = new Random(), random2 = new Random();
        int r1 = random1.nextInt(16) + 1, r2 = random2.nextInt(16) + 1;

        // To make sure r1 & r2 should not be same
        if (r1 == r2 && r2 != 16) {
            r2++;
        } else if (r1 == r2) {
            r2--;
        }

        String arr[] = new String[2];
        arr[0] = r1 + "";
        if (generateTwoNumber)
            arr[1] = r2 + "";

        System.out.println(r1 + " " + r2);
        return arr;
    }

}
