package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
USER：LQY
DATE：2020/8/1
TIME：10:22
*/
public class leetcode_632 {

    public static void main(String []args){
        List<List<Integer>> nums = new ArrayList<>();
        nums.add(Arrays.asList(-5, -4, -3, -2, -1, 1));
        nums.add(Arrays.asList(1, 2, 3, 4, 5));
//        nums.add(Arrays.asList(5,18,22,30));
        new leetcode_632().smallestRange(nums);
    }
    public int[] smallestRange(List<List<Integer>> nums) {

        int k = nums.size();
        int []ans = new int[2];
        int left = -10000;
        int right = 100000;
        //定义k个指针
        int []p = new int[k+1];
        for(int i = 1;i <= k;i++){
            p[i] = nums.get(i-1).size() - 1;
        }
//        System.out.println(Arrays.toString(findMaxAndMinK(nums, p)));
        //遍历
        while(!isOk(p)){
            int []pc = findMaxAndMinK(nums, p);
//            if(pc[0] == pc[1]) break;
            int tleft = nums.get(pc[0]-1).get(p[pc[0]]);
            int tright = nums.get(pc[1]-1).get(p[pc[1]]);
            System.out.println("the min at "+pc[0]+" and the v is "+tleft+"\nthe max at "+pc[1]+" and the v is "+tright);
            if(tright-tleft<right-left || (tright-tleft==right-left && tleft<left)){
                System.out.println("["+left+","+right+"] replaced by ["+tleft+","+tright+"]");
                left = tleft;
                right = tright;

            }
            do{
//                System.out.println("重复值...");
                p[pc[1]]--;
            }while(p[pc[1]]-1>=0 && nums.get(pc[1]-1).get(p[pc[1]])==nums.get(pc[1]-1).get(p[pc[1]]-1));
        }

        for(int i : p)
            System.out.print(i+" ");

        return ans;
    }
    //判断是否全部查找过
    public boolean isOk(int []p){
        int k = p.length;

        for(int i = 1;i < k;i++){  //第i个列表
            if(p[i] == -1) return true;
        }
        return false;
    }
//    找出指向的数最小以及最大的那个指针 从1开始
    public int[] findMaxAndMinK(List<List<Integer>> nums, int[]p){
        int maxindex = 0;
        int max = 0;
        int min = Integer.MAX_VALUE;
        int minindex = -1;

        int k = nums.size();
        for(int i = 1;i <= k;i++){
            if(p[i] == -1) continue;
            int tem = nums.get(i-1).get(p[i]);
            if(tem > max){
                maxindex = i;
                max = tem;
            }
            if(tem < min){
                min = tem;
                minindex = i;
            }
        }

        return new int[]{minindex ,maxindex};
    }

}
