package leetcode;

/*
USER：LQY
DATE：2020/7/10
TIME：9:01
*/
public class leetcode_309_important {


    public static void main(String []args){
        int []prices = {1,2,3,0,2};
        System.out.println(new leetcode_309_important().maxProfitAC(prices));
    }

    public int maxProfitAC(int []prices){
        if(prices == null) return 0;
        int len = prices.length;
        if(len <= 1) return 0;
        int [][]dp = new int[len+1][3];

        dp[0][0] = -prices[0];

        for(int i = 1;i < len;i++){
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][2] - prices[i]);
            dp[i][1] = dp[i-1][0] + prices[i];
            dp[i][2] = Math.max(dp[i-1][1], dp[i-1][2]);
        }

        return Math.max(dp[len-1][1], dp[len-1][2]);
    }

    public int maxProfit(int[] prices) {
        if(prices == null) return 0;
        int len = prices.length;
        if(len <= 1) return 0;
        int [][]dp = new int[len+1][len+1];
        //dp[i][i] = 0。dp[i][i+1] = prices[i] - prices[i-1]。dp[0]不要
        int [][]book = new int[len+1][len+1];
        for(int i = 1;i < len;i++){
            if(prices[i] < prices[i-1]) continue;
            dp[i][i+1] = prices[i] - prices[i-1];
            book[i][i+1] = i+1;
        }
        //最后两个样例超时。。。
//        for(int i = len-1;i >=1;i--){
//            for(int j = i+1;j <= len;j++){
//                if(prices[j-1] < prices[i-1]) continue;
//                dp[i][j] = prices[j-1] - prices[i-1];
//                for(int k = i+1;k < j;k++){
//                    if(k+2 < j){

//                        dp[i][j] = Math.max(dp[i][j], dp[i][k]+dp[k+2][j]);
//                    }else{
//                        dp[i][j] = Math.max(dp[i][j], dp[i][k]);
//                    }
//                }
//                profit = Math.max(profit, dp[i][j]);
//            }
//
//        }
        for(int i = 1;i <= len;i++){
            for(int j = 1;j <= len;j++){
                System.out.print(dp[i][j]+"\t");
            }
            System.out.println();
        }
        return dp[1][len];
    }
}
