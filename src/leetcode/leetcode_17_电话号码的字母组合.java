package leetcode;

import java.util.LinkedList;
import java.util.List;

/*
USER：LQY
DATE：2020/8/26
TIME：8:53
*/
public class leetcode_17_电话号码的字母组合 {

    public static void main(String []args){
        String digits = "234";
        new leetcode_17_电话号码的字母组合().letterCombinations(digits);
    }
    List<String> ans = new LinkedList<>();
    int len;
    String []res;
    public List<String> letterCombinations(String digits) {
        System.out.println((int)('0'));
        String []pred = new String[10];
        pred[0] = pred[1] = "";
        int v = 97;
        for(int i = 2;i < 10;i++){
            pred[i] = "" + (char)(v++);
            pred[i] += (char)(v++);
            pred[i] += (char)(v++);
            if(i==7 || i==9) pred[i] += (char)(v++);
        }
        len = digits.length();
        if(len == 0) return ans;
        res = new String[len];
        for(int i = 0;i < len;i++){
            res[i] = pred[digits.charAt(i)-'0'];
        }

//        for(int i = 0;i < len;i++){
//            System.out.print(res[i]+" ");
//        }

        solve(0, "");

        for(String s: ans){
            System.out.println(s);
        }
        return ans;
    }

    void solve(int nowidx, String tem){
        if(nowidx == len){
            ans.add(tem);
//            System.out.println("add:"+tem);
            return;
        }
        for(int i = 0;i < res[nowidx].length();i++){
            solve(nowidx+1, tem+res[nowidx].charAt(i)+"");
        }
    }
}
