package com.chouyarn.heap_28;

/**
 * Created by chouyarn of BI on 2019/3/3
 */
public class Heap {
    private int[] a;
    private int n;
    private int count;
    public Heap(int capacity){
        a = new int[capacity+1];
        n = capacity;
        count = 0;
    }
    public void insert(int data){
        if (count >= n) return;
        ++count;
        a[count] = data;
        int i = count;
        while (i/2 > 0 && a[i] > a[i/2]){
            int tmp = a[i];
            a[i/2] = a[i];
            a[i] = tmp;
            i = i/2;
        }
    }

    public void removeMax(){
        if (count == 0) return;
        a[1] = a[count];
        --count;
        heapify(a,count,1);
    }
    private void heapify(int[] a,int n,int i){
        while (true){
            int maxPos = i;
            if (2*i <= n && a[i] < a[2*i]) maxPos = 2*i;
            if (2*i+1 <= n && a[maxPos] < a[2*i+1]) maxPos= 2*i+1;
            if (maxPos == i) break;
            int tmp = a[i];
            a[i] = a[maxPos];
            a[maxPos] = tmp;
            i = maxPos;
        }

    }

    private void buildHeap(int[] a,int n){
        for (int i = n/2;i>=1;--i){
            heapify(a,n,i);
        }
    }

    private void sort(int[] a,int n){
        buildHeap(a,n);
        int k = n;
        while (k > 1){
            int tmp = a[1];
            a[1] = a[k];
            a[k] = tmp;
            --k;
            heapify(a,k,1);
        }
    }
}
