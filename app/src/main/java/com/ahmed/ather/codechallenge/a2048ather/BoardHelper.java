package com.ahmed.ather.codechallenge.a2048ather;

import java.util.HashMap;
import java.util.Random;

public class BoardHelper {
    MergeAndShift mergeAndShift;
    BoardHelper helper;
    public BoardHelper() {
        mergeAndShift = new MergeAndShift();
        helper = new BoardHelper();
    }

    public String[] getBoardKeySet() {
        return new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16" };
    }

    public void populateBoardMap(HashMap<String, Integer> map) {
        String[] arr = getBoardKeySet();
        for (String s : arr) map.put(s, 0);
    }

    public void showBoardMap(HashMap<String, Integer> map) {
        String[] arr = getBoardKeySet();

        for (int i = 1; i <= arr.length; i++) {
            System.out.print(i + " -> " + map.get(i + "") + " ");
            if (i == 4 || i == 8 || i == 12 || i == 16) {
                System.out.println();
            }
        }

    }

    public void showBoard(HashMap<String, Integer> map) {
        String[] arr = getBoardKeySet();

        for (int i = 1; i <= arr.length; i++) {
            System.out.print(map.get(i + "") + " ");
            if (i == 4 || i == 8 || i == 12 || i == 16) {
                System.out.println();
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

        String[] arr = new String[2];
        arr[0] = r1 + "";
        if (generateTwoNumber)
            arr[1] = r2 + "";

        System.out.println(r1 + " " + r2);
        return arr;
    }

    public void moveBoardsVertically(HashMap<String, Integer> map, boolean pushZeroToEnd) {
        String[][] keyArray = getArrayOfColoumns();
        int[][] valueArray = getValueArray2d(keyArray,map);

        for (int[] ints : valueArray) {
            mergeAndShift.mergeifTwoContinousElementsSame(ints);
            if (pushZeroToEnd) {
                mergeAndShift.pushZerosToEnd(ints, ints.length);
            } else {
                mergeAndShift.pushZerosToStart(ints, ints.length);
            }
        }

        for(int j=0;j<keyArray.length;j++) {
            for(int k=0;k<keyArray[j].length;k++) {
                map.put(keyArray[j][k], valueArray[j][k]);
            }
        }
        helper.showBoard(map);
    }

    public void moveBoardsHorizontally(HashMap<String, Integer> map, boolean pushZeroToEnd) {
        String[][] keyArray = getArrayOfRows();
        int[][] valueArray = getValueArray2d(keyArray,map);

        for (int[] ints : valueArray) {
            mergeAndShift.mergeifTwoContinousElementsSame(ints);
            if (pushZeroToEnd) {
                mergeAndShift.pushZerosToEnd(ints, ints.length);
            } else {
                mergeAndShift.pushZerosToStart(ints, ints.length);
            }
        }

        for(int j=0;j<keyArray.length;j++) {
            for(int k=0;k<keyArray[j].length;k++) {
                map.put(keyArray[j][k], valueArray[j][k]);
            }
        }
        helper.showBoard(map);

    }

    int[][] getValueArray2d(String[][] key2d,HashMap<String, Integer> map) {

        int[][] valueArray = new int[4][4];

        for (int i = 0; i < key2d.length; i++)
            valueArray[i] = MapMatrixMapper.getValueFromKey(key2d[i], map);

        return valueArray;
    }

    String[][] getArrayOfColoumns() {
        String[][] key2d = new String[4][4];
        key2d[0] = MapMatrixMapper.Vertical.col1;
        key2d[1] = MapMatrixMapper.Vertical.col2;
        key2d[2] = MapMatrixMapper.Vertical.col3;
        key2d[3] = MapMatrixMapper.Vertical.col4;
        return key2d;
    }

    String[][] getArrayOfRows() {
        String[][] key2d = new String[4][4];
        key2d[0] = MapMatrixMapper.Horizontal.row1;
        key2d[1] = MapMatrixMapper.Horizontal.row2;
        key2d[2] = MapMatrixMapper.Horizontal.row3;
        key2d[3] = MapMatrixMapper.Horizontal.row4;
        return key2d;
    }

}
