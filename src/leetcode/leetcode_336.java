package leetcode;

import java.util.*;

/*
USER：LQY
DATE：2020/8/6
TIME：15:56
*/
public class leetcode_336 {


    public static void main(String []args){
        String []words = {"abcd","dcba","lls","s","sssll"};

        List<List<Integer>> ans = new leetcode_336().palindromePairs(words);
        for(List l : ans){

        }
    }
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>>ans = new ArrayList<>();
        int n = words.length;
        Map<String,Integer> index = new HashMap<>();
        List<String> revword = new ArrayList<>();
        //添加逆串
        for(int i = 0;i < n;i++)
            revword.add(new StringBuffer(words[i]).reverse().toString());
//       将逆串使用hash对于存储
        for(int i = 0;i < n;i++){
            index.put(revword.get(i), i);
        }
        for(int i = 0;i < n;i++){
            String word = words[i];
            int m = word.length();
            if(m == 0) continue;

            for(int j = 0;j <= m;j++){
                if(isParis(word, j, m-1)){
                    //右部分j - m为回文串
                    //此时只需要查找左部分0 - j-1 是否为某个word的逆即可
                    int left = index.getOrDefault(word.substring(0, j), -1);
                    if(left!=-1 && left!=i){
                        ans.add(Arrays.asList(left, i));
                    }
                }
                if(j>0 && isParis(word, 0, j-1)){
                    //此时左部分0-j-1 为回文串
                    int right = index.getOrDefault(word.substring(j, m), -1);
                    if(right!=-1 && right!=i){
                        ans.add(Arrays.asList(i, right));
                    }
                }
            }
        }

//        for(int i = 0;i < words.length;i++){
//            for(int j = 0;j < words.length;j++){
//                if(i == j) continue;
//                String tem = words[i] + words[j];
//                if(isPairs(tem)){
////                    List<Integer> tem1 = new ArrayList<>();
////                    tem1.addAll(Arrays.asList(i,j));
//                    ans.add(Arrays.asList(i,j));
//                    System.out.println(i+","+j);
//                }
//
//            }
//        }
//
//
////        System.out.println(words);
        return ans;
    }
    boolean isParis(String word, int left, int right){
        while(left < right){
            if(word.charAt(left++) != word.charAt(right--)) return false;
        }
        return true;
    }
    boolean isPairs(String word){
        int lens = word.length();
        if(lens == 1) return true;
        int flag = lens%2;
        int l,r;
        switch (flag){
            case 0:
                r = lens >> 1;
                l = r - 1;
                while(l>=0 && r<lens){
                    if(word.charAt(l--) != word.charAt(r++)) return false;
                }
                return true;
//                break;
            default:
                l = (lens>>1) - 1;
                r = (lens>>1) + 1;
                while(l>=0 && r<lens){
                    if(word.charAt(l--) != word.charAt(r++)) return false;
                }
                return true;

        }

//        return true;
    }
}
