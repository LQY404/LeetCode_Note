package leetcode;

import java.util.Arrays;

/*
USER：LQY
DATE：2020/8/13
TIME：16:18
*/
public class leetcode_43 {

    public static void main(String []args){
        String num1 = "123";
        String num2 = "456";
        System.out.println(new leetcode_43().multiply(num1, num2));
    }

    public String multiply(String num1, String num2) {
        int n1 = num1.length();
        int n2 = num2.length();

        if(n2 > n1){
            String t = num1;
            num1 = num2;
            num2 = t;
            int tn = n1;
            n1 = n2;
            n2 = tn;
        }
        //保持num1一定比num2长
        int p2,p1;
        p2 = n2 - 1;

        int []add = new int[n1+n2];  //进位
        Arrays.fill(add, 0);
//        String ans1 = "";
        while(p2 >= 0){
            int v2 = num2.charAt(p2) - '0';
            p1 = n1 - 1;
            int tadd = n1 + p2;

            while(p1 >= 0){
                int v1 = num1.charAt(p1--) - '0';

                int a = (v1*v2 + add[tadd])/10;  //余数
                int r = (v1*v2 + add[tadd])%10;  //结果
                add[tadd-1] += a;
                add[tadd] = r;
                tadd--;
                System.out.print(v2+"x"+v1+" ");
            }
//            ans1 += add[n1+p2];
            System.out.println();
            for(int i = 0;i < n1+n2;i++){
                System.out.print(add[i]);
            }
            System.out.println();
            p2--;
        }

        String ans = "";
        int i = 0;
        while(i<n1+n2 && add[i]==0) i++;
        if(i == n1+n2) return "0";
        while(i < n1+n2){
//            System.out.print(add[i]);
            ans += add[i++];
        }
//        System.out.println("the ans1 is "+ans1);
        System.out.println();
        return ans;

    }
}
