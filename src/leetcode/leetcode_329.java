package leetcode;

import java.util.Arrays;

/*
USER：LQY
DATE：2020/7/26
TIME：8:25
*/
public class leetcode_329 {

    public static void main(String []args){
        int [][]matrix = {
                {9, 9, 4},
                {6, 6, 8},
                {2, 1, 1}
//                {3, 4, 5},
//                {3, 2, 6},
//                {2, 2, 1}
        };
        System.out.println(new leetcode_329().longestIncreasingPath(matrix));
    }
    public int longestIncreasingPath(int[][] matrix) {

        int row = matrix.length;
        if(row == 0) return 0;
        int column = matrix[0].length;
        if(row==column && row==1){
            return 1;
        }
//        int [][]book = new int[row][column];
        int [][]dp = new int[row][column];
//        for(int i = 0;i < row;i++)
//            for(int j = 0;j < column;j++)
//                dp[i][j] = 1;
//        solve(matrix, 1, 0, 0, book, 1, dp);
        int max = 0;
        for(int i = 0;i < row;i++){
            for(int j = 0;j < column;j++){
                solve(matrix, i, j, dp);


                max = Math.max(max, dp[i][j]);

            }
        }


        return max;

    }
    int [][]dir = {{-1, 0},{0, 1},{1, 0},{0, -1}};  //上、右、下、左

    public int solve(int [][]matrix, int nr, int nc, int [][]dp){
        if(dp[nr][nc] != 0){
            return dp[nr][nc];
        }
        int r = matrix.length;
        int c = matrix[0].length;

//        dp[nr][nc] = count;  //记录
        for(int i = 0;i < 4;i++){
            int nextr = nr + dir[i][0];
            int nextc = nc + dir[i][1];
            if(nextc<0 || nextc>=c || nextr<0 || nextr>=r) continue;
//            if(book[nextr][nextc] == 1) continue;  //走过了
            int nextv = matrix[nextr][nextc];
            int nv = matrix[nr][nc];
            if(nextv <= nv) continue;  //递增情况下。不符合

//            book[nextr][nextc] = 1;
//        关键语句：使得dp[nr][nc]更新为：从[nr,nc]出发能够到达的、递增排列的最长序列长度。
//       max中，第一个不需要解释，懂得都懂；
//      第二个需要解释一下，即为从[nr，nc]出发到达其他可以达到节点的路径长度。
//      也是利用记忆化，来达到叠加最优、剪枝的效果。
            dp[nr][nc] = Math.max(dp[nr][nc], solve(matrix, nextr, nextc, dp)+1);
//            dp[nr][nc] = Math.max(dp[nr][nc], dp[nextr][nextc]);

//            book[nextr][nextc] = 0;
        }
//        if(dp[nr][nc] == 0) dp[nr][nc] = 1;   //这个判断很重要：如果当前的节点四周的值都比它小，则当前节点的dp只能也必须赋值为1.
        return dp[nr][nc]==0?1:dp[nr][nc];
    }
}
