package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
USER：LQY
DATE：2020/7/29
TIME：10:13
*/
public class leetcode_18 {
    public static void main(String []args){

    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        int lens = nums.length;
        for(int x = 0;x <= lens-3;x++){
            int tv = target - nums[x];
            for(int y = x+1;y <= lens-2;y++){
                tv -= nums[y];
                for(int q = lens-1;q>=x+3;q++){
                    tv -= nums[q];
                    for(int z = y+1;z < q;z++){
                        if(tv-nums[z] == 0){
                            ans.add(Arrays.asList(nums[x], nums[y], nums[z], nums[q]));
                        }
                    }
                }
            }
        }

        return ans;
    }
}
