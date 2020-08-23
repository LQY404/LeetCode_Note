package leetcode;

/*
USER：LQY
DATE：2020/8/23
TIME：9:28
*/
public class leetcode_201_数字范围按位与 {

    public static void main(String []args){
        int m = 0;
        int n = 1;
        new leetcode_201_数字范围按位与().rangeBitwiseAnd(m, n);
    }
    public int rangeBitwiseAnd(int m, int n) {
//        int ans =  2147483647;
//        for(int i = m;i <= n;i++){
//            ans = ans & i;
//        }
//        System.out.println(ans);
//        return ans;
//        每+1，都会使得m的某一位k后的所有位均置反，如：
//        m = 0101(5).
//        m+1 = 0110(6)。
//        第三位以后的两位均置反了。即在这里K=3
//        所以，从m->n这之间的连续(n-m+1)个数的按位与只取决于那一个k的值
//        即，在从m累加到n的过程中，有且只有第k位的值没有改变过，其后k-1位均在发生变化
//        所以，可以将m、n分别向右移，每次移动一位，直到m==n为止（与此同时记录下右移的位数），此时m/n的最后一位就是第k位的值，
//        最后，再将m/n左移先前右移的位数即可得到(n-m+1)个连续数的按位与的结果。
        int off = 0;
        while(m != n){
            off++;
            m >>= 1;
            n >>= 1;
        }
        m <<= off;

        // System.out.println(m);
        return m;
    }
}
