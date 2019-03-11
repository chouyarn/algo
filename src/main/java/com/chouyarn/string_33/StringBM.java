package com.chouyarn.string_33;

/**
 * Created by chouyarn of BI on 2019/3/11
 */
public class StringBM {
    private static final  int SIZE = 256;
    private void generateBC(char[] b,int m,int[] bc){
        for (int i = 0;i<SIZE;++i){
            bc[i] = -1;
        }
        for (int i = 0;i< m;++i){
            int ascii = (int)b[i];//计算b[i]的ASCII值
            bc[ascii] = i;
        }
    }

    public int bm(char[] a,int n,char[] b,int m){
        int[] bc = new int[SIZE];//记录模式串中每个字符最后出现的位置
        generateBC(b,m,bc);//构建坏字符哈希表
        int i = 0;
        while (i <= n-m){
            int j;
            for (j = m -1;j>=0;--j){
                if (a[i+j] != b[j]) break;
            }
            if (j < 0){
                return i;
            }
            i = i+(j-bc[(int)a[i+j]]);
        }
        return -1;
    }

    //b是模式串，m表示长度
    private void generateGS(char[] b,int m,int[] suffix,boolean[] prefix){
        for (int i = 0;i<m;++i){
            suffix[i] = -1;
            prefix[i] = false;
        }
        for (int i = 0;i<m-1;++i){
            int j = i;
            int k = 0;//公共后缀子串长度
            while (j >= 0 && b[j] == b[m-1-k]){//与b[0,m-1]求公共后缀子串
                --j;
                ++k;
                suffix[k] = j+1;//j+1 表示公共后缀子串在 b[0, i] 中的起始下标
            }
            if(j == -1) prefix[k] = true;
        }
    }
    //a,b表示主串和模式串；n,m表示主串和模式串的长度
    public int bmFinal(char[] a,int n,char[] b,int m){
        int[] bc=new int[SIZE];
        generateBC(b,m,bc);
        int[] suffix = new int[m];
        boolean[] prefix = new boolean[m];
        generateGS(b,m,suffix,prefix);
        int i = 0;
        while (i <= n - m){
            int j;
            for (j = m-1;j>=0;--j){
                if (a[i+j] != b[j]) break;
            }
            if (j < 0){
                return i;
            }
            int x = j - bc[(int)a[i+j]];
            int y = 0;
            if (j < m-1){
                y = moveByGS(j,m,suffix,prefix);
            }
            i = i + Math.max(x,y);
        }
        return -1;
    }
    private int moveByGS(int j,int m,int[] suffix,boolean[] prefix){
        int k = m -1-j;
        if (suffix[k] != -1) return j - suffix[k] +1;
        for (int r = j+2;r<= m-1;++r){
            if (prefix[m-r] == true){
                return r;
            }
        }
        return m;
    }
}
