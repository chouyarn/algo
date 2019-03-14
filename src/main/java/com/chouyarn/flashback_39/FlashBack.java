package com.chouyarn.flashback_39;

/**
 * Created by chouyarn of BI on 2019/3/14
 */
public class FlashBack {
    //八皇后问题
    private int[] result = new int[8];
    public void cal8queens(int row){
        if (row == 8){
            printQueens(result);
            return;
        }
        for (int column = 0;column < 8;++column){
            if (isOk(row,column)){
                result[row] = column;
                cal8queens(row+1);
            }
        }
    }

    private boolean isOk(int row,int column){
        int leftup = column -1;
        int rightup = column +1;
        for (int i = row - 1;i>=0;--i){
            if (result[i] == column) return false;
            if (leftup >=0){
                if (result[i] == leftup) return false;
            }
            if (rightup < 8){
                if (result[i] == rightup) return false;
            }
            --leftup;++rightup;
        }
        return true;
    }

    private void printQueens(int[] result){
        for (int row =0;row<8;++row){
            for (int column=0;column<8;++column){
                if (result[row] == column) System.out.print("Q ");
                else System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println();
    }

    //0-1b背包问题
    public int maxW = Integer.MIN_VALUE;
    //cw表示当前背包已经装进去的重量和；i表示考察到哪个物品了
    //w表示背包重量；n表示物品个数，items表示每个物品的重量
    public void f(int i,int cw,int[] items,int n,int w){
        if (cw == w || i ==n){
            if (cw > maxW) maxW = cw;
            return;
        }
        f(i+1,cw,items,n,w);
        if (cw + items[i] <= w){
            f(i+1,cw+items[i],items,n,w);
        }
    }

    public static void main(String[] args){
        FlashBack flashBack = new FlashBack();
        flashBack.cal8queens(0);
    }
}
