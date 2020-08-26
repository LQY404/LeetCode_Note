package leetcode;

import java.util.*;

/*
USER：LQY
DATE：2020/8/25
TIME：8:50
*/
public class leetcode_491_递增子序列 {

    public static void main(String []args){
        int []nums = {4, 6, 7, 7};
        new leetcode_491_递增子序列().findSubsequences(nums);
    }

    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> ans = new LinkedList<>();
        Map<List<Integer>, Integer> ac = new HashMap<>();

        int len = nums.length;

        if(len == 1) return ans;

        for(int i = 0;i < len;i++){
            int n1 = nums[i];
            for(int j = i+1;j < len;j++){
                int n2 = nums[j];
                if(n1<=n2 && !ac.containsKey(Arrays.asList(n1,n2))){
//                    if(ac.containsKey(Arrays.asList(n1,n2))) continue;
                    ans.add(Arrays.asList(n1,n2));
                    ac.put(Arrays.asList(n1,n2), j);
                }
            }
        }

        if(ans.isEmpty()) return ans;

        LinkedList<List<Integer>>tem = new LinkedList<>();

        while(true){


            System.out.print("this time :");
            for(List i: ans){
                for(Object j: i){
                    System.out.print(j+" ");
                }
                System.out.println();
            }
            System.out.println();
            while(!ans.isEmpty()){
                LinkedList<Integer> l = new LinkedList<>(ans.remove(0));
                int s = ac.get(l) + 1;     //取出最后一个元素在nums中对应的下标的后一个位置
                int n1 = l.get(l.size()-1); //的值
                while(s < len){  //从nums[s->len)这一段中，每次加入一个元素，枚举
                    LinkedList<Integer> p = new LinkedList<>(l);
                    int n2 = nums[s++];
                    p.addLast(n2);
                    //如果此时ans[0]中最后一个元素，以及其在nums中对应位置处的后一个元素满足递增，同时ACMap中还未记录该list，则加入新的ans中，同时在ACMap中记录下来
                    if(n1<=n2 && !ac.containsKey(p)){
                        System.out.println("add "+p);
                        ac.put(p, s-1);  //注意这里是(s-1)！！！因为前面已经让s自加了
                        tem.add(p);
                    }

                }

            }
            if(tem.isEmpty()) break;
            System.out.println("next time :");
            for(List i: tem){
                for(Object j: i){
                    System.out.print(j+" ");
                }
                System.out.println();
            }
            System.out.println();
//            ans.addAll(tem);
            ans.addAll(tem);
            tem.clear();

        }
        System.out.println("the answer:");
        ans.clear();
        ans.addAll(ac.keySet());
            for(List l: ac.keySet()){
                for(Object i: l){
                    System.out.print(i+" ");
                }
                System.out.println();
            }

//
        for(Map.Entry k: ac.entrySet()){
            System.out.println(k.getKey()+":"+k.getValue());
        }
        return ans;
    }
}
