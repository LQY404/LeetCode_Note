package leetcode;

/*
USER：LQY
DATE：2020/7/19
TIME：9:07
*/
public class leetcode_312_important {

    public static void main(String []args){
        int []nums = {3, 1, 5, 8};
        System.out.println(new leetcode_312_important().maxCoins(nums));
    }
//      错误想法：
//    dp[i][j]：i->j总共可以获得的金币。包括i与j，即也扎破i位置以及j位置处的气球。
//    状态转移方程为：
//    dp[0->n] = Max(nums[i-1]*nums[i]*nums[i+1] + dp[0->i-1] + dp[i+1->n] ,  dp[0->i]+dp[i->n])
//    正确想法：
//    上面的错在：在i与j之间，如果扎破k位置处的气球，那么dp[i][j] = dp[i][k-1] + dp[k+1][j] + nums[k-1][k][k+1]。
//    即dp[i][j]分为两段：i->k-1与k+1->j，
//    再计算dp[i][k-1]与dp[k+1][j]时，此时，“k-1”与“k+1”已经是相邻的了，所以这两者之间又有相互的关系、互相影响。
//   所以，这样的规划会导致子dp之间相互影响而使得状态转移相当麻烦。
//    新思路：
//    上面的问题是由于i->j区间一直割裂导致的，所以我们就先不使用k来割裂[i->j]，即先不扎破位置i、位置j以及位置k处的气球，
//    使得此次的dp封闭在i-j之内，不会对子dp产生影响。
//    此时dp[i][j]定义变更为：
//    dp[i][j]：扎破i与j之间但不包括i与j位置的气球时，可以获得的金币的最大数。
//    所以，在状态转移时，留下i、j、k三个位置最后扎破。
//    即dp[i][j] = dp[i][k] + dp[k][j] + nums[i]*nums[k]*nums[j]。
//    所以就是要寻找使得dp[i][j]最大化的、与i、j一起最后扎破的气球位置k的值。
//    所以有三重循环。
    public int maxCoins(int[] nums) {
        int lens = nums.length;
        if(lens == 0) return 0;
        if(lens == 1) return nums[0];

        int []nums2 = new int[lens+2];  //扩展左右边界。减少判断语句。可以看做空间换时间
        for(int i = 1;i <= lens;i++){
            nums2[i] = nums[i-1];
        }
        nums2[0] = 1;
        nums2[lens+1] = 1;

        int [][]dp = new int[lens+2][lens+2];
        for(int i = 1;i <= lens+1;i++){
            for(int j = i-1;j >= 0;j--){
                int max = 0;
                for(int k = j+1; k < i;k++){
                    int v = dp[j][k] + dp[k][i] + nums2[j]*nums2[k]*nums2[i];

                    if(v > max) max = v;
                }
                dp[j][i] = max;

            }
        }
        for(int i : dp[0]){
            System.out.print(i+"\t");
        }
        System.out.println();
        return dp[0][lens+1];
    }


}
