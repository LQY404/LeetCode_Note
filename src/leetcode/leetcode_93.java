package leetcode;

import java.util.ArrayList;
import java.util.List;

/*
USER：LQY
DATE：2020/8/9
TIME：8:31
*/
public class leetcode_93 {

    public static void main(String []args){
        String s = "25525511135";
        List<String> ans = new leetcode_93().restoreIpAddresses(s);
        for(String d : ans){
            System.out.println(d);
        }
    }
    public List<String> restoreIpAddresses(String s) {
        List<String> ans = new ArrayList<>();

        int n = s.length();
        if(n < 4) return ans;
        if(n == 4){
            String t = "";
            for(int i = 0;i < n-1;i++)
                t += s.charAt(i) + '.';
            t += t.charAt(n-1);
            ans.add(t);
            return ans;
        }
//        int []index = {1, 2, 3};
        System.out.println("start");
        for(int i = 1;i<=3 && i<n;i++){
            String s1 = s.substring(0, i);
            if(Integer.valueOf(s1) > 255) {
                System.out.println("s1 "+s1+" out of the range");
//                break;
                continue;
            }
            for(int j = i+1;j<=i+3 && j<n;j++){
                String s2 = s.substring(i, j);
                if(Integer.valueOf(s2) > 255) {
                    System.out.println("s2 "+s2+" out of the range");
//                    break;
                    continue;
                }
                for(int k = j+1;k<=j+3 && k<n;k++){

                    String s3 = s.substring(j, k);
                    if(n-k > 3) continue;
                    String s4 = s.substring(k, n);

                    if(s3=="" || s4=="" || Integer.valueOf(s3)>255 || Integer.valueOf(s4)>255) {
                        System.out.println("s3 "+s3+" or s4 "+s4+" out of the range");
//                        break;
                        continue;
                    }
                    if(!judge(s1) || !judge(s2) || !judge(s3) || !judge(s4)) continue;
                    //此时添加
                    String t = "";
                    for(int m = 0;m < n;m++){
                        if(m==i-1 || m==j-1 || m==k-1){
                            t += s.charAt(m) + ".";
                        }else{
                            t += s.charAt(m);
                        }
                    }
                    System.out.println("get："+t);
                    ans.add(t);
                }
            }
        }


        return ans;
    }
    boolean judge(String s){
        int n = s.length();
        if(n==2 && Integer.valueOf(s)<10) return false;
        if(n==3 && Integer.valueOf(s)<100) return false;

        return true;
    }
}
