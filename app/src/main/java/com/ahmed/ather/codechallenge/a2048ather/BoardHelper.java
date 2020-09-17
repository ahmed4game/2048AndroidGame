package com.ahmed.ather.codechallenge.a2048ather;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import static com.ahmed.ather.codechallenge.a2048ather.LogUtils.*;

public class BoardHelper {

    public interface ScoreFeed{
        void updateScore(int score);
    }

    MergeAndShift mergeAndShift;

    public BoardHelper(ScoreFeed feed) {
        mergeAndShift = new MergeAndShift(feed);
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
        StringBuilder  builder = new StringBuilder();
        for (int i = 1; i <= arr.length; i++) {
            builder.append(map.get(i + "") + " ");
            if (i == 4 || i == 8 || i == 12 || i == 16) {
                builder.append("");
            }
        }
        showInfo(builder.toString());
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

    public int moveBoardsVertically(HashMap<String, Integer> map, boolean pushZeroToEnd) {
        String[][] keyArray = getArrayOfColoumns();
        int points = 0;
        int[][] valueArray = getValueArray2d(keyArray,map);

        for(int i=0;i<valueArray.length;i++) {
//			valueArray[i] = mergeAndShift.reverse(valueArray[i]);
            points = mergeAndShift.mergeifTwoContinousNonZeroElementSame(valueArray[i]);
            showInfo("Points: "+points);
            if (pushZeroToEnd) {
                mergeAndShift.pushZerosToEnd(valueArray[i], valueArray[i].length);
            } else {
                mergeAndShift.pushZerosToStart(valueArray[i], valueArray[i].length);
            }

        }

        for(int j=0;j<keyArray.length;j++) {
            for(int k=0;k<keyArray[j].length;k++) {
                map.put(keyArray[j][k], valueArray[j][k]);
            }
        }

        String key = getRandomKey(getArrayOfRows(), map);
        map.put(key, getRandomValue(true));

        showBoard(map);
        return points;
    }

    public int moveBoardsHorizontally(HashMap<String, Integer> map, boolean pushZeroToEnd) {
        String[][] keyArray = getArrayOfRows();
        int[][] valueArray = getValueArray2d(keyArray,map);
        int points = 0;

        for(int i=0;i<valueArray.length;i++) {
//			valueArray[i] = mergeAndShift.reverse(valueArray[i]);
            points = mergeAndShift.mergeifTwoContinousNonZeroElementSame(valueArray[i]);
            if (pushZeroToEnd) {
                mergeAndShift.pushZerosToEnd(valueArray[i], valueArray[i].length);
            } else {
                mergeAndShift.pushZerosToStart(valueArray[i], valueArray[i].length);
            }

        }

        for(int j=0;j<keyArray.length;j++) {
            for(int k=0;k<keyArray[j].length;k++) {
                map.put(keyArray[j][k], valueArray[j][k]);
            }
        }

        String key = getRandomKey(getArrayOfRows(), map);
        map.put(key, getRandomValue(true));

        showBoard(map);
        return points;
    }

    int[][] getValueArray2d(String[][] key2d,HashMap<String, Integer> map) {

        String keyArray[][] = key2d;
        int valueArray[][] = new int[4][4];

        for (int i = 0; i < keyArray.length; i++)
            valueArray[i] = MapMatrixMapper.getValueFromKey(keyArray[i], map);

        return valueArray;
    }

    String[][] getArrayOfColoumns() {
        String key2d[][] = new String[4][4];
        key2d[0] = MapMatrixMapper.Vertical.col1;
        key2d[1] = MapMatrixMapper.Vertical.col2;
        key2d[2] = MapMatrixMapper.Vertical.col3;
        key2d[3] = MapMatrixMapper.Vertical.col4;
        return key2d;
    }

    String[][] getArrayOfRows() {
        String key2d[][] = new String[4][4];
        key2d[0] = MapMatrixMapper.Horizontal.row1;
        key2d[1] = MapMatrixMapper.Horizontal.row2;
        key2d[2] = MapMatrixMapper.Horizontal.row3;
        key2d[3] = MapMatrixMapper.Horizontal.row4;
        return key2d;
    }

    String getRandomKey(String[][] rows, HashMap<String, Integer> map) {

        ArrayList<String> emptyTiles = new ArrayList<String>();

        for(int i=0;i<rows.length;i++) {
            for(String key:rows[i]) {
                if(map.get(key) == 0)
                    emptyTiles.add(key);
            }
        }

        Random r = new Random();
        int size = emptyTiles.size();
        System.out.println("Empty tiles size: "+size);

        return emptyTiles.get(r.nextInt(size));
    }

    int getRandomValue(boolean only2) {
        int[] arr = new int[] {2,4};
        Random r = new Random();
        if (only2) {
            return 2;
        }else {
            return arr[r.nextInt(arr.length)];
        }
    }

    public ArrayList<String> getMapKeyList(){
        return new ArrayList<String>(Arrays.asList("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16"));
    }
}
