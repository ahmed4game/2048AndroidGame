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

    public void mergeifTwoContinousElementsSame(int arr[]) {
        int prevIndex = 0;
        for(int i=0;i<arr.length;i++) {
            if(prevIndex==i)
                continue;
            if(arr[prevIndex] == arr[i]) {
                arr[i] = arr[i] + arr[prevIndex];
                arr[prevIndex] = 0;
                prevIndex = i+1;
            }
        }
    }

}
