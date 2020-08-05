package leetcode;

import java.util.Arrays;
import java.util.LinkedList;

/*
USER：LQY
DATE：2020/7/29
TIME：11:08
*/
public class leetcode_62 {

    public static void main(String []args){
        new leetcode_62().uniquePaths(3, 2);
    }
    public int uniquePaths(int m, int n) {
//        int [][]dir = {{0,1}, {1,0}}; //右、下
        int []ans = new int[n];
        //初始化起点
//        ans[0] = 1;
//        初始化两个边界
        for(int i = 0;i < n;i++){
            ans[i] = 1;
        }
//        for(int i = 1;i < m;i++)
//            ans[i][0] = 1;

        for(int i = 1;i < m;i++){
//            for(int j = 1;j < n;j++){
//                ans[i][j] = ans[i-1][j] + ans[i][j-1];
//            }
            //位置0处不需要动
            for(int j = 1;j < n;j++){
                ans[j] += ans[j-1];
            }
            for(int j = 0;j < n;j++)
                System.out.print(ans[j]+" ");
            System.out.println();
        }

//        for(int i = 0;i < m;i++){
//            for(int j = 0;j < n;j++)
//                System.out.print(ans[i][j]+" ");
//            System.out.println();
//        }
        return ans[n-1];

//        ---------------------------------------------------------------------------
//        int [][]ans = new int[m][n];
//        //初始化起点
//        ans[0][0] = 1;
////        初始化两个边界
//        for(int i = 1;i < n;i++){
//            ans[0][i] = 1;
//        }
//        for(int i = 1;i < m;i++)
//            ans[i][0] = 1;
//
//        for(int i = 1;i < m;i++){
//            for(int j = 1;j < n;j++){
//                ans[i][j] = ans[i-1][j] + ans[i][j-1];
//            }
//        }
//        return ans[m-1][n-1];
    }


}
