package leetcode;

import java.util.Arrays;

/*
USER：LQY
DATE：2020/7/25
TIME：9:07
*/
public class leetcode_410_important {

    public static void main(String []args){

    }
    public int splitArray_DP(int[] nums, int m) {
        int lens = nums.length;
        // if(lens == m){
        //     return Math.max(nums);
        // }
        int [][]dp = new int[lens+1][m+1];
        for(int i = 0;i <= lens;i++)
            for(int j = 0;j <= m;j++)
                dp[i][j] = Integer.MAX_VALUE;

        dp[0][0] = 0;
        // int []subSum = new int[lens+1];
        // subSum[1] = nums[0];
        // for(int i = 2;i <= lens;i++)
        //     subSum[i] = subSum[i-1] + nums[i-1];
        for(int i = 1;i < lens;i++)
            nums[i] += nums[i-1];

        for(int i = 1;i <= lens;i++){
            for(int j = 1;j<=m && j<=i;j++){  //j<=m保持前i个连续数最多划分为m段。j<=i保证没有空的段
                for(int k = 0;k < i;k++){   //在前i个连续数已经被符合题意的划分为j-1段时，再在其基础上，找到一个k下标，使得前i个连续数划分为符合题意的j段。这里利用记忆化，从而会产生叠加效应，这也是动态规划的特点。
                    if(k == 0){
                        //此时，直接是将前i个连续数划分为一段，即完整的子数组。
                        dp[i][j] = Math.min(dp[i][j], Math.max(dp[k][j-1], nums[i-1]));
                    }else
                        dp[i][j] = Math.min(dp[i][j], Math.max(dp[k][j-1], nums[i-1] - nums[k-1]));
                }
            }
        }
        return dp[lens][m];
    }
    //二分查找。
//    根据题意可以知道，每一段的和的范围为[maxNums, sumNums]。
//    即不小于nums数组中的最大值，不大于nums数组的和。
//    前者为划分为单个元素的情况，后者为不划分的情况。
//    统计sunNums时如果使用的是int类型可能会越界，最好使用long类型，在返回时强制转换即可。
    public int splitArray_SEA(int[] nums, int m) {
        int maxNums = 0;
        int sumNums = 0;
        for(int i : nums){
            maxNums = maxNums>i? maxNums : i;
            sumNums += i;
        }
        int lowMax = maxNums;
        int highMax = sumNums;

        while(lowMax < highMax){
            int midMax = (lowMax+highMax) >> 1;

            int temp = 0;
            int div = 1;  //初始划分组数为1.
            for(int i : nums){
                temp += i;
                if(temp > midMax){  //再加上第i个数时就超过了
                    //此时前i-1个数可以划分为一组
                    div++;
                    temp = i;  //再初始化，相当于开始新的一组的划分。
                }
            }

            //再重新定位low以及high
            if(div > m){
                //划分多了。说明此时的midMax还是太小了。
                lowMax = midMax + 1;
            }else{
                //划分组数太少了，说明midMax太大了。
                highMax = midMax;
            }
        }
        return lowMax;
    }
}
