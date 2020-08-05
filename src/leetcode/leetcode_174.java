package leetcode;

/*
USER：LQY
DATE：2020/7/12
TIME：9:31
*/
public class leetcode_174 {
    public static void main(String []args){
        int [][]dungeon = {{-2,-3,3},{-5,-10,1},{10,30,-5}};
        System.out.println(new leetcode_174().calculateMinimumHP1(dungeon));
    }
//    逆向DP。AC
    public int calculateMinimumHP1(int [][]dungeon){
        int row = dungeon.length;
        int column = dungeon[0].length;
        if (row == column && row == 1) {
            if (dungeon[0][0] > 0) return 1;
            else return -dungeon[0][0] + 1;
        }

        //初始化
        dungeon[row-1][column-1] = dungeon[row-1][column-1]>0? 0:dungeon[row-1][column-1];
        //右边界
        for(int i = row-2;i >= 0;i--){
            int c = dungeon[i][column-1] + dungeon[i+1][column-1];
            dungeon[i][column-1] = c>0? 0 : c;
        }
        //下边界
        for(int i = column-2;i >= 0;i--){
            int c = dungeon[row-1][i] + dungeon[row-1][i+1];
            dungeon[row-1][i] = c>0? 0 : c;
        }
        for(int i = row-2;i >= 0;i--){
            for(int j = column-2;j >= 0;j--){
                int c = Math.max(dungeon[i+1][j], dungeon[i][j+1]) + dungeon[i][j];
                dungeon[i][j] = c>0? 0 : c;
            }
        }
        return -dungeon[0][0] + 1;
    }
//  正常DP。无法实现。。。
    public int calculateMinimumHP(int[][] dungeon) {
        int row = dungeon.length;
        int column = dungeon[0].length;
        if (row == column && row == 1) {
            if (dungeon[0][0] > 0) return 1;
            else return -dungeon[0][0] + 1;
        }

        int n = Math.max(row, column);
        int [][]ob = new int[n][n];
        int [][]dp = new int[n][n];
        int [][]sum = new int[n][n];
//      初始化ob
        for(int i = 0;i < row;i++){
            for(int j = 0;j < column;j++){
                ob[i][j] = dungeon[i][j];
            }
        }
        dp[0][0] = ob[0][0];
        sum[0][0] = ob[0][0];
        //初始化sum的上边界与左边界
        for(int j = 1;j < column;j++){
            sum[0][j] = sum[0][j-1] + ob[0][j];
            dp[0][j] = Math.min(sum[0][j], dp[0][j-1]);
        }
        for(int i = 1;i < row;i++){
            sum[i][0] = sum[i-1][0] + ob[i][0];
            dp[i][0] = Math.min(dp[i-1][0], sum[i][0]);
        }
        int nrow = 1;
        int ncolumn = 1;
        int [][]dir = {{0,1},{1,0}};
        while(nrow<row && ncolumn<column){
            //先更新对角线处
            int flag;
            //此时不需要判断是否越界
            if(sum[nrow-1][ncolumn] > sum[nrow][ncolumn-1]){
                //从上面来
                flag = -1;
                sum[nrow][ncolumn] = sum[nrow-1][ncolumn] + ob[nrow][ncolumn];

            }else if(sum[nrow-1][ncolumn] < sum[nrow][ncolumn-1]){
                //从左边来
                flag = 1;
                sum[nrow][ncolumn] = sum[nrow][ncolumn-1] + ob[nrow][ncolumn];
            }else{
                //此时需要借助dp
                if(dp[nrow-1][ncolumn] > dp[nrow][ncolumn-1]){
                    //从上面来
                    flag = -1;
                    sum[nrow][ncolumn] = sum[nrow-1][ncolumn] + ob[nrow][ncolumn];
                }else{
                    //从左边来
                    flag = 1;
                    sum[nrow][ncolumn] = sum[nrow][ncolumn-1] + ob[nrow][ncolumn];
                }
            }
            //再更新此处的dp
            if(flag == -1){
                //从上面来
                dp[nrow][ncolumn] = Math.min(dp[nrow-1][ncolumn], sum[nrow][ncolumn]);

            }else if(flag == 1){
//                从左边来

                dp[nrow][ncolumn] = Math.min(dp[nrow][ncolumn-1], sum[nrow][ncolumn]);
            }
            //最后更新当前所在的行和列
            updateRowAndColumn(ob, sum, dp, nrow, ncolumn, row, column);
            nrow++;
            ncolumn++;
        }
        print(sum, row, column);
        print(dp, row, column);

        return dp[row-1][column-1];
    }
    public void updateRowAndColumn(int [][]ob, int [][]sum, int [][]dp, int sr, int sc, int row, int column){

        for(int i = sr+1; i < row;i++){
            //先更新对角线处
            int flag = 0;
            //此时不需要判断是否越界
            if(sum[i-1][sc] > sum[i][sc-1]){
                //从上面来
                flag = -1;
                sum[i][sc] = sum[i-1][sc] + ob[i][sc];
            }else if(sum[i-1][sc] < sum[i][sc-1]){
                //从左边来
                flag = 1;
                sum[i][sc] = sum[i][sc-1] + ob[i][sc];
            }else if(sum[i-1][sc] == sum[i][sc-1]){
                //此时需要借助dp
                if(dp[i-1][sc] > dp[i][sc-1]){
                    //从上面来
                    flag = -1;
                    sum[i][sc] = sum[i-1][sc] + ob[i][sc];
                }else{
                    //从左边来
                    flag = 1;
                    sum[i][sc] = sum[i][sc-1] + ob[i][sc];
                }
            }
            //再更新此处的dp

            if(flag == -1){
                dp[i][sc] = Math.min(dp[i-1][sc], sum[i][sc]);
            }else if(flag == 1){
                dp[i][sc] = Math.min(dp[i][sc-1], sum[i][sc]);
            }

        }
        for(int j = sc+1;j < column;j++){
            //先更新对角线处
            int flag = 0;
            //此时不需要判断是否越界
            if(sum[sr-1][j] > sum[sr][j-1]){
                //从上面来
                flag = -1;
                sum[sr][j] = sum[sr-1][j] + ob[sr][j];
            }else if(sum[sr-1][j] < sum[sr][j-1]){
                //从左边来
                flag = 1;
                sum[sr][j] = sum[sr][j-1] + ob[sr][j];
            }else if(sum[sr-1][j] == sum[sr][j-1]){
                //此时需要借助dp
                if(dp[sr-1][j] > dp[sr][j-1]){
                    //从上面来
                    flag = -1;
                    sum[sr][j] = sum[sr-1][j] + ob[sr][j];
                }else{
                    //从左边来
                    flag = 1;
                    sum[sr][j] = sum[sr][j-1] + ob[sr][j];
                }
            }
            //再更新此处的dp

            if(flag == -1){
                dp[sr][j] = Math.min(dp[sr-1][j], sum[sr][j]);
            }else if(flag == 1){
                dp[sr][j] = Math.min(dp[sr][j-1], sum[sr][j]);
            }
        }

    }

    public void print(int [][]nums, int row, int column){
        for(int i = 0;i < row;i++){
            for(int j = 0;j < column;j++){
                System.out.print(nums[i][j]+"\t");
            }
            System.out.println();
        }
        System.out.println();
    }

}
