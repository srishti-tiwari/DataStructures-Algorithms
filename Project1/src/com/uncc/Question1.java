package com.uncc;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Question1 {

    public static void main(String[] args) {

            int[] input = getInputArr();
            minDiff(input);

    }
    private static int[] getInputArr () {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter array separated with ',' ");
        String s1 = scanner.next();
        String[] a = s1.split(",");
        int[] arr = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            arr[i] = Integer.parseInt(a[i]);
        }
        return arr;
    }
    static void minDiff(int[] arr){
        if(arr.length<2){
            System.out.println(0);
            return;
        }
        dualPivotQuicksort(arr, 0, arr.length -1);

        System.out.print(Arrays.toString(arr));

        int[] diffArr = new int[arr.length -1];
        int n =0;
        for (int i = 0; i < arr.length - 1; i++) {

            diffArr[n++] = arr[i+1] - arr[i];
        }

        dualPivotQuicksort(diffArr, 0 , diffArr.length -1);

        System.out.println(" " + diffArr[0] + " " + diffArr[1]);
        String result = Arrays.toString(arr)+" " + diffArr[0] + " " + diffArr[1] ;
        try {
            writeToFile(result);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    //Dual pivot quick sort gives better performance than traditional quicksort and is faster too.
    public static void dualPivotQuicksort(int[] arr, int lo, int hi) {
            if (hi <= lo) return;

            // make sure a[lo] <= a[hi]
            if (arr[hi] < arr[lo]) swap(arr, lo, hi);

            int lt = lo + 1, gt = hi - 1;
            int i = lo + 1;
            while (i <= gt) {

                if       (arr[i]< arr[lo]) swap(arr, lt++, i++);
                else if  (arr[hi]< arr[i]) swap(arr, i, gt--);
                else                         i++;
            }
            swap(arr, lo, --lt);
            swap(arr, hi, ++gt);

            // recursively sort three subarrays
        dualPivotQuicksort(arr, lo, lt-1);
            if (arr[lt] < arr[gt]) dualPivotQuicksort(arr, lt+1, gt-1);
        dualPivotQuicksort(arr, gt+1, hi);

    }

    // swap a[i] and a[j]
    private static void swap(int[] a, int i, int j) {
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
    private static void writeToFile(String result) throws IOException {
        File outputFile = new File("Output-question1.txt");
        FileWriter fw = null;
        String output = " The first minimum and second minimum difference between any successive\n" +
                "elements in its sorted form = " + result;
        try {
            fw = new FileWriter(outputFile);
            fw.write(output);

        } catch (Exception e) {
            System.out.println("Exception occurred");
        } finally {
            fw.close();
        }

    }

}
