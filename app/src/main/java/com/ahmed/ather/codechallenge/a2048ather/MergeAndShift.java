package com.ahmed.ather.codechallenge.a2048ather;


public class MergeAndShift {

    public void pushZerosToEnd(int arr[], int n) {
        int count = 0;

        // 1, 0, 7, 0, 9
        for (int i = 0; i < n; i++)
            if (arr[i] != 0)
                arr[count++] = arr[i];

        while (count < n)
            arr[count++] = 0;
    }

    public void pushZerosToStart(int arr[], int n) {
        int count = n - 1;

        // 1, 0, 7, 0, 9
        for (int i = n - 1; i >= 0; i--)
            if (arr[i] != 0)
                arr[count--] = arr[i];

        while (count >= 0)
            arr[count--] = 0;
    }

    public void mergeifTwoContinousNonZeroElementSame(int arr[]) {
        int n = arr.length;
        outerloop: for (int i = 0; i < n - 1; i++) {
            if (arr[i] == 0) {
                continue;
            }
            for (int j = i + 1; j < n; j++) {
                if (arr[j] == 0) {
                    continue;
                }
                if (arr[i] != arr[j]) {
                    continue outerloop;
                } else if (arr[i] == arr[j]) {
                    arr[i] = arr[i] + arr[j];
                    arr[j] = 0;
                    i = i + 1;
                    continue;
                }
            }
        }
    }

/*    public int[] reverse(int arr[]) {
        int[] b = new int[arr.length];
        int j = arr.length;
        for (int i = 0; i < arr.length; i++) {
            b[j - 1] = arr[i];
            j = j - 1;
        }

        return b;
    }

    void showArray(int arr[]) {
        for (int i : arr)
            System.out.print(i + " ");
    }*/

}
