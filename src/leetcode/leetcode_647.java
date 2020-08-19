package leetcode;

/*
USER：LQY
DATE：2020/8/19
TIME：10:07
*/
public class leetcode_647 {

    public int countSubstrings(String s) {
        if(s == null) return 0;
        int n = s.length();
        if(n == 1) return n;
        if(n==2 && s.charAt(0)==s.charAt(1)) return 3;
        else if(n==2 && s.charAt(0)!=s.charAt(1)) return 2;

        int [][]dp = new int[n+1][n+1];
        for(int i = 0;i <= n;i++) dp[i][i] = 1;  //单个字符一定是回文串

        int count = 0;
        for(int i = 0;i < n;i++){
            char ns = s.charAt(i);
            count++;
            if(i<n-1 && s.charAt(i+1)==ns){
                count++;
                dp[i][i+1] = 1;
            }

            int left,right;
//            偶数回文串
            left = i;
            right = i + 1;
            while(left>=0 && right<n){
                if(dp[left][right]!=1 || left-1<0 || right+1>=n || s.charAt(left-1)!=s.charAt(right+1)) break;
                left--;
                right++;
                dp[left][right] = 1;
                count++;
            }


//            以字符s.charAt(i)为中心的回文串（奇数回文串）
            left = right = i;
            // right = i + 1;
            while(left>=0 && right<n){
                if(dp[left][right]!=1 || left-1<0 || right+1>=n || s.charAt(left-1)!=s.charAt(right+1)) break;

                left--;
                right++;
                dp[left][right] = 1;
                count++;
            }
        }
        return count;

    }
}
