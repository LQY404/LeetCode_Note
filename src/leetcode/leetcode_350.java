package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
USER：LQY
DATE：2020/7/13
TIME：9:15
*/
public class leetcode_350 {
    public static void main(String []args){
        int []nums2 = {1,2,2,1};
        int []nums1 = {2,2};
        int []ans = new leetcode_350().intersect(nums1, nums2);
        for(int i : ans)
            System.out.print(i+" ");
    }
    public int[] intersect1(int []nums1, int []nums2){
        int len1 = nums1.length;
        int len2 = nums2.length;
        if(len1==0 || len2==0) return new int[0];
        List<Integer> ans1 = new ArrayList<>();
        List<Integer> ans2 = new ArrayList<>();

        for(int i = 0; i < len1;i++)
            ans1.add(nums1[i]);

        for(int i = 0;i < len2;i++){
            Integer v = nums2[i];
            if(ans1.contains(v)){
                ans2.add(v);
                ans1.remove(v);
            }
        }
        int []res = new int[ans2.size()];
        int index = 0;
        for(int i: ans2){
            res[index++] = i;
        }
        return res;
    }

    public int[] intersect(int[] nums1, int[] nums2) {
        List<Integer>ans = new ArrayList<>();
        int len1 = nums1.length;
        int len2 = nums2.length;
        if(len1==0 || len2==0) return new int[0];

        int []hash = new int[len1];
        int []book = new int[len1];
        //哈希处理
        for(int i = 0;i < len1;i++){
            int index = nums1[i]%len1;

            hash[index]++;

        }
        for(int i = 0;i < len1;i++){
            System.out.print(hash[i]+" ");
        }
        //再拿nums2遍历
        for(int i = 0;i < len2;i++){
            int v = nums2[i];
            int index = v%len1;
            if(hash[index] != 0){
                ans.add(v);
                hash[index]--;
            }

        }

        int []res = new int[ans.size()];
        int index = 0;
        for(int i: ans){
//            System.out.print(i+"\t");
            res[index++] = i;
        }
        System.out.println();
        return res;
    }
}
