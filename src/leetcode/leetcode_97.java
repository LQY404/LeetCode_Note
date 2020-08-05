package leetcode;

/*
USER：LQY
DATE：2020/7/18
TIME：9:31
*/
public class leetcode_97 {

    public static void main(String []args){
        String s1 = "a";
        String s2 = "";
        String s3 = "a";

        System.out.println(new leetcode_97().isInterleave2(s1, s2, s3));
    }
    public boolean isInterleave2(String s1, String s2, String s3){
        int len1 = s1.length();
        int len2 = s2.length();
        int len3 = s3.length();
        if(len3 != len1+len2) return false;
        if(len3 == 0) return true;
        boolean [][]dp = new boolean[len1+1][len2+1];
        dp[0][0] = true;

        for(int i = 0;i <= len1;i++){
            for(int j = 0;j <= len2;j++){
                boolean tb = dp[i][j];
                if(i > 0){
                    tb = tb || (s1.charAt(i-1)==s3.charAt(i+j-1));
                }
                if(j > 0){
                    tb = tb || (s2.charAt(j-1)==s3.charAt(i+j-1));
                }
                dp[i][j] = tb;
            }
        }

        return dp[len1][len2];
    }

    public boolean isInterleave(String s1, String s2, String s3) {
        int len1 = s1.length();
        int len2 = s2.length();
        int len3 = s3.length();
        if(len3 != len1+len2) return false;
        boolean []dp = new boolean[len3];
        int index1 = 0;
        int index2 = 0;
//        solve(s1, s2, s3, index1, index2, 0, dp);
        for(boolean d : dp)
            System.out.print(d+" ");
        System.out.println();
        return dp[len3-1];
    }

    //这种深度搜索的方法可以解决绝大多数的测试用例。
//    但是还是无法AC。会超时。
//    原因在于：搜索的过程中，没有将中间状态记住，比如在搜索匹配S3[i->j]时，本来S3[k->j]上次已经搜索过并且已经可以确定是否匹配，
//    但是由于并未记住状态，所以相当于重复计算。所以超时。。。。
    public boolean solve(String s1, String s2, String s3, int index1, int index2, int i, boolean []dp){
        if(index1 >= s1.length()){
            while(i < s3.length()){
                if(s3.charAt(i) != s2.charAt(index2))
                    return false;
                dp[i] = true;
                i++;
                index2++;
            }
            return true;
        }else if(index2 >= s2.length()){
            while(i < s3.length()){
                if(s3.charAt(i) != s1.charAt(index1))
                    return false;
                dp[i] = true;
                i++;
                index1++;
            }
            return true;
        }else{
            char c1 = s1.charAt(index1);
            char c2 = s2.charAt(index2);
            char c3 = s3.charAt(i);
            if(c3==c1 && c3==c2){
                dp[i] = solve(s1, s2, s3, index1+1, index2, i+1, dp) || solve(s1, s2, s3, index1, index2+1, i+1, dp);
            }else if(c3 == c1){
                dp[i] = i>=1? dp[i-1]:true;
                solve(s1, s2, s3, index1+1, index2, i+1, dp);
            }else if(c3 == c2){
                dp[i] = i>=1? dp[i-1]:true;
                solve(s1, s2, s3, index1, index2+1, i+1, dp);
            }else{
                return false;
            }
            return dp[i];
        }
    }
}
