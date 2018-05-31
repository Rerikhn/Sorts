package com.magtu;

import java.util.Random;

public class Main {

    private static int N;
    private static final Random r = new Random();
    private static long nano;

    public static void main(String args[]) {

        N = 1000000;
        double[] arr = new double[N];

        for (int i = 0; i < N; i++) {
            arr[i] = r.nextInt(1000);
        }

        insertSort(arr);
        selectionSort(arr);
        bubbleSort(arr);
        shakerSort(arr);
        shellSort(arr);
        heapSort(arr);
        quickSort(arr);
    }

    private static void worstCase(double arr[]) {
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    double tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }

        double tempArr[] = new double[arr.length];

        System.arraycopy(arr, 1, tempArr, 1, arr.length - 1);
        System.arraycopy(tempArr, 0, arr, 0, arr.length);
    }

    private static void insertSort(double arr[]) {
        double[] temp = new double[arr.length];
        System.arraycopy(arr, 0, temp, 0, arr.length);

        double x;
        int j;
        nano = System.nanoTime();
        for (int i = 1; i < N; i++) {
            x = temp[i];
            temp[0] = x;
            j = i - 1;
            while (x < temp[j]) {
                temp[j + 1] = temp[j];
                j--;
            }
            temp[j + 1] = x;
        }
        nano = System.nanoTime() - nano;
        System.out.println("Performance of insert sort = " + (nano * Math.pow(10, -9)) + " in seconds.");

        /*for (int aTemp : temp) {
            System.out.println(aTemp);
        }*/
    }

    private static void selectionSort(double arr[]) {
        double[] temp = new double[arr.length];
        System.arraycopy(arr, 0, temp, 0, arr.length);

        double x;
        int k;
        nano = System.nanoTime();
        for (int i = 0; i < temp.length - 1; i++) {
            k = i;
            for (int j = i + 1; j < temp.length; j++) {
                if (temp[j] < temp[k])
                    k = j;
            }
            x = temp[k];
            temp[k] = temp[i];
            temp[i] = x;
        }
        nano = System.nanoTime() - nano;
        System.out.println("Performance of selection sort = " + (nano * Math.pow(10, -9)) + " in seconds.");

        /*for (int aTemp : temp) {
            System.out.println(aTemp);
        }*/
    }

    private static void bubbleSort(double arr[]) {
        double[] temp = new double[arr.length];
        System.arraycopy(arr, 0, temp, 0, arr.length);

        nano = System.nanoTime();
        for (int i = temp.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (temp[j] > temp[j + 1]) {
                    double tmp = temp[j];
                    temp[j] = temp[j + 1];
                    temp[j + 1] = tmp;
                }
            }
        }
        nano = System.nanoTime() - nano;
        System.out.println("Performance of bubble sort = " + (nano * Math.pow(10, -9)) + " in seconds.");

        /*for (int aTemp : temp) {
            System.out.println(aTemp);
        }*/
    }

    private static void shakerSort(double[] arr) {
        double[] temp = new double[arr.length];
        System.arraycopy(arr, 0, temp, 0, arr.length);

        nano = System.nanoTime();
        for (int i = 0; i < temp.length / 2; i++) {
            boolean swapped = false;
            for (int j = i; j < temp.length - i - 1; j++) {
                if (temp[j] < temp[j + 1]) {
                    double tmp = temp[j];
                    temp[j] = temp[j + 1];
                    temp[j + 1] = tmp;
                    swapped = true;
                }
            }
            for (int j = temp.length - 2 - i; j > i; j--) {
                if (temp[j] > temp[j - 1]) {
                    double tmp = temp[j];
                    temp[j] = temp[j - 1];
                    temp[j - 1] = tmp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
        nano = System.nanoTime() - nano;
        System.out.println("Performance of shaker sort = " + (nano * Math.pow(10, -9)) + " in seconds.");

        /*for (int aTemp : temp) {
            System.out.println(aTemp);
        }*/
    }

    private static void shellSort(double[] arr) {
        double[] temp = new double[arr.length];
        System.arraycopy(arr, 0, temp, 0, arr.length);

        nano = System.nanoTime();
        for (int gap = temp.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < temp.length; i++) {
                double x = temp[i];
                int j;
                for (j = i; j >= gap && temp[j - gap] > x; j -= gap)
                    temp[j] = temp[j - gap];
                temp[j] = x;
            }
        }
        nano = System.nanoTime() - nano;
        System.out.println("Performance of shell sort = " + (nano * Math.pow(10, -9)) + " in seconds.");

        /*for (double aTemp : temp) {
            System.out.println(aTemp);
        }*/
    }

    private static void heapSort(double[] arr) {
        double[] temp = new double[arr.length];
        System.arraycopy(arr, 0, temp, 0, arr.length);

        nano = System.nanoTime();
        int left, right;
        double x;
        left = (temp.length / 2) + 1;
        right = temp.length;
        while (left > 1) {
            left--;
            sift(temp, left, right - 1);
        }
        while (right > 1) {
            x = temp[1];
            temp[1] = temp[right - 1];
            temp[right - 1] = x;
            right--;
            sift(temp, left, right);
        }
        nano = System.nanoTime() - nano;
        System.out.println("Performance of heap sort = " + (nano * Math.pow(10, -9)) + " in seconds.");
    }

    private static void sift(double[] temp, int left, int right) {
        double x;
        int i, j;
        i = left;
        j = 2 * i;
        x = temp[i];
        while (j <= right) {
            if (j < right && temp[j] > temp[j + 1])
                j++;
            if (x <= temp[j]) break;
            temp[i] = temp[j];
            i = j;
            j = 2 * i;
        }
        temp[i] = x;
    }

    private static void quickSort(double[] arr) {
        double[] temp = new double[arr.length];
        System.arraycopy(arr, 0, temp, 0, arr.length);
        nano = System.nanoTime();
        sort(arr, 0, arr.length - 1);
        nano = System.nanoTime() - nano;
        System.out.println("Performance of quick sort = " + (nano * Math.pow(10, -9)) + " in seconds.");
    }

    private static void sort(double[] arr, int left, int right) {
        int i, j;
        double x, w;
        i = left;
        j = right;
        x = arr[(left + right) / 2];
        do {
            while (arr[i] < x)
                i++;
            while (x < arr[j])
                j--;
            if (i <= j) {
                w = arr[i];
                arr[i] = arr[j];
                arr[j] = w;
                i++;
                j--;
            }
        } while (i < j);
        if (left < j) sort(arr, left, j);
        if (i < right) sort(arr, i, right);
    }
}
