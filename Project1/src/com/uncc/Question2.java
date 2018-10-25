package com.uncc;

import java.io.*;
import java.util.*;

public class Question2
{
    public static void main(String [] args){
        try {
        int[] input = getInputArr();
        int x = getTheX();
        int result =    longestSubArrayForMedian( input, x);
        System.out.println(result);
        writeToFile(result);

        } catch (Exception e){

        }
        finally {

        }
    }
    private static int getTheX() {
        Scanner kbd = new Scanner(System.in);
        System.out.println("Enter the value of x");
        return kbd.nextInt();
    }

    private static int[] getInputArr () {
        Scanner kbd = new Scanner(System.in);
        System.out.println("Enter array separated with ',' ");
        String s1 = kbd.next();
        String[] a = s1.split(",");
        int[] arr = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            arr[i] = Integer.parseInt(a[i]);
        }
        return arr;
    }

    private static int longestSubArrayForMedian(int[] arr, int X){
        //sort to find the median x
        dualPivotQuicksort(arr, 0, arr.length -1);

        //find the number or number greater than X
        int index = findtheX(arr,X);
        int lo,hi = 0;
        lo = index - 0;
        hi = (arr.length - 1) - index;

        if (arr[index] == X) {
            if (lo <= hi) {
                return arr.length;
            } else {
                return ((hi * 2) + 1);
            }
        } else {
            if (index > 0) {
                if (((arr[index - 1] + arr[index]) / 2) < X) {
                    if (lo <= hi) {
                        return arr.length;
                    } else {
                        return ((hi * 2) + 1);
                    }
                } else {
                    if (lo <= hi) {
                        return arr.length;
                    } else {
                        return ((hi * 2) + 2);
                    }
                }

            } else {
                return arr.length;
            }
        }
    }

    private static int findtheX(int[] arr, int theX) {
        for (int i = 0; i < arr.length ; i++) {
            if(arr[i]>=theX){
                return i;
            }
        }
        return -1;
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

    private static void writeToFile(int result) throws IOException {
        File outputFile = new File("Output-question2.txt");
        FileWriter fw = null;
        String output = "Length of longest sub-array = " + Integer.toString(result);
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
