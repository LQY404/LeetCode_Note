package leetcode;

import java.util.ArrayList;
import java.util.List;

/*
USER：LQY
DATE：2020/7/14
TIME：9:37
*/
public class leetcode_120 {

    public static void main(String []args){
        List<Integer> inner1 = new ArrayList<>();
        List<Integer>inner2 = new ArrayList<>();
        List<Integer>inner3 = new ArrayList<>();
        List<Integer>inner4 = new ArrayList<>();
        List<List<Integer>> outer = new ArrayList<>();
        inner1.add(2);
        inner2.add(3);inner2.add(4);
        inner3.add(6);inner3.add(5);inner3.add(7);
        inner4.add(4);inner4.add(1);inner4.add(8);inner4.add(3);
        outer.add(inner1);
        outer.add(inner2);
        outer.add(inner3);
        outer.add(inner4);
        new leetcode_120().minimumTotal(outer);
    }

    public int minimumTotal(List<List<Integer>> triangle) {
//        int ans = 0;
        int len = triangle.size();
        int []dp = new int[len];
        List<Integer> temp = triangle.remove((len-1));
        int index = 0;
        for(int i: temp){
            dp[index++] = i;
        }
        for(int i = 0;i < len;i++)
            System.out.print(dp[i]+" ");
        System.out.println();
        int size = len - 1;
        while(size > 0){
            temp = triangle.remove(size-1);

            index = 0;
            for(int i : temp){
               dp[index] = Math.min(i+dp[index], i+dp[index+1]);
               index++;
            }

            for(int i = 0;i < size;i++)
                System.out.print(dp[i]+" ");
            System.out.println();
            size--;

        }


        return dp[0];
    }
}
