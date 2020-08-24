package leetcode;

/*
USER：LQY
DATE：2020/8/24
TIME：9:07
*/
public class leetcode_459_重复的字符串 {

    public static void main(String []args){
        String s = "abab";
        System.out.println(new leetcode_459_重复的字符串().repeatedSubstringPattern(s));
    }
    public boolean repeatedSubstringPattern(String s) {
        if(s == null) return false;
        int len = s.length();
        if(len == 1) return false;
//
//        for(int step = 1;step < len;step++){
//            String pre = s.substring(0, step);
//            int flag = 1;
//            int i = step;
//            while(i+step <= len){
//                flag = 0;
//                String now = s.substring(i, i+step);
//                if(!now.equals(pre)){
//                    flag = -1;
//                    System.out.println(now+" != "+pre);
//                    break;
//                }
//                i += step;
//            }
//
//            if(flag==0 && i==len) return true;
//        }
//        这是特殊的做法
//        String S = s + s;
//        System.out.println((s+s).substring(1, 2*len-1).contains(s));
        return false;
    }
}
