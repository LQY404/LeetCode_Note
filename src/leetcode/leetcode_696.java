package leetcode;

/*
USER：LQY
DATE：2020/8/10
TIME：9:15
*/
public class leetcode_696 {

    public int countBinarySubstrings(String s) {
        int n = s.length();

        if(n <= 1) return 0;
        int s0,s1,e0,e1;
        s0 = s1 = e0 = e1 = 0;
        int flag = 0;
        if(s.charAt(0) == '0'){
            while(e0<n && s.charAt(e0)=='0') e0++;
//            s1 = e1 = e0;
            flag = 1;
        }else{
            while(e1<n && s.charAt(e1)=='1') e1++;
//            s0 = e0 = e1;
            flag = 0;
        }
        int count = 0;
        while(s0<n && s1<n && e0<=n && e1<=n){
            switch(flag){
                case 0:
                    s0 = e0 = e1;
                    while(e0<n && s.charAt(e0)=='0') e0++;
                    flag = 1;
                    break;
                case 1:
                    s1 = e1 = e0;
                    while(e1<n && s.charAt(e1)=='1') e1++;
                    flag = 0;
                    break;
            }
            if(s1==n || s0==n) break;
            // if(e1-s1 != e0-s0) continue;
            // System.out.println("0 from "+s0+" to "+e0);
            // System.out.println("1 from "+s1+" to "+e1);

            count += Math.min(e0-s0, e1-s1);

        }
        return count;
    }
}
