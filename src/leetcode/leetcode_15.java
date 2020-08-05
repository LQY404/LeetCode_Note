package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
USER：LQY
DATE：2020/7/27
TIME：9:03
*/
public class leetcode_15 {
    public static void main(String []args){
        int []nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> ans = new leetcode_15().threeSum(nums);
        for(List l : ans){
            System.out.println(l);
        }
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);  //先排序
        List<List<Integer>> ans = new ArrayList<>();
        int lens = nums.length;
        int x = 0;  //左指针
        int z = lens - 1;   //右指针
        int y;   //中间的遍历指针
        // 采用双指针的做法。
        // 虽然是求三个数之和。
        // 但是可以先固定一个x位置，这样就成了求两个数之和 nums[y]+nums[z] == -nums[x]的问题。
        // 注意。必须使用去重的if判断语句来降低复杂度，同时，
        // 如果固定的第一个数nums[x] > 0，而由于现在nums数组是排好序的，此时
        // 想要三个数的和等于0，肯定是不可能的，直接break结束即可。这样进一步降低复杂度。
        while(x<=z-2 && x<lens-2){

            int vx = nums[x];
            if(vx > 0) break;
            // 下面语句有两个作用：
            //①去除重复的x。比如：[-1,-1,0,1]。此时如果不加上下面的判断，
            // 则会有[-1,0,1]、[-1,0,1]两个重复的答案。
            // ②降低复杂度。这个很明显。
            if(x>0 && nums[x-1]==vx){
                x++;
                continue;
            }
            // System.out.println("start at "+x+","+z);
            y = x + 1;
            int tz = z;
//            int flag = 0;
            while(y<tz && tz<lens){
                int vy = nums[y];
                int vz = nums[tz];
                // if(((tz<lens-1&&nums[tz+1]==vz)){
                //     tz--;
                //     continue;
                // }
                // int v = vx + nums[y] + nums[tz];
                if(vy+vz+vx == 0){
                    //此时满足条件
                    // System.out.println("fit at :"+x+","+tz);
                    ans.add(Arrays.asList(vx, vy, vz));
                    // List<Integer>tem = new ArrayList<>();
                    // tem.add(vx);
                    // tem.add(nums[y]);
                    // tem.add(nums[tz]);
                    // if(!ans.contains(tem))
                    //     ans.add(tem);
//                    x++;
                    // z--;
                    //跳过重复值
                    //原因和外层循环中对num[x]的判断一样。避免重复序列
                    while(y<tz && nums[tz]==nums[tz-1]) tz--;
                    while(y<tz && nums[y]==nums[y+1]) y++;
                    //最后不要忘记还有往内同时收缩
                    y++;
                    tz--;
//                    flag = 1;
                    // System.out.println("next start at "+x+","+z);
                    // break;
                }
                else if(vx+vy+vz > 0){
                    //此时太大，右指针左移
                    //先去除重复
                    while(y<tz && nums[tz]==nums[tz-1]) tz--;
                    //再左移一次
                    tz--;

                }else{
                    //此时太小。左指针右移
                    //先去除重复
                    while(y<tz && nums[y]==nums[y+1]) y++;
                    // 再右移一次
                    y++;
                }
            }
//            if(flag == 0)
            x++;
//            z = lens - 1;

        }

        return ans;
    }
}
