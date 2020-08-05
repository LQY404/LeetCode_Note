package leetcode;

import java.util.Arrays;

/*
USER：LQY
DATE：2020/8/3
TIME：15:30
*/
public class leetcode_415 {

    public static void main(String []args){
        String num1 = "0";
        String num2 = "0";
        System.out.println(new leetcode_415().addStrings(num1, num2));
    }

    public String addStrings(String num1, String num2) {
        StringBuffer ans = new StringBuffer();
        int up = 0;
        int n1 = num1.length();
        int n2 = num2.length();
        if(n1 == 0) return num2;
        if(n2 == 0) return num1;

        while(n1>0 && n2>0){
            n1--;
            n2--;
            int v = num1.charAt(n1) - '0' + num2.charAt(n2) - '0' + up;

            up = v / 10;
            v = v % 10;
            // System.out.println("add:"+v);
            ans.insert(0, v+'0');

        }
        if(n1 > n2) n1++;
        if(n1 < n2) n2++;
        while(n1-- > 0){
            int v = num1.charAt(n1) - '0' + up;
            up = v / 10;
            v = v % 10;
            System.out.println("n1 remaind, add:"+v);
//            ans = "" + v + ans;
            ans.insert(0, v+'0');
        }
        while(n2-- > 0){
            int v = num2.charAt(n2) - '0' + up;
            up = v / 10;
            v = v % 10;
            System.out.println("n2 remaind, add:"+v);
//            ans = "" + v + ans;
            ans.insert(0, v+'0');
        }

        if(up > 0){
//            ans = up + ans;
            ans.insert(0, up+'0');
        }
        return ans.toString();
    }
}
