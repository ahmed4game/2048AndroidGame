package com.ahmed.ather.codechallenge.a2048ather;

import java.util.HashMap;
import java.util.Random;
import static com.ahmed.ather.codechallenge.a2048ather.LogUtils.*;

public class Game {

    private HashMap<String, Integer> map;

    public Game(){
        map = new HashMap<>();
    }

    public String[] getBoardKeySet() {
        return new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16" };
    }

    public void showBoard() {
        String[] arr = getBoardKeySet();

        for (int i = 1; i <= arr.length; i++) {
            System.out.print(map.get(i + "") + " ");
            if (i == 4 || i == 8 || i == 12 || i == 16) {
                System.out.println("");
            }
        }
    }

    String[] generateIndex(boolean generateTwoNumber) {
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

        showLog(r1 + " " + r2);
        return arr;
    }

    String[] reverse(String arr[]) {

        String[] localArr = new String[arr.length];
        int n = localArr.length;
        for (int i = 0; i < localArr.length; i++) {
            localArr[n-1] = arr[i];
            n-=1;
        }

        return localArr;
    }

}
