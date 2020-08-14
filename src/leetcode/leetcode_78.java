package leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
USER：LQY
DATE：2020/8/14
TIME：9:00
*/
public class leetcode_78 {

    public static void main(String []args){

    }

    public List<List<Integer>> subsets(int[] nums) {
        LinkedList<List<Integer>> ans = new LinkedList<>();
        ans.add(new LinkedList<>());  //这个很关键。必须保证有一个空集。
        int n = nums.length;

        for(int i = n-1;i >= 0;i--){
            int v = nums[i];  //即将新添加的数
            int size = ans.size();
            for(int j = 0;j < size;j++){
                //先依次取出已经形成的子集
                List<Integer> temp = new LinkedList<>(ans.get(j));
                //此时的temp中放的就是一个子集
//                再向原子集中添加当前的新数字，从而得到一个新的子集
                temp.add(v);
//                最后将这个新子集添加到结果集中
                ans.add(temp);
            }
        }

        return ans;
    }
}
